## 介绍

项目名称：study-cloud

项目描述：本项目用于存放在学习spring-cloud相关知识时所产生的代码内容

学习视频： [【尚硅谷SpringCloud框架开发教程(SpringCloudAlibaba微服务分布式架构丨Spring Cloud)】 【精准空降到 00:00】](https://www.bilibili.com/video/BV18E411x7eT/?p=43&share_source=copy_web&vd_source=f7206a6463b0c16ec377d8194e0db4c1&t=0)

参考用例：[cloud2020: 尚硅谷cloud2020 学习代码 (gitee.com)](https://gitee.com/lixiaogou/cloud2020)



## 基础知识

1. [springboot知识]([Spring Boot 教程 - Spring Boot 教程 (hxstrive.com)](https://www.hxstrive.com/subject/spring_boot/136.htm))
2. [什么是CAP（Consistency Availability Partition Tolerance）理论](https://juejin.cn/post/7168844208562765832)
3. [EnableDiscoveryClient注解](https://github.com/cn2770345524/study-cloud/blob/master/files/注解DiscoveryClient.md)
4. [Loadbalance注解](https://juejin.cn/post/7054916397893156894)
5. [RestTemplate的使用](https://github.com/cn2770345524/study-cloud/blob/master/files/RestTemplate工具类.md)



## 内容

注册中心

| 名称      | CAP  | 说明 |
| --------- | ---- | ---- |
| eureka    | AP   |      |
| zookeeper | CP   |      |
| Consul    | CP   |      |
| Nacos     |      |      |

- [用CAP理论来解释各个注册中心的区别](https://github.com/cn2770345524/study-cloud/blob/master/files/用CAP理论来解释各个注册中心的区别.md)



负载均衡

| 名称      | 跳转链接                                                     | 说明                                      |
| --------- | ------------------------------------------------------------ | ----------------------------------------- |
| ribbon    | [点击跳转查看详情](https://github.com/cn2770345524/study-cloud/blob/master/files/what%20is%20Ribbon.md) |                                           |
| openFeign | [点击跳转查看详情](https://github.com/cn2770345524/study-cloud/blob/master/files/openFeign.md) | openFeign是在ribbon调用服务上做了二次封装 |
|           |                                                              |                                           |



熔断降级（断路器）



参考链接：

1. [微服务是什么 (biancheng.net)](http://c.biancheng.net/springcloud/micro-service.html)
2. [| Java 全栈知识体系 (pdai.tech)](https://pdai.tech/)
3. [分布式ID方案](https://www.woshinlper.com/system-design/micro-service/%E5%88%86%E5%B8%83%E5%BC%8Fid%E7%94%9F%E6%88%90%E6%96%B9%E6%A1%88%E6%80%BB%E7%BB%93/)
