package com.suichen.utils;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidSpringAopConfiguration;
import com.suichen.utils.kafka.KafkaSender;
import com.suichen.utils.spring.property.FooProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        RedisAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class})
@EnableAspectJAutoProxy
@EnableScheduling
@EnableTransactionManagement
@EnableAsync(proxyTargetClass=true)
//@EnableKafka
public class UtilsApplication {

    //private static Logger logger = LoggerFactory.getLogger(UtilsApplication.class);

    /*@Scheduled(fixedRate = 1000)
    public void schedule() {
        System.out.println("schedule....");
    }*/

    private void test() {
        Class<?> clazz = null;
        Map<Method, Set<Scheduled>> annotedMethods = MethodIntrospector.selectMethods(clazz,
                new MethodIntrospector.MetadataLookup<Set<Scheduled>>() {
                    @Override
                    public Set<Scheduled> inspect(Method method) {
                        Set<Scheduled> scheduleMethods =
                                AnnotatedElementUtils.getMergedRepeatableAnnotations(method,Scheduled.class, Scheduled.class);
                        return scheduleMethods;
                    }
                });
    }
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UtilsApplication.class, args);
        System.out.println("start success.....");

        /*Binder binder = Binder.get(context.getEnvironment());
        FooProperties foo = binder.bind("com.didispace", Bindable.of(FooProperties.class)).get();
        System.out.println(foo.getFoo());
        context.getBean(FooProperties.class);*/
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
