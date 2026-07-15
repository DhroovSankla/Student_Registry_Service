package com.studentregistry.registry_service.controller;

import com.studentregistry.registry_service.dto.StudentRegistrationRequest;
import com.studentregistry.registry_service.model.Student;
import com.studentregistry.registry_service.service.StudentService;
import com.studentregistry.registry_service.service.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    private NotificationProducer notificationProducer;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentRegistrationRequest request) {
        Student savedStudent = studentService.registerStudent(request);

        notificationProducer.sendRegistrationNotification(
                savedStudent.getName(),
                savedStudent.getEmail(),
                savedStudent.getRollNumber(),
                savedStudent.getDepartment(),
                request.channel(),
                request.templateType()
        );

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> fetchAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}