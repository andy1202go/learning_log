# Mybatis

[toc]

参考官方文档：https://mybatis.org/mybatis-3/sqlmap-xml.html

## Mapper XML Files

### Result Maps

#### construct

对于比较复杂的resultMap，可以通过元素<constructor>来表示result的构造器结构；

对于有参构造的参数顺序问题，

> When you are dealing with a constructor with many parameters, maintaining the order of arg elements is error-prone.
> Since 3.4.3, by specifying the name of each parameter, you can write arg elements in any order. To reference constructor parameters by their names, you can either add `@Param` annotation to them or compile the project with '-parameters' compiler option and enable `useActualParamName` (this option is enabled by default). The following example is valid for the same constructor even though the order of the second and the third parameters does not match with the declared order.

碰到的一个实例是，对象打了注解@AllArgsConstructor，然后xml配置了type，结果报错

```java
org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException:
Error querying database. Cause: java.lang.IndexOutOfBoundsException: Index: 3, Size: 3
```

原因就是返回的结果是2个，但是构造需要三个参数导致的。

参考：https://blog.csdn.net/qaqpbp/article/details/115324998































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



