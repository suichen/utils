package com.suichen.utils.java.instrospector;

import com.suichen.utils.test.Person;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class Main {
    public static void main(String[] args) throws Exception{
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        PropertyDescriptor[] propertyDescriptors =
                beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor:propertyDescriptors) {
            System.out.println(propertyDescriptor.getName()+" ");
        }
    }
}
