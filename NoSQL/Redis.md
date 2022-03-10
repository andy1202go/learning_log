# Redis

[toc]

整体思路：官方文档+源码查看（大多数都是开源的）

### **数据结构数据库 Data structure Database - Redis**

- [Learn Redis the hard way (in production) at Trivago](http://tech.trivago.com/2017/01/25/learn-redis-the-hard-way-in-production/)
- [Twitter: How Twitter Uses Redis To Scale - 105TB RAM, 39MM QPS, 10,000+ Instances](http://highscalability.com/blog/2014/9/8/how-twitter-uses-redis-to-scale-105tb-ram-39mm-qps-10000-ins.html)
- [Slack: Scaling Slack’s Job Queue - Robustly Handling Billions of Tasks in Milliseconds Using Kafka and Redis](https://slack.engineering/scaling-slacks-job-queue-687222e9d100)
- [GitHub: Moving persistent data out of Redis at GitHub](https://githubengineering.com/moving-persistent-data-out-of-redis/)
- [Instagram: Storing Hundreds of Millions of Simple Key-Value Pairs in Redis](https://engineering.instagram.com/storing-hundreds-of-millions-of-simple-key-value-pairs-in-redis-1091ae80f74c)
- [Redis in Chat Architecture of Twitch (from 27:22)](https://www.infoq.com/presentations/twitch-pokemon)
- [Deliveroo: Optimizing Session Key Storage in Redis](https://deliveroo.engineering/2016/10/07/optimising-session-key-storage.html)
- [Deliveroo: Optimizing Redis Storage](https://deliveroo.engineering/2017/01/19/optimising-membership-queries.html)
- [GitHub: Awesome Redis](https://github.com/JamzyWang/awesome-redis)

## 常用命令

### MSET

**解释**

- 1.0.1版本即可使用
- O(N)时间复杂度
- 一次性设置多个k-v对
- 原子，不存在设置一半，一半没有设置
- 类似SET，会用新value替换掉原有value；如果不想替换已有值，使用MSETNX

**返回值**

OK，MSET不存在失败

**示例**

```shell
redis> MSET key1 "Hello" key2 "World"
"OK"
redis> GET key1
"Hello"
redis> GET key2
"World"
redis> 
```

**链接**

https://redis.io/commands/mset

## 可视化管理软件

官方的RedisInsighthttps://redis.com/redis-enterprise/redis-insight/#insight-form

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



