package com.studentregistry.registry_service.dto;

public class NotificationEvent {
    private String eventId;
    private String recipient;
    private String channel;
    private String messageSubject;
    private String messageBody;

    // Default Constructor for Jackson processing
    public NotificationEvent() {}

    public NotificationEvent(String eventId, String recipient, String channel, String messageSubject, String messageBody) {
        this.eventId = eventId;
        this.recipient = recipient;
        this.channel = channel;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
    }

    // Getters and Setters
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public String getMessageSubject() { return messageSubject; }
    public void setMessageSubject(String messageSubject) { this.messageSubject = messageSubject; }

    public String getMessageBody() { return messageBody; }
    public void setMessageBody(String messageBody) { this.messageBody = messageBody; }
}