package com.suichen.utils.spring.boot.environment;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Map;

public class PropertiesPropertySourceDetector extends AbstractPropertySourceDetector{
    @Override
    public String[] getFileExtensions() {
        return new String[]{"properties", "conf"};
    }

    @Override
    public void load(ConfigurableEnvironment environment, String name, Resource resource) throws IOException {
        Map map = PropertiesLoaderUtils.loadProperties(resource);
        addPropertySource(environment, new MapPropertySource(name, map));
    }
}
