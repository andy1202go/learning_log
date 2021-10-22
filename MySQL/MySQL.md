# MySQL

[toc]

参考以左耳朵的MySQL路线来学习下

## 官方文档

### Functions and Operators

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

## 安装

### Linux





## SQL调优

### 案例

#### 1 order by [date] desc

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

## 小疑问

### 1 USING BTREE 的索引是b树还是b+树

b+树；

只有b+和hash两种索引；

b和b+的区别在于数据是不是完全在叶子节点，和有无横向的指针（我认为比较关键的两点）

https://dev.mysql.com/doc/refman/5.7/en/create-index.html#create-index-options

官方说明中，索引类型就是叫BTREE，所以只能说底层是B+（引擎版本有关？）





## 参考文献

1 



## TODO List

| 时间 | 内容 |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |



## 总结

| 时间 | 内容 |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |

