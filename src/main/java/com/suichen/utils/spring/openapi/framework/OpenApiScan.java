package com.suichen.utils.spring.openapi.framework;

import com.suichen.utils.spring.common.CommonUtils;
import com.suichen.utils.spring.utils.AbstraceScanEngine;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Think on 2017/7/30.
 */
@Component
public class OpenApiScan extends AbstraceScanEngine {
    private static final String DEFAULT_OPEN_API_PACKAGE="com.suichen.openapi.api";

    @Override
    public String getBasePackage(String basePackage) {
        return DEFAULT_OPEN_API_PACKAGE;
    }

    @Override
    public boolean filter(MetadataReader metadataReader) {
        return metadataReader.getAnnotationMetadata().hasAnnotatedMethods(OpenApi.class.getName());
    }

    @PostConstruct
    public void do$scan() {
//        List<MetadataReader> metadataReaderList = doScan(DEFAULT_OPEN_API_PACKAGE);
//
//        for (MetadataReader metadataReader:metadataReaderList) {
//            Class clazz= CommonUtils.loadClass(metadataReader.getClassMetadata().getClassName());
//            Method[] methods = clazz.getDeclaredMethods();
//
//            for (Method method:methods) {
//                if (method.getAnnotation(OpenApi.class) != null) {
//                    register(method);
//                }
//            }
//        }
        doScan(DEFAULT_OPEN_API_PACKAGE).stream().map(metadataReader -> {
            ReflectionUtils.doWithMethods(CommonUtils.loadClass(metadataReader.getClassMetadata().getClassName()),
                    this::register, this::filterAnnotation);
            return null;
        });

    }

    private void register(Method method) {
        OpenApi openApiType = method.getAnnotation(OpenApi.class);
        OpenApiMethodInfo info = new OpenApiMethodInfo();
        info.setMethod(method);
        info.setClazz(method.getClass());
        OpenApiRegsister.register(openApiType.value(), info);
    }
    private boolean filterAnnotation(Method method) {
        System.out.println(method.getName());
        return null != method.getAnnotation(OpenApi.class);
    }

}
