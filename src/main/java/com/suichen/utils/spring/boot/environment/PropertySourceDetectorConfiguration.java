package com.suichen.utils.spring.boot.environment;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PropertySourceDetectorConfiguration implements ImportBeanDefinitionRegistrar {
    private static final String PATH_PREFIX = "profiles";

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) registry;
        ConfigurableEnvironment environment = beanFactory.getBean(ConfigurableEnvironment.class);
        List<AbstractPropertySourceDetector> propertySourceDetectors = new ArrayList<>();

        configurePropertySourceDetectors(propertySourceDetectors, beanFactory);
        PropertySourceDetectorComposite propertySourceDetectorComposite = new PropertySourceDetectorComposite();
        propertySourceDetectorComposite.addPropertySourceDetector(propertySourceDetectors);

        String[] activeProfiles = environment.getActiveProfiles();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

        try {
            for (String profile:activeProfiles) {
                String location = PATH_PREFIX+ File.separator+profile+File.separator+"*";
                Resource[] resources = resourcePatternResolver.getResources(location);
                for (Resource resource:resources) {
                    propertySourceDetectorComposite.load(environment, resource.getFilename(), resource);
                }
            }
        }catch (Exception e) {

        }
    }

    private void configurePropertySourceDetectors(List<AbstractPropertySourceDetector> propertySourceDetectors,
                                                  DefaultListableBeanFactory beanFactory) {
        propertySourceDetectors.add(new JsonPropertySourceDetector());
        propertySourceDetectors.add(new YamlPropertySourceDetector());
        propertySourceDetectors.add(new PropertiesPropertySourceDetector());
    }
}
