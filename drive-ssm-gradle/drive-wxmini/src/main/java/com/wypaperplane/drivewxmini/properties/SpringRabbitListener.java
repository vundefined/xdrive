package com.wypaperplane.drivewxmini.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

// @Component
// @RabbitListener(queues = "drive.queue")
public class SpringRabbitListener {

    private final Logger logger = LoggerFactory.getLogger(SpringRabbitListener.class);

    // @RabbitHandler(isDefault = true)
    // public void whenMessageCome(Message message) throws UnsupportedEncodingException {
    //     logger.info("MySpringRabbitListener---{}", SerializationUtils.deserialize(message.getBody()));
    // }

    // @RabbitHandler(isDefault = true)
    // public void whenMessageCome(@Payload String messageStr) {logger.info("MyMessageListener---{}", messageStr);}
}
