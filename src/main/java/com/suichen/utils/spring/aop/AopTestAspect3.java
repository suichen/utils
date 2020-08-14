package com.suichen.utils.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopTestAspect3 {
    @Pointcut("execution(* com.suichen.utils.spring.aop.OrderServiceImpl.saveOrder(..))")
    public void testAop() {

    }

    @AfterReturning(value = "testAop()")
    public void around() throws Throwable{
        System.out.println("after");
    }
}
