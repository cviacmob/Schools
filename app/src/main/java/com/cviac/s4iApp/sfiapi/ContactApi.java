package com.cviac.s4iApp.sfiapi;

/**
 * Created by john on 12/29/2016.
 */

public class ContactApi {
    private String Name;
    private String Mail;
    private String Form;
    private String Subject;
    private String Messages;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getForm() {
        return Form;
    }

    public void setForm(String form) {
        Form = form;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMessages() {
        return Messages;
    }

    public void setMessages(String messages) {
        Messages = messages;
    }


}

