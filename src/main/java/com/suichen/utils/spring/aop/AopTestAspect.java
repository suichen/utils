package com.suichen.utils.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopTestAspect {

    @Around("@annotation(com.suichen.utils.spring.aop.AopTest)")
    public void test(ProceedingJoinPoint joinPoint) {

    }
}
