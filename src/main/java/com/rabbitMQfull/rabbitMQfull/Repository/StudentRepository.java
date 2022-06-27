package com.rabbitMQfull.rabbitMQfull.Repository;

import com.rabbitMQfull.rabbitMQfull.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByLastName(String lastName);
}