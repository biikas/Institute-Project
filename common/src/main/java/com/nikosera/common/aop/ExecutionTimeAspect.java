package com.nikosera.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Narayan Joshi <narenzoshi@gmail.com>
 */
@Aspect
@Component
@Slf4j
public class ExecutionTimeAspect {

    @Around("@annotation(com.nikosera.common.aop.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();

        final Object proceed = joinPoint.proceed();

        final long executionTime = System.currentTimeMillis() - start;

        log.debug(joinPoint.getSignature() + " executed in " + executionTime + " ms");

        return proceed;
    }
}
