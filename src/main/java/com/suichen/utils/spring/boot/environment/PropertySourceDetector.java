package com.suichen.utils.spring.boot.environment;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.IOException;


public interface PropertySourceDetector {
    String[] getFileExtensions();
    void load(ConfigurableEnvironment environment, String name, Resource resource) throws IOException;
}
