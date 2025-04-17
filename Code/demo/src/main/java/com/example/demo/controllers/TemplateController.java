package com.example.demo.controllers;

import com.example.demo.models.Template;
import com.example.demo.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    // Create a new template
    @PostMapping
    public void createTemplate(@RequestBody Template template) {
        templateService.createTemplate(template);
    }

    // Update an existing template
    @PutMapping("/{templateId}")
    public void updateTemplate(@PathVariable String templateId, @RequestBody Template template) {
        templateService.updateTemplate(templateId, template);
    }

    // Retrieve a template by ID
    @GetMapping("/{templateId}")
    public Template getTemplate(@PathVariable String templateId) {
        return templateService.getTemplate(templateId);
    }
}
