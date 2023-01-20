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
public class MethodRequestAspect {

    @Around("@annotation(com.nikosera.common.aop.MethodRequestLogging)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        log.info("#{} #{}: parameters : {}",
                point.getTarget().getClass().getName(),
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(), point.getArgs());
        return result;
    }
}
