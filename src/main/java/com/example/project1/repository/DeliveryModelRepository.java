package com.example.project1.repository;

import com.example.project1.model.DeliveryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryModelRepository extends JpaRepository<DeliveryModel,Long> {
}
