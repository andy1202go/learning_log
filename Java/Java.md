

# Java

[toc]

## 核心技术卷一

### 1 Java程序设计概述

### 2 Java程序设计环境

### 3 Java的基本程序设计结构

### 3.4　变量与常量 

#### 3.4.4　枚举类型

枚举类型，Enum抽象类的方法，是适用于enum关键字的类上面的。

比如：name()

> - #### name
>
>   ```
>   public final String name()
>   ```
>
>   返回此枚举常量的名称，与其枚举声明中声明的完全相同。 **大多数程序员应该使用[`toString()`](https://www.matools.com/file/manual/jdk_api_1.8_google/java/lang/Enum.html#toString--)方法，因为toString方法可能会返回一个更加用户友好的名称。** 该方法主要用于专门的情况，其中正确性取决于获得确切的名称，这从发布到发布不会有所不同。
>
>   - **结果**
>
>     这个枚举常数的名称

#### 3.6 字符串

##### 3.6.7 字符串API

Java中的String类包含了50多个方法，且绝大多数都很有用；

这里先重点关注indexOf()系列和lastIndexOf()系列

- 返回与字符串或代码点cp匹配的第一个/最后一个子串的开始位置
- 这个位置从索引0/原始串尾端或fromIndex开始计算
- 如果在原始串中不存在str，返回-1

实践下，然后看下底层搜索实现

先是数据准备

![image-20210921121047731](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/api/image-20210921121047731.png)

然后是test主体

![image-20210921121605464](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/api/image-20210921121605464.png)

运行看下

![image-20210921121117429](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/api/image-20210921121117429.png)

简单得出以下结论

- 不管是indexOf还是lastIndexOf，都是返回找到的字符串的串首位置
- 找不到都是返回-1

更复杂一点的使用，是指定起始位置，暂时没有想试一下的想法；

看下源码，indexOf()是走到String类的indexOf(str,fromIndex)这里，具体实现为

![image-20210921122309760](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/api/image-20210921122309760.png)

这里的source和target是被搜索字段和搜索字符串的字符数组，也透露了String的本质，就是char[]

代码的组织结构上，基本上是边界处理+数据准备+搜索实现三部分

![image-20210921122533501](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/api/image-20210921122533501.png)

这里我比较关心的一点的是为啥当targetCount为0，返回是fromIndex啊？？一脸问号，等下试试

具体来看核心算法；

通读下来，基本思路很清晰，就是从fromIndex找起，先找到和target开头相同的，然后暂停i，使用j,k分别指向source和target的元素，进行比较，若长度内都是相同的，则返回，否则继续i的遍历直到找到或结束；

比较值得仔细看下的是Look for first character这里的if和while的组合，以及下面的for循环了；两个地方都是利用循环加速遍历，没有具体处理逻辑，只是符合条件，指针就向后指了，很屌。

其中，第一段，先判断个if，是为了进入循环，并且使用++i来实现循环。

lastIndexOf的话

![image-20210921123246211](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/api/image-20210921123246211.png)

其实代码组织类似，三段式；

算法实现的思路其实也类似，只是从尾巴找起，且遍历去看每个字符是否和target的最后一个字符相同；

只是这里使用了goto的实现来简化整个逻辑的实现；

两者摆在一起看下，实现逻辑和思路真的差不多，但各种细节看的着实有点要疯..

![image-20210921123552725](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/api/image-20210921123552725.png)

###### 所以，从这里的源码可以学习到的是

- api的层层封装：暴露出去的api，可以提供丰富的能力，比如indexOf就有三个不同的api；但底层能力是相同的，封装而已；
- 清晰的注释；（老生常谈）
- 方法中的代码组织形式：边界处理/参数校验+参数准备+算法实现
- 风骚的指针控制：while和for，其实允许不写大括号的实现的，使用在指针挪动即可；

最后，我们再来看下，如果target是空字符串会怎样

![image-20210921124208422](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/api/image-20210921124208422.png)

所以，使用indexOf系列的时候，一定不要搜索空字符串或者空对象，会得到预期之外的情况，诡异的bug这样子。

### 4 对象与类

### 5 继承

### 6 接口与内部类

#### 6.5 代理

### 11 异常、断言、日志和调试

#### 11.5 记录日志

日志部分，总共有书本介绍JUL部分，实践，日志体系历史部分，日志体系的不同实践理解部分，最佳实践，桥接包原理部分，日志门面和日志标准部分；

总共耗时0.5+0.5+1+2+1+1.5+2 8.5小时，要看下怎么分配时间来彻底搞懂下

其中第一部分，书籍，晚上看书加打字搞定

实践部分放周末或晚上搞定下

理论部分，可以安排在路上来，不过找资料可以平时找好

##### 11.5.1 java日志体系的历史

大体上参考[理清，五花八门的Java日志](https://mp.weixin.qq.com/s/5IV_i_NHGatdhV_XEfCeAQ)就行；

总结下来是：

- 最开始只有System.out和System.err，会产生大量IO，输出的日志不能保存到文件，只能打印在控制台；无法定制，也无法控制日志是否需要输出；
- 巨佬Ceki出手，Log4j诞生，但Java的母公司Sun拒绝把Log4j引入Java的标准库中
- Java推出自己的Java Util Logging，即JUL
- 为了兼容两种日志标准库，抽象接口，得到JCL，JCL有其默认实现Simple Log，但JCL问题多多
- 巨佬再出手，Slf4j诞生，随之而来的是Slf4j的默认实现Logback，以及Slf4j和其他各种api或日志标准的桥接包
- 抄袭的Log4j2诞生，包含log4j-api和log4j-core，而且也有各种桥接包
- 最终，两点需要知道
  1. java日志体系分为门面和具体实现
  2. 分为巨佬的Slf4j-Logback以及Apache两大阵营

文章算是整体梳理了混乱的java日志体系，但其中关于桥接包部分我没有很好的理解（但是最后解决问题的例子还是很好理解的，总之遇到日志问题，可以画图来分析解决），尤其是在https://time.geekbang.org/column/article/220307类似文章中看到的下面这张图，才是各种奇怪

![img](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/imgs/97fcd8b55e5288c0e9954f070f1008fe.png)

从上图看下来，感觉是从api桥接到slf4j再适配具体实现的情况，但实际上，如果你把从上到下的某条线都依赖到项目中的话，有一定几率是会Stack Overflow的；

究其原因，是调用链导致一定概率形成死循环，而如何避免，是要从代码实现角度来的。具体可以参考文章：详解log4j-over-slf4j与slf4j-log4j12共存stack overflow异常分析

而且上文还解答了我对于上面那张图片的疑问，实际上在slf4j[官方文档](http://www.slf4j.org/legacy.html)中，关于桥接，有这么两张图

[![click to enlarge](http://www.slf4j.org/images/concrete-bindings.png)](http://www.slf4j.org/images/concrete-bindings.png)

[![click to enlarge](http://www.slf4j.org/images/legacy.png)](http://www.slf4j.org/images/legacy.png)

里面相似说明了应用调用slf4jAPi适配具体实现的几种情况，和桥接到其他api的情况；

也就是说，之前的一幅图，是两种情况的结合..........

##### 11.5.2 JUL



##### 11.5.3 Slf4j+Logback的最佳实践（？）

##### 11.5.4 桥接原理

https://blog.csdn.net/jpf254/article/details/80757041这篇文章梳理的比较清晰了

> ## 总结
>
> 1. 建议在应用中使用日志门面API打印日志,日后可以通过调整桥接器和日志框架实现方便的修改日志实现
> 2. slf4j在获取日志框架实现时扫描class path,寻找`org.slf4j.impl.StaticLoggerBinder`,桥接器就是通过提供该类实现桥接功能

- [ ] 结合代码来梳理看下

## 核心技术卷二

### 9 安全

#### 9.1 类加载器

## 参考文献

1 [理清，五花八门的Java日志](https://mp.weixin.qq.com/s/5IV_i_NHGatdhV_XEfCeAQ)



## TODO List

| 时间       | 内容                                                         |      |
| ---------- | ------------------------------------------------------------ | ---- |
| 2021-09-21 | indexOf中，为啥当targetCount为0，返回是fromIndex啊？？       |      |
| 10-10      | java日志体系，使用了门面设计模式                             |      |
| 2022-02-14 | 把java的api找到搞下来，https://pan.baidu.com/disk/main?from=homeSave#/index?category=all&path=%2F |      |



## 总结

| 时间 | 内容                                     |      |
| ---- | ---------------------------------------- | ---- |
|      | 搞不清楚的东西，要么代码，要么第一手资料 |      |
|      |                                          |      |
|      |                                          |      |



