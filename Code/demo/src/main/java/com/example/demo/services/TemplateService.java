package com.example.demo.services;

import com.example.demo.models.Template;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TemplateService {
    private Map<String, Template> templates = new HashMap<>();

    // Create a new template
    public void createTemplate(Template template) {
        templates.put(template.getTemplateId(), template);
    }

    // Update an existing template
    public void updateTemplate(String templateId, Template template) {
        if (!templates.containsKey(templateId)) {
            throw new RuntimeException("Template not found");
        }
        templates.put(templateId, template);
    }

    // Get a template by ID
    public Template getTemplate(String templateId) {
        return templates.get(templateId);
    }
}
