package com.example.pharmacy.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RefillRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String prescription;
    private LocalDateTime timestamp;



    private LocalDateTime submittedAt;

    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
    }

    // getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getPrescription() { return prescription; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }



    // setters
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPrescription(String prescription) { this.prescription = prescription; }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
