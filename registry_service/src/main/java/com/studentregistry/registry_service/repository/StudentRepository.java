package com.studentregistry.registry_service.repository;

import com.studentregistry.registry_service.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Spring automagically compiles this method into: SELECT * FROM students WHERE email = ?
    Optional<Student> findByEmail(String email);

    // Spring automagically compiles this method into: SELECT * FROM students WHERE roll_number = ?
    Optional<Student> findByRollNumber(String rollNumber);
}