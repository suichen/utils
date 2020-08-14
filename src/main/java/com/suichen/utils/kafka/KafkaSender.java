package com.suichen.utils.kafka;


import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class KafkaSender {
//    private static Logger log = LoggerFactory.getLogger(KafkaSender.class);
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private Gson gson = new GsonBuilder().create();
//
//    public void send() {
//        Message message = new Message();
//        message.setId(System.currentTimeMillis());
//        message.setMsg(UUID.randomUUID().toString());
//        message.setSendTime(new Date());
//        log.info("+++++++++++++++++++++ message = {}", gson.toJson(message));
//        kafkaTemplate.send("suichen", gson.toJson(message));
//
//    }
}
