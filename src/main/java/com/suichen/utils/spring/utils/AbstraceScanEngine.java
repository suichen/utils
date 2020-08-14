package com.suichen.utils.spring.utils;

import org.springframework.core.type.classreading.MetadataReader;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Think on 2017/7/30.
 */
public abstract class AbstraceScanEngine {
    public abstract boolean filter(MetadataReader metadataReader);
    public abstract String getBasePackage(String basePackage);

    public List<MetadataReader> doScan(String basePackage) {
        return ScanProvider.getInstance().doScan(getBasePackage(basePackage)).stream()
                .filter(this::filter).collect(Collectors.toList());
    }
}
