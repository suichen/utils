package com.suichen.utils.proxy;

import com.suichen.utils.StackTraceUtils;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Set;

public class RestUtilInit implements BeanFactoryPostProcessor {
    private Logger log = LoggerFactory.getLogger(RestUtilInit.class);

    DefaultListableBeanFactory defaultListableBeanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;

    }

    private void inint() throws Exception {
        Set<Class<?>> requests = new Reflections(StackTraceUtils.getPackagePathByMainClass(2))
                                    .getTypesAnnotatedWith(Server.class);

        for (Class req:requests) {
            createDynamicProxy(req);
        }
    }

    private void createDynamicProxy(Class<?> clazz) throws Exception{
        ServerInfo serverInfo = extractServerInfo(clazz);
        MyInvocationHandler invocationHandler = new MyInvocationHandler(serverInfo, defaultListableBeanFactory);

        BeanDefinition beanDefinition = createProxyBeanDefinition(clazz, invocationHandler);
        registerBeanDefinition(clazz, beanDefinition);
    }


    private BeanDefinition createProxyBeanDefinition(Class<?> clazz, MyInvocationHandler invocationHandler) throws Exception{
        boolean proxyClass = isProxyClass(clazz);

        if (!proxyClass) {//使用JDK动态代理
            JdkProxy proxy = new JdkProxy(clazz, invocationHandler);
            return getJdkBeanDefinition(clazz, invocationHandler);
        }

        //使用Cglib动态代理
        return null;
    }

    private BeanDefinition getJdkBeanDefinition(Class<?> clazz, MyInvocationHandler invocationHandler) {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.
                genericBeanDefinition(clazz).addConstructorArgValue(invocationHandler)
                .getRawBeanDefinition();
        beanDefinition.setAutowireCandidate(true);
        return beanDefinition;
    }
    private void registerBeanDefinition(Class<?> clazz, BeanDefinition beanDefinition) {
        this.defaultListableBeanFactory.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
    }

    private ServerInfo extractServerInfo(Class<?> clazz) {
        ServerInfo info = new ServerInfo();
        Server server = clazz.getAnnotation(Server.class);
        info.setHost(server.value());
        return info;
    }

    private boolean isProxyClass(Class<?> clazz) {
        return !clazz.isInterface() || clazz.getAnnotation(Server.class).proxyClass();
    }
}
