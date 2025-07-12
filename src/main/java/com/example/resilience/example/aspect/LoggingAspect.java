package com.example.resilience.example.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.resilience.example.services.*.*(..))")
    public void beforeEnterService(JoinPoint joinPoint){
        logger.info("log before method :{} ",joinPoint.getClass());
    }
    @After("execution(* com.example.resilience.example.services.*.*(..))")
    public void afterEnterService(JoinPoint joinPoint){
        logger.info("log after method :{} ",joinPoint.getClass());
    }
    @Around("execution(* com.example.resilience.example.services.*.*(..))")
    public Object logWhileEnterAndExistServiceMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("method is starting");
      Object result=  proceedingJoinPoint.proceed();
        logger.info("method is completed");
        return result;
    }
}
