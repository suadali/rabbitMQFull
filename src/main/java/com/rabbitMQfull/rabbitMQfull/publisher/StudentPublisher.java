package com.rabbitMQfull.rabbitMQfull.publisher;

import com.rabbitMQfull.rabbitMQfull.config.MessagingConfig;
import com.rabbitMQfull.rabbitMQfull.dto.Student;
import com.rabbitMQfull.rabbitMQfull.dto.StudentStatus;
import com.rabbitMQfull.rabbitMQfull.service.StudentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentPublisher {

    private StudentService studentService;

    @Autowired
    public StudentPublisher(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        Student studentreal=new Student();
        studentreal.setId(student.getId());
        studentreal.setFirstName(student.getFirstName());
        studentreal.setLastName(student.getLastName());
        studentreal.setEmail(student.getEmail());
        studentreal.setAge(student.getAge());

        StudentStatus studentStatus = new StudentStatus(studentreal, "PROCESS", "student added");

        studentService.send(studentStatus);

        System.out.println(studentStatus);
        studentService.addNewStudent(student);
    }



//    @Autowired // injecting rabbit MQ... service?
//    private RabbitTemplate template;

//    @PostMapping
//    public String bookOrder(@RequestBody Student student) {
////        order.setOrderId(UUID.randomUUID().toString());
////        //restaurantservice
////        //payment service
////        StudentStatus orderStatus = new StudentStatus(studentreal, "PROCESS", "order placed succesfully in " + restaurantName);
////        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
////        return "Success !!";
//
//        Student studentreal=new Student();
//        studentreal.setId(student.getId());
//        studentreal.setFirstName(student.getFirstName());
//        studentreal.setLastName(student.getLastName());
//        studentreal.setEmail(student.getEmail());
//        studentreal.setAge(student.getAge());
//
//        StudentStatus studentStatus = new StudentStatus(studentreal, "PROCESS", "student added");
//        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, studentStatus);
//        System.out.println(studentStatus);
//        return "Success !!";
//
//
//    }
}