# commons-lang

[toc]

>  [apache](https://so.csdn.net/so/search?q=apache&spm=1001.2101.3001.7020)的commons系列包，是一系列非常通用的公共组件包，Spring,Hibernate等众多第三方框架都依赖于这些基础的组件包，而commons-lang则是基础中的基础，是这系列基础包中的基础包。

| Package                                                      | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [org.apache.commons.lang3](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/package-summary.html) | Provides highly reusable static utility methods, chiefly concerned with adding value to the [`java.lang`](https://docs.oracle.com/javase/7/docs/api/java/lang/package-summary.html?is-external=true) classes. |
| [org.apache.commons.lang3.arch](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/arch/package-summary.html) | Provides classes to work with the values of the os.arch system property. |
| [org.apache.commons.lang3.builder](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/builder/package-summary.html) | Assists in creating consistent `equals(Object)`, `toString()`, `hashCode()`, and `compareTo(Object)` methods. |
| [org.apache.commons.lang3.compare](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/compare/package-summary.html) | Provides classes to work with the [`Comparable`](https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html?is-external=true) and [`Comparator`](https://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html?is-external=true) interfaces. |
| [org.apache.commons.lang3.concurrent](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/concurrent/package-summary.html) | Provides support classes for multi-threaded programming.     |
| [org.apache.commons.lang3.concurrent.locks](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/concurrent/locks/package-summary.html) | Provides support classes for multi-threaded programming.     |
| [org.apache.commons.lang3.event](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/event/package-summary.html) | Provides some useful event-based utilities.                  |
| [org.apache.commons.lang3.exception](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/exception/package-summary.html) | Provides functionality for Exceptions.                       |
| [org.apache.commons.lang3.function](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/function/package-summary.html) | Provides functional interfaces to complement those in `java.lang.function` and utilities for working with Java 8 lambdas. |
| [org.apache.commons.lang3.math](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/math/package-summary.html) | Extends [`java.math`](https://docs.oracle.com/javase/7/docs/api/java/math/package-summary.html?is-external=true) for business mathematical classes. |
| [org.apache.commons.lang3.mutable](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/mutable/package-summary.html) | Provides typed mutable wrappers to primitive values and Object. |
| [org.apache.commons.lang3.reflect](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/reflect/package-summary.html) | Accumulates common high-level uses of the `java.lang.reflect` APIs. |
| [org.apache.commons.lang3.stream](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/stream/package-summary.html) | Provides utility classes to complement those in `java.util.stream`. |
| [org.apache.commons.lang3.text](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/text/package-summary.html) | Provides classes for handling and manipulating text, partly as an extension to [`java.text`](https://docs.oracle.com/javase/7/docs/api/java/text/package-summary.html?is-external=true). |
| [org.apache.commons.lang3.text.translate](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/text/translate/package-summary.html) | An API for creating text translation routines from a set of smaller building blocks. |
| [org.apache.commons.lang3.time](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/time/package-summary.html) | Provides classes and methods to work with dates and durations. |
| [org.apache.commons.lang3.tuple](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/tuple/package-summary.html) | Tuple classes, starting with a Pair class in version 3.0.    |

## [org.apache.commons.lang3](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/package-summary.html)

### StringUtils

#### join

join一系列方法都是把东西拼接在一起的，只是参数不同；

- 可以join各种数组，各种实现了Interface Iterator<E>的内容；

- 可以指定separator
- 可以指定起始和终点（exclusive，也就是不包含）
- joinWith方法，是先指定separator，然后指定Object...，所以可以连接不同类型的对象

```java
    private static void joinTest() {
        log.info("null situation is :" + "\n" + StringUtils.join(null));
        log.info("with index is like :" + "\n" + StringUtils.join(NORMAL_LIST, ":", 0, 3));
        log.info("join with null separator:" + "\n" + StringUtils.join(NORMAL_LIST, null));
        log.info("join with null separator function:" + "\n" + StringUtils.join(NORMAL_LIST));
        log.info("join with joinWith" + "\n" + StringUtils.joinWith(";", NORMAL_LIST, FILE_NAME));
    }

三月 03, 2022 3:35:24 下午 org.shithappens.libs.apache.commons.lang3.StringUtilsTest joinTest
信息: null situation is :
null
三月 03, 2022 3:35:24 下午 org.shithappens.libs.apache.commons.lang3.StringUtilsTest joinTest
信息: with index is like :
123b:abc:long sentence........................
三月 03, 2022 3:35:24 下午 org.shithappens.libs.apache.commons.lang3.StringUtilsTest joinTest
信息: join with null separator:
123babclong sentence........................@@@"well"
三月 03, 2022 3:35:24 下午 org.shithappens.libs.apache.commons.lang3.StringUtilsTest joinTest
信息: join with null separator function:
[123b, abc, long sentence........................, @@@, "well"]
三月 03, 2022 3:35:24 下午 org.shithappens.libs.apache.commons.lang3.StringUtilsTest joinTest
信息: join with joinWith
[123b, abc, long sentence........................, @@@, "well"];test.file.mp4
```

#### joinWith

见join

## 参考文献

1 https://commons.apache.org/proper/commons-lang/



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



