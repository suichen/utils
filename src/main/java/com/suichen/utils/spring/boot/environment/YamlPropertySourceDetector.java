package com.suichen.utils.spring.boot.environment;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;

public class YamlPropertySourceDetector extends AbstractPropertySourceDetector{
    private static final JsonParser YAML_PARSER = new YamlJsonParser();

    @Override
    public String[] getFileExtensions() {
        return new String[]{"yaml", "yml"};
    }

    @Override
    public void load(ConfigurableEnvironment environment, String name, Resource resource) throws IOException {
        try {
            Map<String, Object> map = YAML_PARSER.parseMap(getContentStringFromResource(resource));
            Map<String, Object> target = flatten(map);
            addPropertySource(environment, new MapPropertySource(name, target));
        }catch (Exception e) {

        }
    }
}
