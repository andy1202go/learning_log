# MySQL

[toc]

参考以左耳朵的MySQL路线来学习下

## 官方文档（8.0版本）

### 0 Data Prepare

https://dev.mysql.com/doc/employee/en/employees-installation.html

### 1 General Infomation

MySQL的基本情况，历史，特点；8.0版本的新特性

#### 1.0 官方定位

- vary fast
- 多线程
- 多用户
- 鲁棒

一些特点

- GNU license
- 双重许可，可以根据GNU的条款用作开源产品，也可以从Oracle购买商业许可

#### 1.1 About This Manual

针对8.0版本，不兼容之前的。

不提供sql指导或关系数据库概念。

各种文档获取方式，以及最新文档地址等。

### 2 Installing and Upgrading MySQL

安装和后续的配置；老版本的升级

#### 2.4 Installing MySQL on macOS

- check系统版本
- 下载社区版本https://dev.mysql.com/downloads/mysql/
- 安装数据库
  - root/hellow0rld
- 在系统偏好配置中，双击MySQL图标，然后启动数据库，并勾选随机启动

### 3 Tutorial

基本使用教程

### 4 MySQL Programs

MySQL的一些程序，比如mysqld；环境参数等

### 5 MySQL Server Administration

MySQL服务器管理

### 6 Security

安全：常规，权限控制，加密连接，插件，企业级

### 7 Backup and Recovery

### 8 Optimization

优化sql表达式，索引，数据库结构，InnoDB表，执行计划，性能衡量

#### 8.0 案例

##### 1 order by [date] desc

- 描述
  - order by的条件是单独索引，且是时间类型的，区分度最大
  - where里面有大量的筛选条件
  - where里面的筛选条件不包含order by 的
  - where筛选条件里面有些条件有区分度较大的索引
- 现象是
  - 实际查出来的行数只有179行
  - 实际耗时160s左右
  - explain后发现，走的是order by条件的索引，extra中是using where，rows是3w左右
- 怀疑
  - order by 时间导致的？
  - order by的索引没有在where里面？
  - 数据量太大？
  - 其他sql拖慢整个库？
- 实验
  - order by 字段2 desc，explain发现走了where中的区分度最大的索引，using filesort,using where，rows大概7w，实际执行时间大概2s
  - order by 主键 的desc，因为主键和时间正相关，所以可以这么用。情况和上述实验类似，执行时间更快一些
- 疑问
  - order by 索引与否的区别？
  - explain中rows和实际的差距？
  - 初始情况为啥那么慢？？

下面一个个看

- order by 和索引的关系

  参考官方文档的来：https://dev.mysql.com/doc/refman/5.7/en/order-by-optimization.html#order-by-index-use

  建议是使用索引来加速排序这个动作，而且这个索引理论上是组合与否都可以的。

  另外，根据https://www.cnblogs.com/zhaoyl/archive/2012/05/04/2483513.html order by的字段不在where条件但在select中的时候是有排序操作的；

  所以原始的order by 索引字段的情况，是对排序操作有一定的加速作用的

- explain中rows和实际的差距？

  - 官方说了，这个是tm的估计值，是MySQL认为一定会检查的行数https://dev.mysql.com/doc/refman/5.7/en/explain-output.html#explain_rows

- 初始情况为啥那么慢？？

  其实应该分成几个子问题

  - 索引为什么选用了order by 的索引字段？
    - 读题：区分度最大
    - 所以where和order是一起看的，只是order是在where执行之后的
  - 选用这个索引字段会发生什么
    - 按照一系列where条件去过滤数据，而且没有索引字段的筛选，很慢；
    - 筛选后按照索引字段进行排序，很快
  - 为什么换一下order by的字段会比较快
    - 因为总体数据结果量比较小，所以排序影响其实不大，但order by 主键的话，应该会更好

- 最优改动方案

  - 新建一个索引，使用where中大区分度字段和order by 字段结合

  - 如果结果数据量预估都比较小，可以order by 主键 来解决，改动最小

- 关键点

  - order by 和where一起筛选索引字段
  - order by 和where的执行顺序
  - 索引对order by 的优化
  - rows的估算

后续发现使用id不是最好的方案，因为某些情况下，优化器认为id的区分度最大，然后还是全表扫描；

不如用left(date_time)的方式来，一方面保证了业务的正确性，一方面避免了走date_time索引全表扫描

### 9 Language Structure

语言结构（？）

### 10 Character Sets，Collations，Unicode

字符集相关

#### 10.1 Character Sets and Collations in General

使用一个例子，A,B,a,b的编码和比较，来通俗的解释了什么是字符集，什么是Collation；

