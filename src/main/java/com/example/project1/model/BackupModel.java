package com.example.project1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "backups")
public class BackupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private String path;

    public BackupModel() {}

    public BackupModel(LocalDateTime createdAt, String path) {
        this.createdAt = createdAt;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
