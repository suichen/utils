package com.suichen.utils.spring.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AopMainTest {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        Advisor advisor = new DefaultPointcutAdvisor(new DmzPointcut(), new DmzAroundAdvice());
        proxyFactory.addAdvisor(advisor);

        proxyFactory.addAdvice(new DmzAfterReturnAdvice());
        proxyFactory.addAdvice(new DmzBeforeAdvice());
        proxyFactory.addAdvice(new DmzIntroductionAdvice());
        proxyFactory.setTarget(new DmzService());

        proxyFactory.setProxyTargetClass(true);
        Object proxy = proxyFactory.getProxy();
        proxy.toString();

        if (proxy instanceof DmzService) {
            ((DmzService)proxy).testAop();
        }

        if (proxy instanceof Runnable) {
            ((Runnable) proxy).run();
        }
    }
}