> Suppose that we have an alphabet with four letters: A, B, a, b. We give each letter a number: A = 0, B = 1,
> a = 2, b = 3. The letter A is a symbol, the number 0 is the encoding for A, and the combination of all four
> letters and their encodings is a character set.
> Suppose that we want to compare two string values, A and B. The simplest way to do this is to look at the
> encodings: 0 for A and 1 for B. Because 0 is less than 1, we say A is less than B. What we've just done is
> apply a collation to our character set. The collation is a set of rules (only one rule in this case): “compare
> the encodings.” We call this simplest of all possible collations a binary collation.

总结MySQL的字符集有几个特点：

- 可以用不同字符集存储字符串
- 用不同的规则集来比较字符串
- 在同一个服务器，同一个数据库，甚至是同一个表，都可以有不同的字符集设定
- 在任何级别启用字符集和排序规则的规范。

#### 10.2 Character Sets and Collations in MySQL

讲述了关于字符集和排序规则，在MySQL中的情况；

通过show character set [like ''];查看字符集；

通过SHOW COLLATION WHERE Charset = 'utf8mb4';查看排列规则；

- 每种字符集有默认的collation；
- 不同的字符集，不可能有相同的collation
- collation的命名有规则，一般是以对应的character set开头，然后跟1个或多个collation特性的描述，比如utf8mb4_general_ci

##### 10.2.1 Character Set Repertoire

##### 10.2.2 UTF-8 for Metadata

### 11 Data Types

数据类型

### 12 Functions and Operators

#### 12.7 Date and Time Functions

##### now()

https://dev.mysql.com/doc/refman/5.7/en/date-and-time-functions.html#function_now

> Returns the current date and time as a value in `'*`YYYY-MM-DD hh:mm:ss`*'` or *`YYYYMMDDhhmmss`* format, depending on whether the function is used in string or numeric context. The value is expressed in the session time zone.

也就是根据类型要求，返回两种不同的格式。

遇到过时间字段是bigint的，然后插入的时候是插入的now()，结果就是*`YYYYMMDDhhmmss`* 的样子。

