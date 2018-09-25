package com.suichen.utils;

import com.suichen.utils.kafka.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableAspectJAutoProxy
//@EnableTransactionManagement
//@EnableKafka
public class UtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilsApplication.class, args);
        System.out.println("start success.....");

//        ConfigurableApplicationContext context = SpringApplication.run(UtilsApplication.class, args);
//        System.out.println("start success....");
//
//        // Kafka
//        KafkaSender kafkaSender = context.getBean(KafkaSender.class);
//
//        for (int i = 0; i < 3; i++) {
//            kafkaSender.send();
//
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        String str = System.getProperty("java.io.tmpdir")+ File.separator;
//        System.out.println(str);

        System.err.println("error");
    }
}
