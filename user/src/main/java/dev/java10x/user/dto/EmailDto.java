package dev.java10x.user.dto;

import java.util.UUID;

public class EmailDto{
    private UUID UserId;
    private String emailFrom;
    private String emailTo;
    private String emailSubject;
    private String body;

    public EmailDto(UUID UserId, String emailFrom, String emailTo, String emailSubject, String body) {
        this.UserId = UserId;
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.emailSubject = emailSubject;
        this.body = body;
    }

    public EmailDto() {

    }

    public UUID getId() {
        return UserId;
    }

    public void setId(UUID id) {
        this.UserId = id;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
