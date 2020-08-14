package com.suichen.utils.spring.utils;

import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 2017/7/30.
 */

public class ScanProvider {
    private static ScanProvider instance = new ScanProvider();

    public static ScanProvider getInstance() {
        return instance;
    }

    public List<MetadataReader> doScan(String basePackage) {
        Resource[] resources = getResourceArray(basePackage);
        List<MetadataReader> metadataReaderList = new ArrayList<>();

        try {
            for (Resource resource:resources) {
                MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                metadataReaderList.add(metadataReader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return metadataReaderList;
    }

    private String getPackageSerachPath(String basePackage) {
        return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + resolveBasePackage(basePackage) + "/" + this.resourcePattern;
    }

    private String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(this.environment.resolveRequiredPlaceholders(basePackage));
    }


    private Resource[] getResourceArray(String basePackage) {
        try {
            return this.resourcePatternResolver.getResources(getPackageSerachPath(basePackage));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
    private String resourcePattern = DEFAULT_RESOURCE_PATTERN;
    private Environment environment = new StandardEnvironment();

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private MetadataReaderFactory metadataReaderFactory =
            new CachingMetadataReaderFactory(this.resourcePatternResolver);
}