后续的说明主要是和函数 [`SYSDATE()`](https://dev.mysql.com/doc/refman/5.7/en/date-and-time-functions.html#function_sysdate)的差异点比较，主要有两点

1. NOW()返回的是一个常量，是声明开始执行时间点的；sysdate()返回的是准确的时间点的时间；for example

   ```mysql
   mysql> SELECT NOW(), SLEEP(2), NOW();
   +---------------------+----------+---------------------+
   | NOW()               | SLEEP(2) | NOW()               |
   +---------------------+----------+---------------------+
   | 2006-04-12 13:47:36 |        0 | 2006-04-12 13:47:36 |
   +---------------------+----------+---------------------+
   
   mysql> SELECT SYSDATE(), SLEEP(2), SYSDATE();
   +---------------------+----------+---------------------+
   | SYSDATE()           | SLEEP(2) | SYSDATE()           |
   +---------------------+----------+---------------------+
   | 2006-04-12 13:47:44 |        0 | 2006-04-12 13:47:46 |
   +---------------------+----------+---------------------+
   ```

2. set timestamp会改变now()的值，不会影响sysdate()；设为0后会恢复

#### 12.8 String Functions and Operators

##### 12.8.1 String Comparison Functions and Operators

暂时仅关注其中对反斜线转义的描述：

> Note
> MySQL uses C escape syntax in strings (for example, \n to represent the
> newline character). If you want a LIKE string to contain a literal \, you must
> double it. (Unless the NO_BACKSLASH_ESCAPES SQL mode is enabled, in which
> case no escape character is used.) For example, to search for \n, specify it as
> \\\n. To search for \, specify it as \\\\\\\; this is because the backslashes are
> stripped once by the parser and again when the pattern match is made, leaving a
> single backslash to be matched against.

### 13 SQL Statements

表达式

#### 13.6 Compound Statement Syntax

##### 13.6.5 Flow Control Statements

###### 13.6.5.1 CASE Statement

case表达式，属于混合表达式-流程控制表达式分类；

有两种语法：

```sql
CASE case_value
WHEN when_value THEN statement_list
[WHEN when_value THEN statement_list] ...
[ELSE statement_list]
END CASE
Or:
CASE
WHEN search_condition THEN statement_list
[WHEN search_condition THEN statement_list] ...
[ELSE statement_list]
END CASE
```

第二种用过，比如update的时候给不同的where值设定不同的value；

第一种，是拿case的字段值和when值做比较，直观没想到使用场景；

举个例子如下

```sql
SELECT
    NAME '英雄',
    CASE NAME
        WHEN '德莱文' THEN
            '斧子'
        WHEN '德玛西亚-盖伦' THEN
            '大宝剑'
        WHEN '暗夜猎手-VN' THEN
            '弩'
        ELSE
            '无'
    END '装备'
FROM
    user_info;
```



##### 13.6.7 Condition Handling

###### 13.7.5.36 SHOW TABLE STATUS Statement

```sql
SHOW TABLE STATUS
[{FROM | IN} db_name]
[LIKE 'pattern' | WHERE expr]
```

table信息，尤其是看数据大小等、行数据大小

- [ ] 补充截图

部分字段说明：

- Rows：行数，Innodb不准确，甚至只是40-50%的样子，用count自己去查
- Data_length：Innodb下，是近似估计的已分配的空间大小。bytes。

猜测Innodb不准的原因，是Innodb存储的page导致的。

虽然是近似值，但也是可以做参考的。

#### 13.8 Utility Statements

效用表达式（？），DESCRIPTION,EXPLAIN,HELP,USE

##### 13.8.1 DESCRIBE Statement

是explain的同义词；

##### 13.8.2 EXPLAIN Statement

explain更多用于查看执行计划(that is, an explanation of how MySQL would execute a query).

desc是其他的，比如desc table_name

语法如下：

```sql
{EXPLAIN | DESCRIBE | DESC}
tbl_name [col_name | wild]
{EXPLAIN | DESCRIBE | DESC}
[explain_type]
{explainable_stmt | FOR CONNECTION connection_id}
{EXPLAIN | DESCRIBE | DESC} ANALYZE [FORMAT = TREE] select_statement
explain_type: {
FORMAT = format_name
}
format_name: {
TRADITIONAL
| JSON
| TREE
}
explainable_stmt: {
SELECT statement
| TABLE statement
| DELETE statement
| INSERT statement
| REPLACE statement
| UPDATE statement
}
```

###### Obtaining Table Structure Information

```sql
desc table_name;
```

###### Obtaining Execution Plan Information

explain

- 支持SELECT, DELETE, INSERT, REPLACE, and UPDATE语句，后续也会支持table语句
- TODO

### 14 MySQL Data Dictionary

### 15 The InnoDB Storage Engine

InnoDB存储引擎相关

### 16 Alternative Storage Engines

其他存储引擎

### 17 Replication

复制

### 18 Group Replication

### 19 MySQL Shell

### 20 Using MySQL as a Document Store

文件存储

### 21 InnoDB Cluster

### 22 InnoDB ReplicaSet

### 23 MySQL NDB Cluster 8.0

### 24 Partitioning

分片

### 25 Stored Objects

### 26 INFOMATION_SCHEMA Tables

#### 26.3 General Table Information

##### 26.3.25 The INFORMATION_SCHEMA TABLES Table

见[13.7.5.36 SHOW TABLE STATUS Statement](######13.7.5.36 SHOW TABLE STATUS Statement)

### 27 MySQL Performance Schema

### 28 MySQL sys schema

### 29 Connectors and APIs

### 30 MySQL Enterprise Edition

### 31 MySQL Workbench

https://dev.mysql.com/downloads/workbench/

### 32 MySQL on the OCI Marketplace

### FAQ

#### 1 USING BTREE 的索引是b树还是b+树

b+树；

只有b+和hash两种索引；

b和b+的区别在于数据是不是完全在叶子节点，和有无横向的指针（我认为比较关键的两点）

https://dev.mysql.com/doc/refman/5.7/en/create-index.html#create-index-options

官方说明中，索引类型就是叫BTREE，所以只能说底层是B+（引擎版本有关？）

### Error Messages and Common Problems

## 深入浅出MySQL（第2版）

### 第一部分 基础篇

### 第1章 MySQL的安装与配置

书里只介绍了Windows和Linux两种安装；我们找找Mac的

#### mac安装

[2.4 Installing MySQL on macOS](####2.4 Installing MySQL on macOS)

然后再下载workbench来获取一个图形化操作页面

[31 MySQL Workbench](###31 MySQL Workbench)

然后准备数据，以mysql官网提供的employee数据来导入先，参考

[0 Data Prepare](###0 Data Prepare)

#### 导入数据

1. 通过0下载文件到本地；
2. 打开benchmark，导入数据；
3. 出错，通过https://blog.csdn.net/qq_39812545/article/details/79934262修改绝对路径；
4. 再次尝试，完成导入；
5. ![image-20211129230636313](./资料/imgs/image-20211129230636313.png)



### 第2章 SQL基础

目的：标准SQL的使用方法，以及一些拓展SQL的使用方法。

#### 2.1 历史及标准化

- 是使用关系模型的数据库应用语言
- ANSI标准，1970年代至今

#### 2.2 (My)SQL使用入门

(My)SQL这种命名，是为了介绍标准SQL之后，再介绍下MySQL对其的拓展。

##### 2.2.1 SQL分类

三类SQL语句

- DDL：Data **Definition** Languages，数据定义语言；定义各种数据库对象
  - 定义数据段，数据库，表，列，索引
  - 常用语句：create,drop,alter,rename
- DML：Data **Manipulation** Languages，数据操纵语言，操作数据库记录
  - 增删改查
  - 常用语句：insert,delete,update,select
- DCL：Data Control Languages，数据控制语言，控制数据段的访问和许可
  - 定义数据库、表、字段、用户的访问权限和安全级别
  - 常用语句：grant,revoke

##### 2.2.2 DDL语句

1. 创建数据库：create database dbname
2. 执行结果：3部分解读，Query OK, 1row affected (0.01 sec)
3. 删除数据库：drop database dbname；注意：**所有drop语句操作的结果都是显示“0 rows affected”**
4. 创建表：

```sql
CREATE TABLE tablename(
column_name_1 column_type_1 constrains,
  ...
 column_name_n column_type_n constrains
)
```

5. 查看表的定义

```sql
DESC tablename;
show create table tablename \G;
```

6. 删除表：drop table tablename
7. 修改表

```sql
---修改表字段
ALTER TABLE tablename MODIFY [COLUMN] column_definition [FIRST|AFTER col_name]; 
---增加表字段
ALTER TABLE tablename ADD [COLUMN] column_definition [FIRST|AFTER col_name]; 
---删除表字段
ALTER TABLE tablename DROP [COLUMN] col_name; 
---字段改名 
ALTER TABLE tablename CHANGE [COLUMN] old_col_name new_column_definition;
---表改名
ALTER TABLE tablename RENAME [TO] new_tablename;
```

##### 2.2.3 DML语句

对数据库中，表记录的操作，增删改查

###### 2.2.3.1 插入记录

```sql
INSERT INTO tablename(field1,field2...) VALUES(value1,value2...)
```

其中field是字段名，value是对应的值；

两个特殊规则

1. 可以只有value没有field，但顺序要和表的一致，且要全部字段对应的值都有

```sql
--官方数据集
insert into employees.departments values('d100','Test D');

--错误示范 Error Code: 1136. Column count doesn't match value count at row 1
insert into employees.departments values('Test D');
```

2. 可以指定部分field插入数据，未指定的字段要求必须是以下的一种或几种
   - 可空字段
   - 非空但有默认值的
   - 自增字段

还有一个很好的特性是，可以批量插入数据，节省大量网络开销

```sql
INSERT INTO tablename(field1,field2...)
VALUES
(record1_value1,record1_value2...),
(record2_value1,record2_value2...),
...
(recordn_value1,recordn_value2...);
```

###### 2.2.3.2 更新记录

```sql
UPDATE tablename SET field1=value1,field2=value2... [WHERE CONDITION]
```

更屌的是，可以同时更新多个表中数据

```sql
UPDATE t1,t2... SET t1.field1=expr1,tn.fieldn=exprn...[WHERE CONDITION]
```

据说，是多用于

> 根据一个表的字段来动态的更新另一个表的字段

- [ ] 找一个实际的update多个表的case

一条SQL语句更新一张表的多个字段：

```sql
update table_name
set column_name =
case
when "a" then "aa"
when "b" then "bb"
end,
column_name2 = 
when "a" then "a1"
when "b" then "b2"
end
where column_name3 in ("a","b");
```

通用语法，according to official doc

```sql
--Single-table syntax:
UPDATE [LOW_PRIORITY] [IGNORE] table_reference
SET assignment_list
[WHERE where_condition]
[ORDER BY ...]
[LIMIT row_count]
value:
{expr | DEFAULT}
assignment:
col_name = value
assignment_list:
assignment [, assignment] ...
--Multiple-table syntax:
UPDATE [LOW_PRIORITY] [IGNORE] table_references
SET assignment_list
[WHERE where_condition]
```



###### 2.2.3.3 删除记录

和更新类似，也是基本的单表删除和多表删除

```sql
DELETE FROM tablename [WHERE CONDITION]

DELETE t1,t2,t3... FROM t1,t2,t3... [WHERE CONDITION] 
```

只是需要牢记，不加WHERE条件的话，会全量删除，GG

###### 2.2.3.4 查询记录

查询乃重中之重，但直接上代码和注释更直观，只是浏览的话，建议先看注释，想一想应该是什么，再去看命令语法

```sql
--全量查询
SELECT * FROM tablename [WHERE CONDITION]

--查询不重复
SELECT DISTINCT field1 FROM tablename [WHERE CONDITION]

--条件查询
--CONDITION部分，可以有各种比较运算符，也可以有逻辑运算符如and or

--排序和限制
SELECT * FROM tablename [WHERE CONDITION] [ORDER BY field1 [DESC/ASC], field2 [DESC/ASC]...] [LIMIT offset_start, row_count]
--默认是ASC
--多个字段排序，是按照先后顺序去排的，每个字段都可以拥有不同的排序顺序；若只有一个字段进行排序，遇到相同的记录，相同的记录将会无序排列
--如果只希望展示特定的部分的数据，可以使用limit
--offset_statr是偏移量，row_count是行数
--注意：limit是MySQL拓展SQL的语法，其他数据库可能不适用

--聚合
SELECT [field1,field2...] fun_name
FROM tablename
[WHERE CONDITION]
[GROUP BY field1,field2... [WITH ROLLUP]]
[HAVING HAVING_CONDITION]
--其中fun_name是要做的聚合操作，也就是聚合函数，常用的有sum,max,min,count
--with rollup是对分类聚合之后的结果再汇总
--比如说
SELECT dept_no,count(1) 
from employees.dept_emp 
group by dept_no with rollup;
--统计各部门员工总数，并汇总员工总数，结果如图

```

![image-20211208230058834](./资料/imgs/image-20211208230058834.png)

```sql
--HAVING关键字表示对分类后的结果再进行条件的过滤；
--having和where的区别在于，聚合前还是聚合后的过滤；尽量使用where，提高聚合的效率

--表连接
--大类上分为内连接和外连接；内连接仅选出两张表中互相匹配的记录，外连接会选出其他不匹配的记录
--外连接又细分为左连接和右连接；左或者右表示，选出左或者右表中不匹配的记录
select emp.emp_no,emp.dept_no from employees.dept_emp emp left join employees.departments dep on emp.dept_no = dep.dept_no;

select emp.emp_no,emp.dept_no from employees.dept_emp emp join employees.departments dep on emp.dept_no = dep.dept_no;

--子查询
select emp.emp_no from employees.employees emp where emp.emp_no in (select emp_no from employees.dept_emp)
--用于子查询的关键字主要包括in,not in,exists,not exists,=,!=
--在某些情况下，子查询可以转化为表连接，主要是两个方面的考虑：MySQL4.1之前不支持子查询，表连接在很多情况的性能是优于子查询的

--记录联合
--用于将两个表的数据按照一定条件查询之后，将结果合并显示的场景
select dept_no from employees.departments
union all
select dept_no from employees.dept_emp;
--union all是结果直接合并展示，union是去重之后的展示
```



## 遇到的问题些

### 1 插入表情时候java报错，SQLException，咋处理？

直接反应是去改对应表的字符集，utf8为utf8mb4；

alter table table_name character set 'utf8mb4';

然后验证发现失败...

疑惑了很久，使用了以下几种方式

- show variables like '%char%'查看当前数据库的字符设置，然后发现character_set_client和character_set_connection是utf8；怀疑这里有问题，尝试通过set names utf8mb4来改，发现不允许这个修改；
- 尝试调整java中jdbc的配置来，尝试后失败

经过咨询，通过show create table table_name ，发现每一字段都有Character set utf8描述，然后表的描述是character set utf8mb4；所以，应该是建表的时候，不只是指定了整张表的字符集，也指定了每一字段的字符集；

把字段的字符集调整后，问题得以解决；

alter table table_name change column_name column_name varchar(100) character set utf8mb4 collate utf8mb4_general_ci

根本原因是对mysql的字符集这块不熟悉，其实官方文档在字符集这块第一部分就描述了“在任何级别启用字符集和排序规则的规范。”[character general](####10.1 Character Sets and Collations in General)

### 2  MySQL 的转义

反斜线，MySQL uses C escape syntax in strings (for example, \n to represent the
newline character).



## 参考文献

1 



## TODO List

| 时间    | 内容                                |              |
| ------- | ----------------------------------- | ------------ |
| 2021-10 | Mac安装MySQL                        | 重要但不紧急 |
| 2021-10 | Linux安装MySQL                      | 不重要不紧急 |
| 2021-10 | logs的MySQL问题开始处理             | 不重要但紧急 |
| 2021-11 | 官方文档和深入浅出MySQL一起结合着看 |              |



## 总结

| 时间 | 内容 |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |

