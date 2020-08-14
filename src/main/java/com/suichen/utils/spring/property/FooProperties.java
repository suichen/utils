package com.suichen.utils.spring.property;

import com.suichen.utils.spring.utils.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class FooProperties {
    private String foo;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }
}
