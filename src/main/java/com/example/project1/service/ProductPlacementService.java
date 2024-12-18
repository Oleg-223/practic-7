package com.example.project1.service;

import com.example.project1.model.ProductPlacementModel;

import java.util.List;

public interface ProductPlacementService {
    List<ProductPlacementModel> findAllProductPlacement();
    ProductPlacementModel findProductPlacementById(Long id);
    ProductPlacementModel createProductPlacement(ProductPlacementModel productPlacement);
    ProductPlacementModel updateProductPlacement(ProductPlacementModel productPlacement);
    void deleteProductPlacement(Long id);

    List<ProductPlacementModel> findPlacementsByProductId(Long productId);
}
