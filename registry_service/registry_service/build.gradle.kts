plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    // REMOVED: io.spring.dependency-management plugin to stop the Gradle 9 lifecycle mutation error
}

group = "com.studentregistry"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // We use the Spring Boot Platform Bill of Materials (BOM) to enforce perfect version alignment matching 3.3.0 natively
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.3.0"))

    // Core Spring Boot Application Starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("com.mysql:mysql-connector-j")

    // Database Version Control Engine
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")

    // Unified Testing Framework
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Testcontainers for automated Docker database testing
    testImplementation(platform("org.testcontainers:testcontainers-bom:1.19.8"))
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mysql")
}

tasks.withType<Test> {
    useJUnitPlatform()
}