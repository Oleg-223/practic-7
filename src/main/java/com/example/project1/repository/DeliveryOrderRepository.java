package com.example.project1.repository;

import com.example.project1.model.DeliveryOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrderModel, Long> {
}
