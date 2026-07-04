package com.studentregistry.registry_service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Tells Spring to look for application-test.properties
public abstract class BaseIntegrationTest {
    // Clean, lightweight base without Testcontainers orchestration dependencies
}