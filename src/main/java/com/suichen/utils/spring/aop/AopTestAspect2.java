package com.suichen.utils.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopTestAspect2 {
    @Pointcut("execution(* com.suichen.utils.test.service.HelloServiceImpl.testAop(..))")
    public void testAop() {

    }

    @Around("testAop()")
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("aspect2 method before");
        try {
            joinPoint.proceed();
        }catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("aspect2 method after");
    }
}
