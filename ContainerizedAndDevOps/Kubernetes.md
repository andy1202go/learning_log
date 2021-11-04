# K8S

[toc]

## 路线

Kubernetes 是 Google 开源的容器集群管理系统，是 Google 多年大规模容器管理技术 Borg 的开源版本，也是 CNCF 最重要的项目之一，主要功能包括：

- 基于容器的应用部署、维护和滚动升级；
- 负载均衡和服务发现；
- 跨机器和跨地区的集群调度；
- 自动伸缩；
- 无状态服务和有状态服务；
- 广泛的 Volume 支持；
- 插件机制保证扩展性。

Kubernetes 发展非常迅速，已经成为容器编排领域的领导者。

阅读 Kubernetes 前世今生的一篇论文。

- [Borg, Omega, and Kubernetes](https://static.googleusercontent.com/media/research.google.com/zh-CN//pubs/archive/44843.pdf) ，看看 Google 这十几年来从这三个容器管理系统中得到的经验教训。

学习 Kubernetes，有两个免费的开源电子书。

- 《[Kubernetes Handbook](https://jimmysong.io/kubernetes-handbook/)》，这本书记录了作者从零开始学习和使用 Kubernetes 的心路历程，着重于经验分享和总结，同时也会有相关的概念解析。希望能够帮助你少踩坑，少走弯路，还会指引你关注 kubernetes 生态周边，如微服务构建、DevOps、大数据应用、Service Mesh、Cloud Native 等领域。
- 《[Kubernetes 指南](https://kubernetes.feisky.xyz/zh/)》，这本书旨在整理平时在开发和使用 Kubernetes 时的参考指南和实践总结，形成一个系统化的参考指南以方便查阅。

这两本电子书都不错，前者更像是一本学习教程，而且面明显广一些，还包括 Cloud Natvie、Service Mesh 以及微服务相关的东西。而后者聚焦于 Kubernetes 本身，更像一本参考书。

**《Kubernetes in Action》一本很完美的教科书，抽丝剥茧，图文并茂。**

Kubernetes 的官方网站：[Kubernetes.io](https://kubernetes.io/)，上面不但有[全面的文档](https://kubernetes.io/docs/home/) ，也包括一个很不错的 [官方教程](https://kubernetes.io/docs/tutorials/kubernetes-basics/) 。

此外，还有一些交互式教程，帮助你理解掌握，以及一些很不错的文章。

**一些交互式教程**

- [Katacoda](https://www.katacoda.com/courses/kubernetes)
- [Kubernetes Bootcamp](https://kubernetesbootcamp.github.io/kubernetes-bootcamp/)

**一些文章**

这里还有一些不错的文档。

- [Kubernetes tips & tricks](https://opsnotice.xyz/kubernetes-tips-tricks/)
- [Achieving CI/CD with Kubernetes](http://theremotelab.com/blog/achieving-ci-cd-with-k8s/)
- [How to Set Up Scalable Jenkins on Top of a Kubernetes Cluster](https://dzone.com/articles/how-to-setup-scalable-jenkins-on-top-of-a-kubernet)
- 10 Most Common Reasons Kubernetes Deployments Fail [Part I](https://kukulinski.com/10-most-common-reasons-kubernetes-deployments-fail-part-1/) 和 [Part II](https://kukulinski.com/10-most-common-reasons-kubernetes-deployments-fail-part-2/)
- [How to Monitor Kubernetes](http://sysdig.com/blog/monitoring-kubernetes-with-sysdig-cloud/) ，一共有 4 个篇章
- [Logging in Kubernetes with Fluentd and Elasticsearch](http://www.dasblinkenlichten.com/logging-in-kubernetes-with-fluentd-and-elasticsearch/)
- [Kubernetes Monitoring: Best Practices, Methods, and Existing Solutions](https://dzone.com/articles/kubernetes-monitoring-best-practices-methods-and-e)

**网络相关的文章**

学习 Kubernetes， Kubernetes 101 系列的文章。

- [Kubernetes 101 - Networking](http://www.dasblinkenlichten.com/kubernetes-101-networking/)
- [Kubernetes networking 101 - Pods](http://www.dasblinkenlichten.com/kubernetes-networking-101-pods/)
- [Kubernetes networking 101 - Services](http://www.dasblinkenlichten.com/kubernetes-networking-101-services/)
- [Kubernetes networking 101 - (Basic) External access into the cluster](http://www.dasblinkenlichten.com/kubernetes-networking-101-basic-external-access-into-the-cluster/)
- [Kubernetes Networking 101 - Ingress resources](http://www.dasblinkenlichten.com/kubernetes-networking-101-ingress-resources/)
- [Getting started with Calico on Kubernetes](http://www.dasblinkenlichten.com/getting-started-with-calico-on-kubernetes/)

**CI/CD 相关的文章**

- [Automated Image Builds with Jenkins, Packer, and Kubernetes](https://cloud.google.com/solutions/automated-build-images-with-jenkins-kubernetes#kubernetes_architecture)
- [Jenkins setups for Kubernetes and Docker Workflow](http://iocanel.blogspot.in/2015/09/jenkins-setups-for-kubernetes-and.html)
- [Lab: Build a Continuous Deployment Pipeline with Jenkins and Kubernetes](https://github.com/GoogleCloudPlatform/continuous-deployment-on-kubernetes)

**最佳实践**

- [Kubernetes Best Practices](https://medium.com/@sachin.arote1/kubernetes-best-practices-9b1435a4cb53) by [Sachin Arote](https://medium.com/@sachin.arote1?source=post_header_lockup) ，AWS 工程师总结的最佳实践。
- [Kubernetes Best Practices](https://speakerdeck.com/thesandlord/kubernetes-best-practices) by [Sandeep Dinesh](https://github.com/thesandlord) ，Google 云平台工程师总结的最佳实践。

**Docker 和 Kubernetes 资源汇总**

下面是 GitHub 上和 Docker & Kubernetes 相关的 Awesome 系列。

- [Awesome Docker](https://github.com/veggiemonk/awesome-docker)。
- [Awesome Kubernetes](https://github.com/ramitsurana/awesome-kubernetes)。

对于系统的说明 Docker 和 Kubernetes 生态圈，推荐看 The New Stack 为 Kubernetes 出的一系列的电子书或报告。

- The New Stack eBook Series

   

  ，非常完整和详实的 Docker 和 Kubernetes 生态圈的所有东西。

  - Book 01: [The Docker Container Ecosystem](https://thenewstack.io/ebooks/docker-and-containers/the-docker-container-ecosystem/)
  - Book 02: [Applications & Microservices with Docker & Containers](https://thenewstack.io/ebooks/docker-and-containers/applications-microservices-docker-containers/)
  - Book 03: [Automation & Orchestration with Docker & Containers](https://thenewstack.io/ebooks/docker-and-containers/automation-orchestration-docker-containers/)
  - Book 04: [Network, Security & Storage with Docker & Containers](https://thenewstack.io/ebooks/docker-and-containers/networking-security-storage-docker-containers/)
  - Book 05: [Monitoring & Management with Docker & Containers](https://thenewstack.io/ebooks/docker-and-containers/monitoring-management-docker-containers/)
  - Book 06: [Use Cases for Kubernetes](https://thenewstack.io/ebooks/use-cases/use-cases-for-kubernetes/)
  - Book 07: [State of the Kubernetes Ecosystem](https://thenewstack.io/ebooks/kubernetes/state-of-kubernetes-ecosystem/)
  - Book 08: [Kubernetes Deployment & Security Patterns](https://thenewstack.io/ebooks/kubernetes/kubernetes-deployment-and-security-patterns/)
  - Book 09: [CI/CD with Kubernetes](https://thenewstack.io/ebooks/kubernetes/ci-cd-with-kubernetes/)
  - Book 10: [Kubernetes solutions Directory](https://thenewstack.io/ebooks/kubernetes/kubernetes-solutions-directory/)
  - Book 11: [Guid to Cloud-Native Microservices](https://thenewstack.io/ebooks/microservices/cloud-native-microservices-2018/)

## 管理平台

### KubeSphere

https://kubesphere.io/zh/



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



