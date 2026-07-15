package com.studentregistry.registry_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentRegistrationRequest(
        @NotBlank(message = "Student name cannot be blank")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format schema structure")
        String email,

        @NotBlank(message = "Roll number cannot be blank")
        @Size(min = 3, max = 50, message = "Roll number must be between 3 and 50 characters")
        String rollNumber,

        @NotBlank(message = "Department cannot be blank")
        String department,

        String channel,
        String templateType
) {}