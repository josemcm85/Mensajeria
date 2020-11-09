package com.r6.service;

import java.io.File;
import java.util.ArrayList;

public class Correo {
	private String emailSender;
    private String password;
    private String emailReceiver;
    private String subject;
    private String body;

    private ArrayList<File> attachedFiles;

    public Correo(String emailSender, String password, String emailReceiver, String subject, String body, ArrayList<File> attachedFiles) {
        this.emailSender = emailSender;
        this.password = password;
        this.emailReceiver = emailReceiver;
        this.subject = subject;
        this.body = body;
        this.attachedFiles = attachedFiles;
    }

    public ArrayList<File> getAttachedFiles() {
        return attachedFiles;
    }

    public void setAttachedFiles(ArrayList<File> attachedFiles) {
        this.attachedFiles = attachedFiles;
    }
    
    
    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emaiulSender) {
        this.emailSender = emaiulSender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailReceiver() {
        return emailReceiver;
    }

    public void setEmailReceiver(String emailReceiver) {
        this.emailReceiver = emailReceiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}