[注册中心](https://cloud.tencent.com/product/tse?from=10680)在分布式应用中是经常用到的，也是必不可少的，那注册中心，又分为以下几种：eureka（springcloud推荐的），zookeeper（与dubbo无缝结合），consul（HashiCorp开源），nacos（阿里开源的）；



## 那上面这几种注册中心都有啥区别呢？

#### Zk

用过zk做注册中心的小伙伴都知道，zk集群下一旦leader节点gg了，在短时间内服务都不可通讯，因为它们在一定时间内follower进行选举来推出新的leader，因为在这段时间内，所有的服务通信将受到影响，而且leader选取时间比较长，需要花费30~120s时间，因此：可以理解为 ZK是实现的CP，也就是将失去A（可用性）

### eureka

eureka集群下每个节点之间（P2P）都会定时发送心跳，定时同步数据，并没有master/slave之分，因此每个注册到eureka下的实例都会定时同步ip等，服务之间的调用也是根据eureka拿到的缓存服务数据进行调用。但是，如果一台eureka服务g掉了，其他eureka在一定时间内未感知到这台eureka服务g了，各个服务之间还是可以正常调用。 Eureka的集群中，只要有一台Eureka还在，就能保证注册服务可用（保证可用性），只不过查到的信息可能不是最新的（不保证强一致性）。**当数据出现不一致时，虽然A, B上的注册信息不完全相同，但每个Eureka节点依然能够正常对外提供服务，这会出现查询服务信息时如果请求A查不到，但请求B就能查到。如此保证了可用性但牺牲了一致性。** 除此之外，Eureka还有一种自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时会出现以下几种情况：

Eureka不再从注册表中移除因为长时间没有收到心跳而过期的服务； Eureka仍然能够接受新服务注册和查询请求，但是不会被同步到其它节点上（即保证当前节点依然可用）； 当网络稳定时，当前实例新注册的信息会被同步到其它节点中； 因此，Eureka可以很好的应对因网络故障导致部分节点失去联系的情况，而不会像zookeeper那样使得整个注册服务瘫痪。

### Consul：

Consul 是 HashiCorp 公司推出的开源工具，用于实现分布式系统的服务发现与配置。Consul 使用 Go 语言编写，因此具有天然可移植性（支持Linux、windows和Mac OS X）。

Consul 内置了服务注册与发现框架、分布一致性协议实现、健康检查、Key/Value 存储、多数据中心方案，不再需要依赖其他工具（比如 ZooKeeper 等），使用起来也较为简单。

Consul 遵循CAP原理中的CP原则，保证了强一致性和分区容错性，且使用的是Raft算法，比zookeeper使用的Paxos算法更加简单。虽然保证了强一致性，但是可用性就相应下降了，例如服务注册的时间会稍长一些，因为 Consul 的 raft 协议要求必须过半数的节点都写入成功才认为注册成功 ；在leader挂掉了之后，重新选举出leader之前会导致Consul 服务不可用。

##### Consul强一致性(C)带来的是：

服务注册相比Eureka会稍慢一些。因为Consul的raft协议要求必须过半数的节点都写入成功才认为注册成功 Leader挂掉时，重新选举期间整个consul不可用。保证了强一致性但牺牲了可用性。 Eureka保证高可用(A)和最终一致性：

服务注册相对要快，因为不需要等注册信息replicate到其他节点，也不保证注册信息是否replicate成功 当数据出现不一致时，虽然A, B上的注册信息不完全相同，但每个Eureka节点依然能够正常对外提供服务，这会出现查询服务信息时如果请求A查不到，但请求B就能查到。如此保证了可用性但牺牲了一致性。 其他方面，eureka就是个servlet程序，跑在servlet[容器](https://cloud.tencent.com/product/tke?from=10680)中; Consul则是go编写而成。

### Nacos

个人比较看好nacos，整合了[配置中心](https://cloud.tencent.com/product/tse?from=10680)和服务发现，和cloud，dubbo天然结合，代码也没啥侵入性 Nacos： Nacos是阿里开源的，Nacos 支持基于 DNS 和基于 RPC 的服务发现。在Spring Cloud中使用Nacos，只需要先下载 Nacos 并启动 Nacos server，Nacos只需要简单的配置就可以完成服务的注册发现。

Nacos除了服务的注册发现之外，还支持动态配置服务。动态配置服务可以让您以中心化、外部化和动态化的方式管理所有环境的应用配置和服务配置。动态配置消除了配置变更时重新部署应用和服务的需要，让配置管理变得更加高效和敏捷。配置中心化管理让实现无状态服务变得更简单，让服务按需弹性扩展变得更容易。

一句话概括就是Nacos = Spring Cloud注册中心 + Spring Cloud配置中心。



声明：本文属于转载

转载地址：[用CAP理论来解释各个注册中心的区别 - 腾讯云开发者社区-腾讯云 (tencent.com)](https://cloud.tencent.com/developer/article/1616347)