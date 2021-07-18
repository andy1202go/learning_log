# Git学习笔记

### 				——基于极客时间

[toc]

## 1 基本篇

### 1.1 综述

git是个版本控制系统，文件管理工具；

版本控制系统的历史：

- 从无到有，手动拷贝；
- 集中式；svn；客户端必须时刻与服务端相连接
- 分布式；服务端和客户端都有完整的版本库，所以版本管理效率更高；

git诞生的原因，是Linux老师一直使用的版本控制系统，不再免费使用了，所以林老师就决定自己写一个来自己用。所以git也是一个非常牛逼的文件管理工具；

优点

- 最优的存储性能
- 非凡的性能
- 开源
- 容易做备份
- 容易定制工作流程
- 支持离线操作

优点多，用的人也多。

基于git，诞生了github和gitlab等

### 1.2 安装

我用的mac，所以就关注mac了。

Windows上面都是直接使用的git bash集成工具来的。

mac上面，现在知道的有三种方式

- 按照视频所述直接下载安装包
- 安装git官网对mac系统的提示安装，通过brew
- m1系列的Mac，好像是都自带了Git的

验证安装成功，就输入git --version，查看安装的版本即可；

如果是Apple自带的，会显示版本和Apple Git字样的；

### 1.3 使用Git之前需要做的最小配置

- user.name和user.email的配置——要保留每次变更的操作人信息
- 范围，global，local（默认）

### 1.4 本地建仓并设置local用户信息

```
git init project_name
```

在目录下配置local的用户信息

注意：local和global都对当前目录生效的时候，local优先级更高

![WechatIMG5](./imgs/WechatIMG5.jpeg)

### 1.5 工作区和暂存区

### 1.6 给文件重命名

```
git mv old_file new_file
```

### 1.7 git log查看版本演变历史

### 1.8 gitk 通过图形界面来查看版本历史

所以各种工具中集成的图形化的git，是不是都是基于这个来的？嗯，感觉是基于git log来的，后面有几乎可以验证下。

注意作者和提交人是不一定同一个的。因为提交人可能是对其他人的提交进行处理的。版权严谨。

### 1.9 .git目录

![git目录](./imgs/git目录.jpg)

实现本地管理能力
ls -al
HEAD 文件，是个引用，指向当前分支
config 文件，存储了配置信息，比如之前配置的密码啥的
refs文件夹 有heads(分支)和tags(里程碑)，存的是每次commit。通过git cat-file -t来看文件属性。关于hash值，能区分开的时候，git会截断展示
objects tree类型 git cat-file -p 看内容 存储blob
git的核心对象是commit tree blob
实践证明，add到暂存区，就会在objects中创建文件了



下图展示了HEAD和refs文件夹的关系；HEAD指向refs/head的当前分支/commit（通过类型可以看出是commit类型）

![git目录HEAD探索](./imgs/git目录HEAD探索.jpg)

![refsHead和branch](/Users/liangbo/Desktop/Projects/Git/learning_log/Git_three_piece_suite/imgs/refsHead和branch.jpg)

config文件保存了当前仓库的配置信息

![config](./imgs/config.jpg)

还有一个很重要的文件夹是objects;

进去看到的object是内部的文件名和外部的短的文件夹名称一起组成的objectName，这点需要注意

![objects](./imgs/objects.jpg)

对于object来说，有commit、tree、blob几种；是git的核心对象

在objects文件夹中，如果之前commit有文件夹，则有的object就是tree，tree中的内容就是文件夹中的内容；而各种实际的文件，都是在叶子节点上的，且是blob类型；

设计成这样子，考虑的话，感觉一方面易于管理文件，一方面对于相同的内容（注意是内容），其blob都是一样的（节省空间啥的）

### 1.10 commit tree blob的关系

![三种object关系图](/Users/liangbo/Desktop/Projects/Git/learning_log/Git_three_piece_suite/imgs/三种object关系图.jpg)

commit中存储当前提交的各种信息，数据则存储为一棵树

![commit内容](./imgs/commit内容.jpg)

树和blob上面聊过了，图示也比较清晰了

### 1.12 分离头指针

git checkout 某个commitId的时候，会出现

理论上在尝试修改的情况下，可以这么玩；

所谓分离头指针，是指HEAD不是指向某个分支，而是直接指向某个commit了；这个可以通过不同情况下的git log来验证（HEAD -> master和HEAD）；此时的变更没有基于某个branch去做

在分离头指针的情况下，也可以提交commit啥的，但是实际上不会出现在历史树上；如果在切换分支前没有把这个commit跟某个分支或tag绑定，则git会认为这种commit是不重要的；

### 1.13 进一步理解HEAD和branch

不管HEAD指向branch还是commit，最后都是指向某个commit；

所以是头**指针**

在很多命令的时候，都可以直接用HEAD替代

## 2 独自使用Git时的常见场景

### 2.14 怎么删除不需要的分支

```
git branch -d
git branch -D
```

### 2.15 修改最新的commit的message

```
git commit --amend
```

### 2.16 修改历史的信息

```
git rebase -i commitID
```

Rebase命令，-i表示交互界面，在交互界面可以做许多事情，通过下面的注释来进行选择了；比如reward就是修改message的，所以对于需要修改的commit，修改为reward，然后再进行交互即可；

> 目前这里使用这个，去修改历史commit的message
> git rebase -i commitId
> 弹出交互页面，根据命令提示，使用reward
> 实际实现，从最后git的响应来看，是分离头指针后，执行命令后，重新提交了，blob是没有变化的
> 对于本地可以这么玩

![rebase-reward](./imgs/rebase-reward.jpg)

![rebase过程](./imgs/rebase过程.jpg)

### 2.17 合并连续commit

还是用rebase，只是这次使用squash命令

![合并commit](./imgs/合并commit.jpg)

### 2.18 合并不连续commit

还是rebase，只要自己认为不冲突，就可以调整commit的前后顺序；

### 2.19 比较暂存区和HEAD

```
git diff --cached
```



## 3 Git与GitHub的简单同步

### 3.30 注册GitHub账号

### 3.31 配置公私钥

因为需要通过ssh连接Git使用，所以需要这个东西；

``` 
ls -al ~/.ssh
```

通过这个命令先看下有没有rsa文件，即是否生成过秘钥了；

然后可以通过

```
ssh-keygen -t rsa -C "email-address"
```

来生成秘钥；

将.pub文件内容复制在git网站-设置-ssh-新建ssh中即可完成设置；

当然以上步骤其实通过官方帮助文档来的更靠谱；

官方help文档，在git网站-头像-Help中，切换简体中文，美滋滋。

### 3.32 在GitHub上创建个人仓库

强烈建议创建仓库的时候顺带创建README文件，因为搜索仓库的时候，会在README文件搜索关键字。

注意License的运用，对于以后的引用输出的时候，这块需要谨慎，有些license是不允许引用啥的的。

### 3.33 把本地仓库同步到GitHub

## 4 Git多人单分支集成协作时的常见场景

### 4.34

## 5 Git集成使用禁忌

### 5.39

## 6 初识GitHub

### 6.41

## 7 使用GitHub进行团队协作

### 7.47

## 8 Gitlab实践

### 8.57

## 其他独立问题

1. git命令中，有选项，选项中，什么时候用-，什么时候用--？
   - 参考知乎问题https://www.zhihu.com/question/41366215
   - 总结一下
     - 通常，-简写，--全拼
     - 风格问题/历史原因，UNIX，GNU等习惯不同而延续下来的



