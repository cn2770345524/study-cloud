# 注解EnableDiscoveryClient

**作用：**
1、可以获取服务信息
2、用于向consul或者zookeeper作为注册中心的时候提供注册服务



**用法**

1.在主启动类上加上注解

```java
@EnableDiscoveryClient
@SpringBootApplication
public class Consumer80Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer80Application.class, args);
    }
}
```



2.使用autowired自动注入获取到discoveryClient实例对象

```java
// DiscoveryClient来自于org.springframework.cloud.client.discovery.DiscoveryClient
@Autowired
private DiscoveryClient discoveryClient;
```



3.代码编写

```java
@GetMapping(value = "/payment/discovery")
public Object discovery(){

    // 获取微服务的名称信息
    List<String> services = discoveryClient.getServices();
    for (String element : services) {
    	// 日志信息，也会在控制台打印出来
        log.info("******element："+element);
    }

    // 获取一个微服务名称下面的所有微服务,毕竟一个微服务名称，可以有多个微服务
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    for (ServiceInstance instance : instances) {
    	// 日志信息，也会在控制台打印出来
        log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
    }
    return this.discoveryClient;
}

```



提示：

> 如果是eureka作为注册中心，使用注解EnableEurekaClient是和注解DiscoveryClient一样的效果，且注解DiscoveryClient可以适配多中注册中心，推荐在项目中使用注解DiscoveryClient