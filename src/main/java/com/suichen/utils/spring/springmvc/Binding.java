package com.suichen.utils.spring.springmvc;


import com.suichen.utils.spring.annotation.MyModelAttribute;
import com.suichen.utils.spring.annotation.MyRequstParam;
import com.suichen.utils.spring.exception.springmvcException;
import com.suichen.utils.spring.utils.AnnotationUtils;
import com.suichen.utils.spring.utils.isBasicTypeUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName Binding
 * @Description
 * @Data 2018/7/4
 * @Author xiao liang
 */
public class Binding {

    public static  List<Object> bingdingMethodParamters(Map<String, Method> bindingRequestMapping, HttpServletRequest request) {
        List<Object> resultParameters  = new ArrayList<>();
        Set<Map.Entry<String, Method>> entries = bindingRequestMapping.entrySet();
        for (Map.Entry<String, Method> entry :
                entries) {
            Method method = entry.getValue();
            Parameter[] parameters = method.getParameters();

            //pan kong
            for (Parameter parameter :
                    parameters) {
                if (!AnnotationUtils.isEmpty(parameter.getAnnotations())){
                    Object resultParameter = null;
                    try {
                        resultParameter = bingdingEachParamter(parameter, request);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new springmvcException("绑定参数异常");
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        throw new springmvcException("绑定参数异常");
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        throw new springmvcException("绑定参数异常");
                    }
                    resultParameters.add(resultParameter);
                }
            }
        }
        return resultParameters;
    }

    private static Object bingdingEachParamter(Parameter parameter, HttpServletRequest request) throws IllegalAccessException, NoSuchMethodException, InstantiationException {

      //这里有问题
        if (!AnnotationUtils.isEmpty(parameter.getAnnotation(MyRequstParam.class))){
            BindingParamter bindingParamter = new BindingByMyRequstParam();
            Object resultParameter = bindingParamter.bindingParamter(parameter, request);
            return resultParameter;
        }

        else if (!AnnotationUtils.isEmpty(parameter.getAnnotation(MyModelAttribute.class))){
            BindingParamter bindingParamter = new BindingByMyModelAttribute();
            Object resultParameter = bindingParamter.bindingParamter(parameter,request);
            return resultParameter;
        }
        else if(parameter.getAnnotations() == null || parameter.getAnnotations().length ==0){
            boolean flag = isBasicTypeUtils.isBasicType(parameter.getType().getSimpleName());
            if (flag){
                BindingParamter bindingParamter = new BindingByMyRequstParam();
                Object resultParameter = bindingParamter.bindingParamter(parameter, request);
                return resultParameter;
            }
            else{
                BindingParamter bindingParamter = new BindingByMyModelAttribute();
                Object resultParameter = bindingParamter.bindingParamter(parameter,request);
                return resultParameter;
            }
        }
        return null;

    }

}
