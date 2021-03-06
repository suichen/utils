package com.suichen.utils.spring.boot.environment;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PropertySourceDetectorComposite implements PropertySourceDetector{

    private static final String DEFAULT_SUFFIX = "properties";
    private final List<AbstractPropertySourceDetector> propertySourceDetectors = new ArrayList<>();

    public void addPropertySourceDetector(AbstractPropertySourceDetector sourceDetector) {
        propertySourceDetectors.add(sourceDetector);
    }

    public void addPropertySourceDetector(List<AbstractPropertySourceDetector> sourceDetectors) {
        propertySourceDetectors.addAll(sourceDetectors);
    }

    public List<AbstractPropertySourceDetector> getPropertySourceDetectors() {
        return Collections.unmodifiableList(propertySourceDetectors);
    }

    @Override
    public String[] getFileExtensions() {
        List<String> fileExtensions = new ArrayList<>(8);
        for (AbstractPropertySourceDetector propertySourceDetector:propertySourceDetectors) {
            fileExtensions.addAll(Arrays.asList(propertySourceDetector.getFileExtensions()));
        }

        return fileExtensions.toArray(new String[0]);
    }

    @Override
    public void load(ConfigurableEnvironment environment, String name, Resource resource) throws IOException {
        if (resource.isFile()) {
            String fileName = resource.getFile().getName();
            int index = fileName.lastIndexOf(".");

            String suffix;
            if (-1 == index) {
                suffix = DEFAULT_SUFFIX;
            } else {
                suffix = fileName.substring(index+1);
            }

            for (AbstractPropertySourceDetector propertySourceDetector:propertySourceDetectors) {
                if (propertySourceDetector.support(suffix)) {
                    propertySourceDetector.load(environment, name, resource);
                    return;
                }
            }
        }
    }
}
