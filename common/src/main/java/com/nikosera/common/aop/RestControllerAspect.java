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
public class RestControllerAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("Entering API Class {} # Method : {}: Parameters : {}",
                point.getTarget().getClass().getSimpleName(),
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                point.getArgs()
        );
        return point.proceed();
    }
}
