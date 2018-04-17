package com.didispace.feignconsumer;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 禁用hystrix
 * @author liupeihua
 * @date 2018/3/26 下午3:52
 */
//要开启hystrix必须注释Configuration
//@Configuration
public class DisableHystrixConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
