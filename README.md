##### hystrix的简单使用

- 配置hystrix,添加hystrix依赖
```
<dependency>
    <groupId>com.netflix.hystrix</groupId>
    <artifactId>hystrix-metrics-event-stream</artifactId>
    <version>${hystrix.version}</version>
</dependency>

<dependency>
    <groupId>com.netflix.hystrix</groupId>
    <artifactId>hystrix-javanica</artifactId>
    <version>${hystrix.version}</version>
</dependency>

<dependency>
    <groupId>com.netflix.hystrix</groupId>
    <artifactId>hystrix-servo-metrics-publisher</artifactId>
    <version>${hystrix.version}</version>
</dependency>
```
- 自定义hystrix

需要继承HystrixCommand类,建议重写getFallback方法
部分代码来自https://github.com/xianlinbox/HystrixDemo

- 注解方式使用hystrix

使用示例:
```
/**
     * 注解方式配置Hystrix
     * 注意，需要引入Hystrix Aspect   @see HystrixCommandAspect
     *
     */
    @HystrixCommand(groupKey = "cache",commandKey = "hello-user",threadPoolKey = "hello-pool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000"),
                    @HystrixProperty(name = "execution.isolation.strategy",value = "THREAD")},
            fallbackMethod = "userFallback"
    )
    public String getUser(Long id) throws IOException {
        String response = Request.Get("http://localhost:8080/user/cache/" + id)
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute()
                .returnContent()
                .asString();
        logger.info("Get user  {}, {}", id, response);
        return response;
    }

    public String userFallback(Long id, Throwable e) {
        logger.error("userCacheFallback failed", e.getMessage());
        return "default user wandouxia";
    }
}

```

注入HystrixCommandAspect
```
@Bean
public HystrixCommandAspect hystrixAspect() {
    return new HystrixCommandAspect();
}
```