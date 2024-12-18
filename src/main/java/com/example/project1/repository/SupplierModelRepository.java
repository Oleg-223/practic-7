package com.example.project1.repository;

import com.example.project1.model.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierModelRepository extends JpaRepository<SupplierModel,Long> {
}
