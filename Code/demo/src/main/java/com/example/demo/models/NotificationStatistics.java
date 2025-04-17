package com.example.demo.models;

import java.util.List;

public class NotificationStatistics {
    private int totalSent;
    private int totalFailed;
    private List<String> failureReasons;
    private String mostSentTemplate;  // Change this to String instead of Template

    // Constructor with String for mostSentTemplate
    public NotificationStatistics(int totalSent, int totalFailed, List<String> failureReasons, String mostSentTemplate) {
        this.totalSent = totalSent;
        this.totalFailed = totalFailed;
        this.failureReasons = failureReasons;
        this.mostSentTemplate = mostSentTemplate;
    }

    // Getters and Setters
    public int getTotalSent() {
        return totalSent;
    }

    public void setTotalSent(int totalSent) {
        this.totalSent = totalSent;
    }

    public int getTotalFailed() {
        return totalFailed;
    }

    public void setTotalFailed(int totalFailed) {
        this.totalFailed = totalFailed;
    }

    public List<String> getFailureReasons() {
        return failureReasons;
    }

    public void setFailureReasons(List<String> failureReasons) {
        this.failureReasons = failureReasons;
    }

    public String getMostSentTemplate() {  // Getter for String
        return mostSentTemplate;
    }

    public void setMostSentTemplate(String mostSentTemplate) {
        this.mostSentTemplate = mostSentTemplate;
    }

    // Method to generate report
    public void generateReport() {
        System.out.println("Total Sent: " + totalSent);
        System.out.println("Total Failed: " + totalFailed);
        System.out.println("Failure Reasons: " + failureReasons);
        System.out.println("Most Sent Template: " + (mostSentTemplate != null ? mostSentTemplate : "None"));
    }
}
