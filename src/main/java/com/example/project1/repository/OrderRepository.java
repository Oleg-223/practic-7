package com.example.project1.repository;

import com.example.project1.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Long> {
    List<OrderModel> findByCustomerId(Long customerId);
}
