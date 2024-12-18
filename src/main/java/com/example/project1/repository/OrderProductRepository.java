package com.example.project1.repository;

import com.example.project1.model.OrderProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductModel,Long> {
}
