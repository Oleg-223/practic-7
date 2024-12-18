package com.example.project1.repository;

import com.example.project1.model.StorageCellModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageCellModelRepository extends JpaRepository<StorageCellModel,Long> {
}
