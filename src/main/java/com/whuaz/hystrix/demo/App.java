package com.whuaz.hystrix.demo;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author grez
 * @since 19-4-24
 **/
@SpringBootApplication
@EnableAspectJAutoProxy
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }
}
