package com.smit.homework.secretcontacts.entity;

import jakarta.persistence.*;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Entity representing secret contact stored in the database.
 * 
 * <p>
 * <strong>Author:</strong> Robert Kris Laur
 * </p>
 */
@Entity
@Table(name = "Secret_Contacts")
public class SecretContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String code;

    @JsonProperty("phone_number")
    @Column(nullable = false, name = "phone_number", columnDefinition = "TEXT")
    private String phoneNumber;

    @Column(nullable = false, updatable = false, name = "created_at")
    @JsonProperty("created_at")
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    @JsonIgnore
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
        updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    public void setName(String name) throws IllegalArgumentException {
        if (!isValidInput(name)) {
            throw new IllegalArgumentException("Invalid inputText");
        }
        this.name = name;
    }

    public void setCode(String code) throws IllegalArgumentException {
        if (!isValidInput(code)) {
            throw new IllegalArgumentException("Invalid translation");
        }
        this.code = code;
    }

    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (!isValidInput(phoneNumber)) {
            throw new IllegalArgumentException("Invalid sourceLang");
        }
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private boolean isValidInput(String input) throws IllegalArgumentException {
        return input != null && !input.isEmpty();
    }
}