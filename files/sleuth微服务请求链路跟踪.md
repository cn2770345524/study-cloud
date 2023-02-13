## 1 链路追踪介绍

在大型系统的微服务化构建中，一个系统被拆分成了许多微服务。这些模块负责不同的功能，组合成系统，最终可以提供丰富的功能。在这种架构中，一次请求往往需要涉及到多个服务。互联网应用构建在不同的软件模块集上，这些软件模块，有可能是由不同的团队开发、可能使用不同的编程语言来实现、有可能布在了几千台服务器，横跨多个不同的数据中心，也就意味着这种架构形式也会存在一些问题：

- 如何快速发现问题？

- 如何判断故障影响范围？

- 如何梳理服务依赖以及依赖的合理性？

- 如何分析链路性能问题以及实时容量规划？

  

  ![img](resources/.assets/3a844d3079dc4728aa9bb787feca93e8.png) 

**分布式链路追踪（Distributed Tracing），就是将一次分布式请求还原成调用链路，进行日志记录，性能监控并将一次分布式请求的调用情况集中展示。**比如各个服务节点上的耗时、请求具体到达哪台机器上IP、每个服务节点的请求状态200  500等等。



常见的链路追踪技术有下面这些：

cat 由大众点评开源，基于Java开发的实时应用监控平台，包括实时应用监控，业务监控 。 集成

方案是通过代码埋点的方式来实现监控，比如： 拦截器，过滤器等。 对代码的侵入性很大，集成成本较高。风险较大。

**zipkin** 由Twitter公司开源，开放源代码分布式的跟踪系统，用于收集服务的定时数据，以解决微服务架构中的延迟问题，包括：数据的收集、存储、查找和展现《图形化》。**该产品结合spring-cloud-sleuth 使用较为简单， 集成很方便， 但是功能较简单**。

pinpoint Pinpoint是韩国人开源的基于字节码注入的调用链分析，以及应用监控分析工具。特点

是支持多种插件，UI功能强大，接入端无代码侵入。

skywalking 【未来企业会使用的多】

SkyWalking是本土开源的基于字节码注入的调用链分析，以及应用监控分析工具。特点是支持多

种插件，UI功能较强，接入端无代码侵入。目前已加入Apache孵化器。

Sleuth  （日志记录每一条链路上的所有节点，以及这些节点所在的机器，和耗时。）log4j

SpringCloud 提供的分布式系统中链路追踪解决方案。

**注意：SpringCloud alibaba技术栈中并没有提供自己的链路追踪技术的，我们可以采用Sleuth +Zipkin来做链路追踪解决方案**



Springcloud 并不是自己技术---而是把所有框架整合在一起 来解决微服务上的问题。



## 2.SpringCloud Sleuth



SpringCloud Sleuth主要功能就是在分布式系统中提供追踪解决方案。它大量借用了Google Dapper的设计， 先来了解一下Sleuth中的术语和相关概念。

**Trace** (一条完整链路--包含很多span(微服务接口))

由一组Trace Id（贯穿整个链路）相同的Span串联形成一个树状结构。为了实现请求跟踪，当请求到达分布式系统的入口端点时，只需要服务跟踪框架为该请求创建一个唯一的标识（即TraceId），同时在分布式系统内部流转的时候，框架始终保持传递该唯一值，直到整个请求的返回。那么我们就可以使用该唯一标识将所有的请求串联起来，形成一条完整的请求链路。

**Span**

代表了一组基本的工作单元。为了统计各处理单元的延迟，当请求到达各个服务组件的时候，也通过一个唯一标识（SpanId）来标记它的开始、具体过程和结束。通过SpanId的开始和结束时间戳，就能统计该span的调用时间，除此之外，我们还可以获取如事件的名称。请求信息等元数据。

**Annotation**

用它记录一段时间内的事件，内部使用的重要注释：

cs（Client Send）客户端发出请求，开始一个请求的命令
sr（Server Received）服务端接受到请求开始进行处理， sr－cs = 网络延迟（服务调用的时间）
ss（Server Send）服务端处理完毕准备发送到客户端，ss - sr = 服务器上的请求处理时间
cr（Client Reveived）客户端接受到服务端的响应，请求结束。 cr - cs = 请求的总时间



![跟踪信息传播](resources/.assets/trace-id-16762979648425.jpg) 

## 3.基于Sleuth+zipkin来对分布式系统做链路跟踪已经可视化监控

1.zipkin是个可视化服务器，我们可以通过多种方式去获取（spring cloud F版本已经集成了zipkin）。zipkin的官网地址：[https://zipkin.io/pages/quickstart.html](https://zipkin.io/pages/quickstart.html)。zipkin的github地址：[https://github.com/openzipkin/zipkin](https://github.com/openzipkin/zipkin)

1.1.通过拉去docker镜像获取应用实例

```bash
docker run -d -p 9411:9411 openzipkin/zipkin
```

1.2.直接下载对应的jar包，通过`java -jar` 命令启动，下载链接：https://search.maven.org/remote_content?g=io.zipkin&a=zipkin-server&v=LATEST&c=exec

下载后启动，一般默认端口为9411

![image-20230213222939267](resources/.assets/image-20230213222939267.png)

![image-20230213223001445](resources/.assets/image-20230213223001445.png)



2.在springCloud微服务中加入sleuth链路监控

2.1对应的maven坐标

```xml
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

2.2.修改配置文件,加入如下配置

```yaml
spring:
  zipkin:
    base-url: http://127.0.0.1:9411/ # 你的zipkin的服务器地址
```



> 注意是在每个微服务中加入上面的配置



我们尝试通过a服务调用b服务在zipkin中就可以看到调用请求的链路过长，消耗时间，等等相关的数据

![image-20230213223323035](resources/.assets/image-20230213223323035.png)

![image-20230213223335611](resources/.assets/image-20230213223335611.png)



## 4.参考文章

- [Sleuth实战 - 方志朋的博客 (fangzhipeng.com)](https://www.fangzhipeng.com/springcloud/2017/08/12/springcloud-sleuth.html)