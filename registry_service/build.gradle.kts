plugins {
    java
    id("org.springframework.boot") version "3.3.0"
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
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.3.0"))

    // Core Spring Boot Application Starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    runtimeOnly("com.mysql:mysql-connector-j")

    // JWT Engine Matrix
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

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

    // The Apache Kafka messaging engine dependency
    implementation("org.springframework.kafka:spring-kafka")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Automatically parse and inject local environment variables before executing tasks
tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    val envFile = file(".env")
    if (envFile.exists()) {
        envFile.readLines().forEach { line ->
            if (line.isNotBlank() && !line.startsWith("#") && line.contains("=")) {
                val parts = line.split("=", limit = 2)
                environment(parts[0].trim(), parts[1].trim())
            }
        }
    }
}