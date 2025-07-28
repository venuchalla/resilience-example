package com.example.resilience.aspect;


import com.example.resilience.annotations.LogAfterMethod;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.resilience.services.*.*(..))")
    public void beforeEnterService(JoinPoint joinPoint) {
        logger.info("log before service method execution : {} ", joinPoint.getClass());
    }

    // @After("execution(* com.example.resilience.example.services.*.*(..))")
    @After("@annotation(com.example.resilience.annotations.LogAfterMethod)")
    public void logAfterMethodExecution(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAfterMethod annotation = method.getAnnotation(LogAfterMethod.class);
        //Access the annotation value
        String value = annotation.value();
        logger.info("After advice value passed from the annotation : {}", value);
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("After advice Executed: {} ", methodName);
    }

    @Around("execution(* com.example.resilience.services.*.*(..))")
    public Object logWhileEnterAndExistServiceMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Around advice for method {} is started ",proceedingJoinPoint.getSignature().toShortString());
        Object result = proceedingJoinPoint.proceed();
        logger.info("Around advice method {} is completed",proceedingJoinPoint.getSignature().toShortString());
        return result;
    }
}
