package com.studentregistry.registry_service.service;

import com.studentregistry.registry_service.dto.StudentRegistrationRequest;
import com.studentregistry.registry_service.model.Student;
import com.studentregistry.registry_service.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Standard constructor injection constructor syntax assignment
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student registerStudent(StudentRegistrationRequest request) {
        // Guardrail Rule 1: Enforce unique emails
        if (studentRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("A student with this email already exists!");
        }

        // Guardrail Rule 2: Enforce unique roll numbers
        if (studentRepository.findByRollNumber(request.rollNumber()).isPresent()) {
            throw new IllegalArgumentException("A student with this roll number already exists!");
        }

        // Transform incoming DTO contract data directly into a clean persistence DB Entity
        Student student = new Student(request.name(), request.email(), request.rollNumber());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}