package com.cviac.s4iApp.datamodel;

/**
 * Created by john on 1/10/2017.
 */

public class EmailInfo {
    private String email;
    private String subject;
    private String message;

    public EmailInfo(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
