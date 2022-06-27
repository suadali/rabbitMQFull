package com.rabbitMQfull.rabbitMQfull.consumer;

import com.rabbitMQfull.rabbitMQfull.config.MessagingConfig;
import com.rabbitMQfull.rabbitMQfull.dto.StudentStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(StudentStatus studentStatus) {
        System.out.println("Message recieved from queue : " + studentStatus);
    }
}
