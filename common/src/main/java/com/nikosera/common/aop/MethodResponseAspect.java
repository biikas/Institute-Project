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
public class MethodResponseAspect {

    @Around("@annotation(com.nikosera.common.aop.MethodResponseLogging)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        log.info("#{} #{}: response : {} : {}",
                point.getTarget().getClass().getName(),
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(), result);
        return result;
    }
}
