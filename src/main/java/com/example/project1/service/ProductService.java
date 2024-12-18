package com.example.project1.service;

import com.example.project1.model.ProductModel;
import com.example.project1.model.ProductPlacementModel;

import java.util.List;

public interface ProductService {
    List<ProductModel> findAllProduct();
    ProductModel findProductById(Long id);
    List<ProductModel> findProductsByIds(List<Long> ids); // Новый метод
    ProductModel createProduct(ProductModel order);
    ProductModel updateProduct(ProductModel order);
    void deleteProduct(Long id);
    List<ProductPlacementModel> findPlacementsByProductId(Long productId);
}
