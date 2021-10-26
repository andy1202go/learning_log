# Network

[toc]

## 路线

### 网络学习

《[计算机网络（第五版）](https://book.douban.com/subject/10510747/)》，这本中有很多细节。全书按照网络协议模型自下而上（物理层、数据链路层、介质访问控制层、网络层、传输层和应用层）有系统地介绍了计算机网络的基本原理，并结合 Internet 给出了大量的协议实例。

这本书还与时俱进地引入了最新的网络技术，包括无线网络、3G 蜂窝网络、RFID 与传感器网络、内容分发与 P2P 网络、流媒体传输与 IP 语音，以及延迟容忍网络等。另外，本书针对当前网络应用中日益突出的安全问题，用了一整章的篇幅对计算机网络的安全性进行了深入讨论，而且把相关内容与最新网络技术结合起来阐述。这本书读起来并不枯燥，因为其中有很多小故事和小段子。

两个网上的教程和讲义也可以让人入门。

- 渥汰华大学的一个课程讲义你也可以一看 [Computer Network Design](http://www.site.uottawa.ca/~shervin/courses/ceg4185/lectures/) 。
- GeeksforGeeks 上也有一个简单的 [Computer Network Tutorials](https://www.geeksforgeeks.org/computer-network-tutorials/) 。



### 网络调优

实用的可以操作的技术。

- 《Linux 的高级路由和流量控制 HowTo》（[Linux Advanced Routing & Traffic Control HOWTO](http://lartc.org/) ），这是一个非常容易上手的关于 iproute2、流量整形和一点 netfilter 的指南。
- 关于网络调优，你可以看一下这个文档 [Red Hat Enterprise Linux Network Performance Tuning Guide](https://access.redhat.com/sites/default/files/attachments/20150325_network_performance_tuning.pdf)。
- 还有一些网络工具能够帮上你的大忙，这里有一个网络工具的 Awesome 列表 [Awesome Pcap Tools](https://github.com/caesar0301/awesome-pcaptools) ，其中罗列了各种网络工具，能够让你更从容地调试网络相关的程序。
- [Making Linux TCP Fast](https://netdevconf.org/1.2/papers/bbr-netdev-1.2.new.new.pdf) ，一篇非常不错的 TCP 调优的论文。
- 下面是在 PackageCloud 上的两篇关于 Linux 网络栈相关的底层文章。
  - [Monitoring and Tuning the Linux Networking Stack: Receiving Data](https://blog.packagecloud.io/eng/2016/06/22/monitoring-tuning-linux-networking-stack-receiving-data/)
  - [Monitoring and Tuning the Linux Networking Stack: Sending Data](https://blog.packagecloud.io/eng/2017/02/06/monitoring-tuning-linux-networking-stack-sending-data/)



### 网络协议

读 RFC 有几个好处，一方面可以学习技术，另一方面，你可以通过 RFC 学习到一个好的技术文档是怎么写的，还能看到各种解决问题的方案和思路。

对于第 2 层链路层，需要了解一下 ARP：

- [RFC 826 - An Ethernet Address Resolution Protocol](https://tools.ietf.org/html/rfc826)

以及 Tunnel 相关的协议：

- [RFC 1853 - IP in IP Tunneling](https://tools.ietf.org/html/rfc1853)
- [RFC 2784 - Generic Routing Encapsulation (GRE)](https://tools.ietf.org/html/rfc2784)
- [RFC 2661 - Layer Two Tunneling Protocol “L2TP”](https://tools.ietf.org/html/rfc2661)
- [RFC 2637 - Point-to-Point Tunneling Protocol (PPTP)](https://tools.ietf.org/html/rfc2637)

对于第 4 层，最需要了解的是 TCP/IP 。《[TCP 的那些事儿（上）](https://coolshell.cn/articles/11564.html)》和《[TCP 的那些事儿（下）](https://coolshell.cn/articles/11609.html)》两篇文章。

- [RFC 793 - Transmission Control Protocol](https://tools.ietf.org/html/rfc793) - 最初的 TCP 标准定义，但不包括 TCP 相关细节。
- [RFC 813 - Window and Acknowledgement Strategy in TCP](https://tools.ietf.org/html/rfc813) - TCP 窗口与确认策略，并讨论了在使用该机制时可能遇到的问题及解决方法。
- [RFC 879 - The TCP Maximum Segment Size and Related Topics](https://tools.ietf.org/html/rfc879) - 讨论 MSS 参数对控制 TCP 分组大小的重要性，以及该参数与 IP 分段大小的关系等。
- [RFC 896 - Congestion Control in IP/TCP Internetworks](https://tools.ietf.org/html/rfc896) - 讨论拥塞问题和 TCP 如何控制拥塞。
- [RFC 2581 - TCP Congestion Control](https://tools.ietf.org/html/rfc2581) - 描述用于拥塞控制的四种机制：慢启动、拥塞防御、快重传和快恢复。后面这个 RFC 被 [RFC 5681](https://tools.ietf.org/html/rfc5681) 所更新。还有 [RFC 6582 - The NewReno Modification to TCP’s Fast Recovery Algorithm](https://tools.ietf.org/html/rfc6582) 中一个改进的快速恢复算法。
- [RFC 2018 - TCP Selective Acknowledgment Options](https://tools.ietf.org/html/rfc2018) - TCP 的选择确认。
- [RFC 2883 - An Extension to the Selective Acknowledgement (SACK) Option for TCP](https://tools.ietf.org/html/rfc2883) - 对于 RFC 2018 的改进。
- [RFC 2988 - Computing TCP’s Retransmission Timer](https://tools.ietf.org/html/rfc2988) - 讨论与 TCP 重传计时器设置相关的话题，重传计时器控制报文在重传前应等待多长时间。也就是经典的 TCP Karn/Partridge 重传算法。
- [RFC 6298 - Computing TCP’s Retransmission Timer](https://tools.ietf.org/html/rfc6298) - TCP Jacobson/Karels Algorithm 重传算法。

经典论文《[Congestion Avoidance and Control](http://ee.lbl.gov/papers/congavoid.pdf)》。

关于 Linux 下的 TCP 参数，[TCP 的 man page](http://man7.org/linux/man-pages/man7/tcp.7.html) 。

对于第 7 层协议，HTTP 协议是重点要学习的。

《[HTTP 权威指南](https://book.douban.com/subject/10746113/) 》，这本书有点厚，可以当参考书来看。这本书中没有提到 HTTP/2 的事，但是可以让你了解到 HTTP 协议的绝大多数特性。

HTTP 1.1 的原始 RFC 是 1999 年 6 月的 [RFC 2616](https://tools.ietf.org/html/rfc2616)，但其在 2014 后很快被下面这些 RFC 给取代了。

- [RFC 7230 - Hypertext Transfer Protocol (HTTP/1.1): Message Syntax and Routing](https://tools.ietf.org/html/rfc7230)
- [RFC 7231 - Hypertext Transfer Protocol (HTTP/1.1): Semantics and Content](https://tools.ietf.org/html/rfc7231)
- [RFC 7232 - Hypertext Transfer Protocol (HTTP/1.1): Conditional Requests](https://tools.ietf.org/html/rfc7232)
- [RFC 7233 - Hypertext Transfer Protocol (HTTP/1.1): Range Requests](https://tools.ietf.org/html/rfc7233)
- [RFC 7234 - Hypertext Transfer Protocol (HTTP/1.1): Caching](https://tools.ietf.org/html/rfc7234)
- [RFC 7235 - Hypertext Transfer Protocol (HTTP/1.1): Authentication](https://tools.ietf.org/html/rfc7235)

关于[HTTP/2](https://en.wikipedia.org/wiki/HTTP/2)，这是 HTTP 的一个比较新的协议，它于 2015 年被批准通过，现在基本上所有的主流浏览器都默认启用这个协议。所以，你有必要学习一下这个协议。下面是相关的学习资源。

- [Gitbook - HTTP/2 详解](https://legacy.gitbook.com/book/ye11ow/http2-explained/details)
- [http2 explained](http://daniel.haxx.se/http2/)（[中译版](https://www.gitbook.com/book/ye11ow/http2-explained/details)）
- [HTTP/2 for a Faster Web](https://cascadingmedia.com/insites/2015/03/http-2.html)
- [Nginx HTTP/2 白皮书](https://www.nginx.com/wp-content/uploads/2015/09/NGINX_HTTP2_White_Paper_v4.pdf)
- HTTP/2 的两个 RFC：
  - [RFC 7540 - Hypertext Transfer Protocol Version 2 (HTTP/2)](https://httpwg.org/specs/rfc7540.html) ，HTTP/2 的协议本身
  - [RFC 7541 - HPACK: Header Compression for HTTP/2](https://httpwg.org/specs/rfc7541.html) ，HTTP/2 的压缩算法

Wikipedia 的 [Internet Protocol Suite](https://en.wikipedia.org/wiki/Internet_protocol_suite) 上看看，这是一个很不错的网络协议的词条汇集地。

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



