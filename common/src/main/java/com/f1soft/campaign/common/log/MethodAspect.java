package com.f1soft.campaign.common.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodAspect {

    @Around("@annotation(com.f1soft.campaign.common.log.MethodLogging)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        log.info("#{}: parameters : {} : parseStatement : {} executed in {} ms", ((MethodSignature) point.getSignature()).getMethod()
                .getName(), point.getArgs(), result, System.currentTimeMillis() - start);
        return result;
    }
}
