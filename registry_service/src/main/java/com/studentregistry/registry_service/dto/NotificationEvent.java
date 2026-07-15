package com.studentregistry.registry_service.dto;

public class NotificationEvent {
    private String eventId;
    private String recipient;
    private String channel;
    private String messageSubject;
    private String messageBody;
    private String studentName;
    private String rollNumber;
    private String department;
    private String templateType;

    // Default Constructor for Jackson processing
    public NotificationEvent() {}

    public NotificationEvent(String eventId, String recipient, String channel, String messageSubject, String messageBody,
                             String studentName, String rollNumber, String department, String templateType) {
        this.eventId = eventId;
        this.recipient = recipient;
        this.channel = channel;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.department = department;
        this.templateType = templateType;
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

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getTemplateType() { return templateType; }
    public void setTemplateType(String templateType) { this.templateType = templateType; }
}