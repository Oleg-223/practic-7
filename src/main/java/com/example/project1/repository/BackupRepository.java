package com.example.project1.repository;

import com.example.project1.model.BackupModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupRepository extends JpaRepository<BackupModel, Long> {
}
