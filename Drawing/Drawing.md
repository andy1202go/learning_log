# Drawing

[toc]

## 1 UML图形绘制

### 1.1 PlantUML

官网：https://plantuml.com/zh/

> **PlantUML**是一个开源项目，支持快速绘制：
>
> 
>
> - [时序图](https://plantuml.com/zh/sequence-diagram)
> - [用例图](https://plantuml.com/zh/use-case-diagram)
> - [类图](https://plantuml.com/zh/class-diagram)
> - [对象图](https://plantuml.com/zh/object-diagram)
> - [活动图](https://plantuml.com/zh/activity-diagram-beta) ([旧版语法](https://plantuml.com/zh/activity-diagram-legacy)在此处)
> - [组件图](https://plantuml.com/zh/component-diagram)
> - [部署图](https://plantuml.com/zh/deployment-diagram)
> - [状态图](https://plantuml.com/zh/state-diagram)
> - [定时图](https://plantuml.com/zh/timing-diagram)
>
> 
>
> 同时还支持以下非UML图：
>
> 
>
> - [JSON 数据](https://plantuml.com/zh/json)
> - [YAML 数据](https://plantuml.com/zh/yaml)
> - [网络图 (nwdiag)](https://plantuml.com/zh/nwdiag)
> - [线框图形界面](https://plantuml.com/zh/salt)
> - [架构图](https://plantuml.com/zh/archimate-diagram)
> - [规范和描述语言 (SDL)](https://plantuml.com/zh/activity-diagram-beta#sdl)
> - [Ditaa 图](https://plantuml.com/zh/ditaa)
> - [甘特图](https://plantuml.com/zh/gantt-diagram)
> - [思维导图](https://plantuml.com/zh/mindmap-diagram)
> - [WBS 工作分解图](https://plantuml.com/zh/wbs-diagram)
> - [以 AsciiMath 或 JLaTeXMath 符号的数学公式](https://plantuml.com/zh/ascii-math)
> - [实体关系图](https://plantuml.com/zh/ie-diagram)

亲身实践后发现画时序图、用例图、简单的流程图很爽！

三大实用好处：代码绘制，链接浏览，idea插件

#### 1.1.1 idea插件

插件搜索PlantUML integration，然后需要安装Graphviz（用于渲染）;

整个流程可以参考https://www.wiz.cn/wapp/folder/018ae9c4-266a-4f27-8a49-9ef68bb5e895?c=%2FMy%20Notes%2F&docGuid=4ab42cc5-b28c-4001-b371-1cf7f54d7976

使用方法的话，就是右键创建PlantUML文件即可，选择图形类型（其实都无所谓，只认代码）

#### 1.1.2 代码绘制

示例如下：时序图，用例图，流程图

``` uml
@startuml
title TestSequence

actor User as user
participant "User Agent" as ua
participant "Backend" as back

autonumber

user->ua:访问客户端
activate ua

ua->back:客户端访问后端
activate back

back-->ua:后端响应
deactivate back

ua-->user:客户端响应
deactivate ua

@enduml
```

``` uml
@startuml
left to right direction
actor Guest as g
package Professional {
    actor Chief as c
    actor "Food Critic" as fc
}
package Restaurant {
    usecase "Eat Food" as uc1
    usecase "Pay For Food" as uc2
    usecase "Drink" as uc3
    usecase "Review" as uc4
}
g--> uc1
g--> uc2
g--> uc3
fc--> uc4
@enduml
```

```uml
@startuml
title 流程图test
start
:第一个环境;
if(a)then(shit happens)
    :第一个环境;
    endif
:要结束了;
stop
@enduml

@startuml
(*)  --> "check input"
If "verbose" then
--> [Yes] "turn on verbosity"
--> "run command"
else
--> "run command"
Endif
-->(*)
@enduml
```

#### 1.1.3 链接浏览

图片右键可以得到链接，直接可以在这个地址编辑生成

http://www.plantuml.com/plantuml/uml/SoWkIImgAStDuIh9BCb9LNYsjV7vYkwdi_ULf1QNS84bkQoUhrdpP4V3anshdlUjVxgesPehCqqZKRCa8pLFepWZiqGX8x6W83MlM9FBwkc-wKyxDZoRjk7vh1PheFRy0gxKl9JCD0ZJ0SW6MWK0

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

