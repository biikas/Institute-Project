package com.f1soft.campaign.common.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class RestControllerAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Pointcut("!@annotation(com.f1soft.campaign.common.log.SkipAPILogging)")
    public void skipLogging() {
    }

    @Pointcut("@annotation(com.f1soft.campaign.common.log.FormPostAPILogging)")
    public void formPostLogging() {
    }

    @Around("controller() && skipLogging()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();

            log.info("Entering API Class {} # Method : {}: Parameters : {}",
                    className,
                    methodName,
                    joinPoint.getArgs()
            );

            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;

            log.info("Response of API Class {} # Method : {}: Parameters : {} Execution Time {}",
                    className,
                    methodName,
                    getValue(result),
                    elapsedTime
            );

            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }


    @Around("formPostLogging()")
    public Object logAroundFormPost(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();

            StringBuilder sb = new StringBuilder();

            Annotation[][] parameterAnnotations = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterAnnotations();
            assert joinPoint.getArgs().length == parameterAnnotations.length;

            for (int argIndex = 0; argIndex < joinPoint.getArgs().length; argIndex++) {
                for (Annotation annotation : parameterAnnotations[argIndex]) {
                    if (!(annotation instanceof RequestParam))
                        continue;
                    RequestParam requestParam = (RequestParam) annotation;
                    sb.append(requestParam.value() + "=" + joinPoint.getArgs()[argIndex]).append(",");
                }
            }

            log.info("Entering API Class {} # Method : {}: Parameters : {}",
                    className,
                    methodName,
                    sb
            );

            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;

            log.info("Response of API Class {} # Method : {}: Parameters : {} Execution Time {}",
                    className,
                    methodName,
                    getValue(result),
                    elapsedTime
            );

            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }


    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }
}
