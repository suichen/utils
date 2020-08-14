package com.suichen.utils.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Value("${cicada.port}")
    private Integer servers;

    @Bean
    Person person() {
        Person person = new Person();
        person.setAge(servers);
        return person;
    }

}
