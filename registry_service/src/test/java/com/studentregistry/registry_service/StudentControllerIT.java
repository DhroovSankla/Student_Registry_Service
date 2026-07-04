package com.studentregistry.registry_service;

import com.studentregistry.registry_service.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class StudentControllerIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        // Explicitly clear the test database table before each test run
        studentRepository.deleteAll();
    }

    @Test
    void shouldRegisterNewStudentSuccessfully() throws Exception {
        String studentJson = """
                {
                    "name": "Integration Test",
                    "email": "test.student@cgc.edu",
                    "rollNumber": "TEST101"
                }
                """;

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Integration Test"))
                .andExpect(jsonPath("$.email").value("test.student@cgc.edu"));
    }
}