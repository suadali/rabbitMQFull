package com.rabbitMQfull.rabbitMQfull.publisher;

import com.rabbitMQfull.rabbitMQfull.config.MessagingConfig;
import com.rabbitMQfull.rabbitMQfull.dto.Student;
import com.rabbitMQfull.rabbitMQfull.dto.StudentStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student")
public class OrderPublisher {

    @Autowired // injecting rabbit MQ... service?
    private RabbitTemplate template;

    @PostMapping
    public String bookOrder(@RequestBody Student student) {
//        order.setOrderId(UUID.randomUUID().toString());
//        //restaurantservice
//        //payment service
//        StudentStatus orderStatus = new StudentStatus(studentreal, "PROCESS", "order placed succesfully in " + restaurantName);
//        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
//        return "Success !!";

        Student studentreal=new Student();
        studentreal.setId(student.getId());
        studentreal.setFirstName(student.getFirstName());
        studentreal.setLastName(student.getLastName());
        studentreal.setEmail(student.getEmail());
        studentreal.setAge(student.getAge());

        StudentStatus studentStatus = new StudentStatus(studentreal, "PROCESS", "student added");
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, studentStatus);
        System.out.println(studentStatus);
        return "Success !!";


    }
}