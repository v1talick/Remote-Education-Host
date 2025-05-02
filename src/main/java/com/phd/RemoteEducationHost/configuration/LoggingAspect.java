package com.phd.RemoteEducationHost.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.phd.RemoteEducationHost.services..*(..)) " +
            "|| execution(* com.phd.RemoteEducationHost.repositories..*(..))" +
            "|| execution(* com.phd.RemoteEducationHost.mappers..*(..))")
    public void applicationPackagePointcut() {
    }

    //
    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
//        if (LoggingContext.shouldSkip()) return;

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String[] paramNames = signature.getParameterNames();
        Object[] paramValues = joinPoint.getArgs();

        StringBuilder logMessage = new StringBuilder("Method called: " + methodName + " with arguments: [");
        for (int i = 0; i < paramNames.length; i++) {
            logMessage.append(paramNames[i]).append("=").append(paramValues[i]);
            if (i < paramNames.length - 1) {
                logMessage.append(", ");
            }
        }
        logMessage.append("]");
        log.info(logMessage.toString());
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
//        if (LoggingContext.shouldSkip()) return;

        String methodSignature = joinPoint.getSignature().toShortString();
        log.info("Method returned: {} with result: {}", methodSignature, result);
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        String methodSignature = joinPoint.getSignature().toShortString();
        log.error("Method threw exception: {} with message: {}", methodSignature, e.getMessage(), e);
    }
}
