package com.nikosera.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author Narayan Joshi <narenzoshi@gmail.com>
 */
@Aspect
@Component
@Slf4j
public class MethodAspect {

    @Around("@annotation(com.nikosera.common.aop.MethodLogging)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("Executing {}'s {} ",point.getTarget().getClass().getSimpleName(), MethodSignature.class.cast(point.getSignature()).getMethod().getName());
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        log.info("Executed {}: with parameters : {} : executed in {} ms",
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                point.getArgs(),
                System.currentTimeMillis() - start);
        return result;
    }
}
