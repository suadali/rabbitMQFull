package com.rabbitMQfull.rabbitMQfull.service;

import com.rabbitMQfull.rabbitMQfull.Repository.StudentRepository;
import com.rabbitMQfull.rabbitMQfull.config.MessagingConfig;
import com.rabbitMQfull.rabbitMQfull.dto.Student;
import com.rabbitMQfull.rabbitMQfull.dto.StudentStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    @Autowired
    private AmqpTemplate rabbitTemplate;

//    @Value("${javainuse.rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${javainuse.rabbitmq.routingkey}")
//    private String routingkey;

//
//    private String exchange = "javatechie_exchange";
//    private String routingkey = "javatechie_routingKey";


    public void send(StudentStatus studentStatus) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, studentStatus);
        System.out.println("Send msg = " + studentStatus);
    }
}
