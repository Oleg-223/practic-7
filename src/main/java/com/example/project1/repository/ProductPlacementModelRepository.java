package com.example.project1.repository;

import com.example.project1.model.ProductPlacementModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPlacementModelRepository extends JpaRepository<ProductPlacementModel,Long> {
    List<ProductPlacementModel> findByProductId(Long productId);
}
