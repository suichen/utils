package com.suichen.utils.spring.property;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Map;

public class PlaceholderHelper {
    private static final String PLACEHOLDER_PREFIX = "${";
    private static final String PLACEHOLDER_SUFFIX = "}";
    private static final String VALUE_SEPARATOR = ":";
    private static final String EXPRESSION_PREFIX = "#{";
    private static final String EXPRESSION_SUFFIX = "}";


    /**
     * 对扩展Spring的$Value("${key}")进行扩展
     * 将key对应的字符串转化为对象
     *
     * activity.id = {"wenzhou": 256, "jinhua": 278}
     */
    public Object resolvePropertyValue(ConfigurableBeanFactory beanFactory, String beanName,
                                       String placeholder, Class<?> clazz) {
        String strValue = beanFactory.resolveEmbeddedValue(placeholder);
        BeanDefinition bd = beanFactory.containsBean(beanName) ? beanFactory.getMergedBeanDefinition(beanName) : null;
        Object value = evaluateBeanDefinitionString(beanFactory, strValue, bd);
        try {
            return JSON.parseObject(JSON.toJSONString(value), clazz);
        }catch (Exception e) {
            e.printStackTrace();
            return value;
        }
    }

    private Object evaluateBeanDefinitionString(ConfigurableBeanFactory beanFactory, String value, BeanDefinition beanDefinition) {
        if (beanFactory.getBeanExpressionResolver() == null) {
            return value;
        }

        Scope scope = (beanDefinition != null ?
                beanFactory.getRegisteredScope(beanDefinition.getScope()) : null);

        return beanFactory.getBeanExpressionResolver().evaluate(value, new BeanExpressionContext(beanFactory, scope));
    }















}
