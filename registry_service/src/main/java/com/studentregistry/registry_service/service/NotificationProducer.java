package com.studentregistry.registry_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentregistry.registry_service.dto.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotificationProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TOPIC = "notification-hub-topic";

    public void sendRegistrationNotification(String studentName, String studentEmail, String rollNumber, String department, String channel, String templateType) {
        try {
            // 1. Generate a unique event tracking identifier
            String eventId = "evt_" + UUID.randomUUID().toString().substring(0, 8);

            // Set defaults if not provided
            String activeChannel = (channel == null || channel.trim().isEmpty()) ? "EMAIL" : channel;
            String activeTemplate = (templateType == null || templateType.trim().isEmpty()) ? "WELCOME" : templateType;

            // 2. Formulate the notification contents matching our microservice matrix schema
            NotificationEvent event = new NotificationEvent(
                    eventId,
                    studentEmail,
                    activeChannel,
                    "Welcome Notification", // default subject, will be overridden by template compilation on App 2
                    "Verification Pending", // default body
                    studentName,
                    rollNumber,
                    department,
                    activeTemplate
            );

            // 3. Transform the rich object into a raw JSON string payload string
            String jsonPayload = objectMapper.writeValueAsString(event);

            // 4. Fire-and-forget the message onto the streaming infrastructure broker
            kafkaTemplate.send(TOPIC, eventId, jsonPayload);
            System.out.println(">>> [Kafka Producer] Successfully broadcasted student event: " + eventId);

        } catch (Exception e) {
            System.err.println("!!! [Kafka Producer Error] Ingestion failed: " + e.getMessage());
        }
    }
}