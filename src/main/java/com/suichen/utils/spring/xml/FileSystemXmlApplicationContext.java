package com.suichen.utils.spring.xml;

import com.suichen.utils.spring.factory.GenericBeanDefinition;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 *  @Author xiao liang
 */
@Slf4j
public class FileSystemXmlApplicationContext extends XmlApplicationContext {


    public  Map<String, GenericBeanDefinition> getGenericBeanDefinition(String contextConfigLocation){

        Map<String, GenericBeanDefinition>  genericBeanDefinition  = super.getBeanDefinitionMap(contextConfigLocation);

        return genericBeanDefinition;
    }
}


