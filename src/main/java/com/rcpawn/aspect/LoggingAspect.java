package com.rcpawn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.Comment;

@Aspect
@Component
//动态代理
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Before("execution(* com.rcpawn.service.UserService.*(..))")
    public void beforeServiceMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Calling method: " + methodName);
    }
}
