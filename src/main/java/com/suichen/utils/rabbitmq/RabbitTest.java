package com.suichen.utils.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/rabbit")
public class RabbitTest {
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender2 helloSender2;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;

    @PostMapping("/hello")
    public void hello() {
        helloSender1.send();
    }

    @PostMapping("/oneToMany")
    public void oneToMany() {
        for (int i = 0; i < 10; i++) {
            helloSender1.send("helloMsg: "+i);
        }
    }

    @PostMapping("/manyToMany")
    public void manyToMany() {
        for (int i = 0; i < 10; i++) {
            helloSender1.send("helloSender1 helloMsg: "+i);
            helloSender2.send("helloSender2 helloMsg: "+i);
        }
    }

    @PostMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }

    @PostMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.send();
    }

}
