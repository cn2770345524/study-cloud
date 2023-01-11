package com.liuhao.cloud.ribbon.settings;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyRuleSetting用于配置ribbon负载均衡使用的算法，默认是使用的轮询算法，我们可以通过配置来修改负载均衡所使用的的算法
 *
 * 注意：
 * 不能将对于ribbon的配置放在能被ComponentScan扫描的包下(既springboot启动类所在包以下)，那样做会导致配置对全局生效
 * 最好 放在上层不同的包名下
 *
 **/
@Configuration
public class MyRuleSetting {

    @Bean
    public IRule getRule(){
        return new RandomRule();
    }

}
