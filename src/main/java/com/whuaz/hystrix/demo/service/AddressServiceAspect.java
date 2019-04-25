package com.whuaz.hystrix.demo.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author grez
 * @since 19-4-24
 **/
@Component
@Aspect
public class AddressServiceAspect {

    @Pointcut("execution(* com.whuaz.hystrix.demo.dao.AddressDao.getAddress(..)))")
    public void addressServiceTarget() {

    }

    @Around(value = "addressServiceTarget()")
    public Object around (ProceedingJoinPoint pjp) {
        return wrapWithHystrixCommand(pjp).queue();
    }

    private HystrixCommand<Object> wrapWithHystrixCommand(final ProceedingJoinPoint pjp) {
        return new HystrixCommand<Object>(setter()) {
            @Override
            protected Object getFallback() {
                return null;
            }

            @Override
            protected Object run() throws Exception {
                try {
                    return pjp.proceed();
                } catch (Throwable throwable) {
                    throw (Exception) throwable;
                }
            }
        };
    }

    private HystrixCommand.Setter setter() {
        return HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Address"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("Address"));
    }
}
