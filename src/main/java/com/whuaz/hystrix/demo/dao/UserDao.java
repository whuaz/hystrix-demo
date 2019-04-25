package com.whuaz.hystrix.demo.dao;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author grez
 * @since 19-4-25
 **/
@Component
public class UserDao {

    private Logger logger = LoggerFactory.getLogger(UserDao.class);


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
