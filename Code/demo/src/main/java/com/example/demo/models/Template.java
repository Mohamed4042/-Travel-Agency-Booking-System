package com.example.demo.models;

import java.util.List;

public class Template {
    private String templateId;
    private String subject;
    private String body;
    private List<String> placeholders;
    private String language;

    public Template(String templateId, String subject, String body, List<String> placeholders, String language) {
        this.templateId = templateId;
        this.subject = subject;
        this.body = body;
        this.placeholders = placeholders;
        this.language = language;
    }

    // Getters and setters
    public String getTemplateId() { return templateId; }
    public void setTemplateId(String templateId) { this.templateId = templateId; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public List<String> getPlaceholders() { return placeholders; }
    public void setPlaceholders(List<String> placeholders) { this.placeholders = placeholders; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
