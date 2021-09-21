

# MapStruct

[toc]

## 1 基础概念

### 1.1 What is it

> MapStruct is a code generator that greatly simplifies the implementation of mappings between Java bean types based on a convention over configuration approach.
>
> The generated mapping code uses plain method invocations and thus is fast, type-safe and easy to understand.
>
> MapStruct 是一个代码生成器，它基于约定优于配置方法极大地简化了 Java bean 类型之间映射的实现。
>
> 生成的映射代码使用简单的方法调用，因此速度快、类型安全且易于理解。

通俗的来说，就是优雅的做类型转换这件事，比BeanUtils要繁琐一点，但比其性能更高；学习成本也不高

### 1.2 Why should we use it

> Multi-layered applications often require to map between different object models (e.g. entities and DTOs). Writing such mapping code is a tedious and error-prone task. MapStruct aims at simplifying this work by automating it as much as possible.
>
> In contrast to other mapping frameworks MapStruct generates bean mappings at compile-time which ensures a high performance, allows for fast developer feedback and thorough error checking.
>
> 多层应用程序通常需要在不同的对象模型（例如实体和 DTO）之间进行映射。编写这样的映射代码是一项乏味且容易出错的任务。 MapStruct 旨在通过尽可能自动化来简化这项工作。
>
> 与其他映射框架相比，MapStruct 在编译时生成 bean 映射，以确保高性能，允许快速的开发人员反馈和彻底的错误检查。

### 1.3 How to use

参考官方文档即可，整体还是比较完备的；https://mapstruct.org/documentation/installation/

## 2 Demo

### 2.1 Get Started

**以下代码基于1.4.2.Final版本**

参考官方文档的Car的实例即可，总结为下列几点基本使用约定

- 多个类
- 转换关系建立Mapper接口
- @Mapper注解
- 通过GetMapper获取实例

![image-20210912113820979](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/MapStruct/image-20210912113820979.png)

### 2.2 高阶使用

上述情况仅仅适用于最简单的场景，即source和target的参数完全一样；

直接参考文档即可，但是要大概知道有哪些场景

其他场景包括但不限于

- 多个参数转换为一个
- 将嵌套的 bean 属性映射到当前目标
- 更新bean
- 从map转为某个bean（toBean)
- 自定义转换逻辑
- source中的java表达式（expression("java(new Date())")）

等等

总之一句话：所有涉及类转换的东西，它基本上都能做！！！

#### 2.2.1 更多实际使用Case

## 3 原理

从代码执行情况来看，流程上是

- 编写Mapper代码
- Compile阶段生成MapperImpl代码
- 运行阶段，通过getClass，拿到约定的Mapper加Impl后缀代码的Class
- 调用对应代码完成

所以，关键是中间的两步；

### 3.1 运行

先来看简单的执行阶段；

关键是上面那个getMapper()方法，如何获取到的Mapper实现类；

点进去我们看到实际上总共有两步：

![image-20210912115244955](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/MapStruct/image-20210912115244955.png)

- 获取classLoaders
- 获取Mapper

分别来看

#### 3.1.1 获取classLoaders

![image-20210912115321837](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/MapStruct/image-20210912115321837.png)

很清晰，把当前类和当前线程的Classloader都加入Classloader的List中；

不过，为什么这么做呢？？

挖个坑吧

#### 3.1.2 getMapper

![image-20210912120130627](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/MapStruct/image-20210912120130627.png)

通过mapper类名+约定后缀Impl获取即可

### 3.2 编译

在文档的第二部分其实说明的很清楚

> MapStruct is a Java annotation processor based on [JSR 269](http://www.jcp.org/en/jsr/detail?id=269) and as such can be used within command line builds (javac, Ant, Maven etc.) as well as from within your IDE.
>
> It comprises the following artifacts:
>
> - *org.mapstruct:mapstruct*: contains the required annotations such as `@Mapping`
> - *org.mapstruct:mapstruct-processor*: contains the annotation processor which generates mapper implementations
>
> MapStruct 是一个基于 JSR 269 的 Java 注释处理器，因此可以在命令行构建（javac、Ant、Maven 等）以及您的 IDE 中使用。
>
> 它包括以下工件：
>
> org.mapstruct:mapstruct: 包含需要的注解，如@Mapping
>
> org.mapstruct:mapstruct-processor：包含生成映射器实现的注释处理器

再回到get started部分的依赖来看

![image-20210912121045146](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/MapStruct/image-20210912121045146.png)

所需的@Mapper注解在mapstruct包中，处理Mapper注解的类的方法在mapstruct-processor中；

#### 3.2.1 JSR 269

JSR 269给java提供了插件化注解处理能力；

按照约定去自定义Annotation和Processor就可以在compile去动态生成代码；

更多情况参考https://www.cnblogs.com/throwable/p/9139908.html

#### 3.2.2 MappingProcessor

按照JSR269的要求，需要满足以下几点：

> - 1、自定义一个Annotation Processor，需要继承`javax.annotation.processing.AbstractProcessor`，并覆写process方法。
> - 2、自定义一个注解，注解的元注解需要指定`@Retention(RetentionPolicy.SOURCE)`。
> - 3、需要在声明的自定义Annotation Processor中使用`javax.annotation.processing.SupportedAnnotationTypes`指定在第2步创建的注解类型的名称(注意需要全类名，"包名.注解类型名称"，否则会不生效)。
> - 4、需要在声明的自定义Annotation Processor中使用`javax.annotation.processing.SupportedSourceVersion`指定编译版本。
> - 5、可选操作，可以通在声明的自定义Annotation Processor中使用`javax.annotation.processing.SupportedOptions`指定编译参数。

按照这个逻辑，再按照之前文档描述的process能力所在，在其中找到MappingProcessor这个类，满足了269的要求

![image-20210912154006869](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/MapStruct/image-20210912154006869.png)

这也是为什么没有在包中搜索到对@Mapper的处理类；

对于整个生成代码的阶段，就比较繁琐和复杂了，暂无细看的必要了；

#### 3.2.3 SPI的使用

但是从整个processor包的结构来看，直观的就能看到有一个spi包，仔细看，会发现在很多处理中都使用了SPI机制；

![image-20210912154432319](/Users/liangbo/Desktop/Projects/Git/learning_log/Java/Lib/MapStruct/image-20210912154432319.png)

这种和java约定深度耦合，也正是这个包的目的——为java的对象转换提供比较优雅的方法；

## 总结

| 时间       | 内容                                           |      |
| ---------- | ---------------------------------------------- | ---- |
| 2021-09-12 | 做的事情比较单纯，但做到了非常优雅了           |      |
|            | 目的明确，充分利用了java的机制，而不是再造轮子 |      |
|            | 每个阶段的实现都有可学习和深究的地方           |      |



## 参考链接

1 官网地址：https://mapstruct.org

2 插件化注解处理能力：https://www.cnblogs.com/throwable/p/9139908.html

3 mapStruct源码分析：https://juejin.cn/post/6844904199755415559

## TODO

| 时间       | 内容                                                         |      |
| ---------- | ------------------------------------------------------------ | ---- |
| 2021-09-12 | collectClassLoaders中，加载了clazz本身的，和当前线程的，且有先后顺序，为什么要这么做 |      |
|            | JSR 269学习使用：插件化注解处理API                           |      |
|            |                                                              |      |

