# IDEA常用内容

[toc]

## 1 常用插件

### 1.1 阿里代码规范

Alibaba Java Coding Guidelines plugin support.

### 1.2 Maven管理

Maven helper

### 1.3 code glance

代码浏览

### 1.4 lombok

好像社区版用不了

### 1.5 自动注释

easy-javadoc https://developer.aliyun.com/article/753659

### 1.6  LeetCode Editor

参考：https://www.yzlfxy.com/jiaocheng/java/366790.html

- 搜索插件安装
- 配置用户名等
- 选择题目作答
- 右键LeetCode部分进行run或submit等

### 1.7 Rainbow Brackets

### 1.8 Free Mybatis Plugin

### 1.9  String Manipulation

### 1.10 PlantUML

实际上只是plantUML推出的idea中的插件，本身的东西挺amazing的https://plantuml.com/zh/

画用例图、时序图、简单的流程图的首选！！！

### 1.11 Code Review Helper

https://github.com/veezean/IntellijIDEA-CodeReview-Plugin

### 1.12 SequenceDiagram

快速生成时序图的插件

https://blog.csdn.net/u013978512/article/details/109112391?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-2-109112391.pc_agg_new_rank&utm_term=idea%E8%B0%83%E7%94%A8%E9%93%BE%E8%B7%AF&spm=1000.2123.3001.4430

默认的，可以通过Call Hierarchy看到方法的调用链的树状描述，但遗憾的是没有图

## 2 常用配置

### 2.1 Terminal中空格间距过大的问题

一方面在font相关的地方选用Show only monospaced font；https://blog.csdn.net/qq_38366946/article/details/88977211

一方面在Editor-->Color Scheme-->Console Font中调整terminal情况

## 3 常用工具

### 3.1 使用diagram浏览代码结构

以facade或controller为入口，一点点加入class，形成完备的diagram；

https://www.jianshu.com/p/e7fdb2fa3f08

基于图，了解接口的功能和调用链路；

同时还能跳转到代码上去（双击后从某个地方jump to source）。沉淀为文档；

关于几种图形的layout，目前使用分层的要多一些。定义和适用场景参考https://yed.yworks.com/support/manual/layout_orthogonal_compact.html