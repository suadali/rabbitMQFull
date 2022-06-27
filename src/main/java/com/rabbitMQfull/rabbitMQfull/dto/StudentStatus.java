package com.rabbitMQfull.rabbitMQfull.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentStatus {

    private Student student;
    private String status;//progress,completed
    private String message;
}