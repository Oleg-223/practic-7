package com.example.project1.service;

import com.example.project1.model.ProductModel;
import com.example.project1.model.ProductPlacementModel;
import com.example.project1.repository.ProductModelRepository;
import com.example.project1.repository.ProductPlacementModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductModelRepository productModelRepository;
    private final ProductPlacementModelRepository productPlacementModelRepository;

    public ProductServiceImpl(ProductModelRepository productModelRepository,
                              ProductPlacementModelRepository productPlacementModelRepository) {
        this.productModelRepository = productModelRepository;
        this.productPlacementModelRepository = productPlacementModelRepository;
    }

    @Override
    public List<ProductModel> findAllProduct() {
        return productModelRepository.findAll();
    }

    @Override
    public List<ProductModel> findProductsByIds(List<Long> ids) {
        return productModelRepository.findAllById(ids);
    }

    @Override
    public ProductModel findProductById(Long id) {
        return productModelRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public ProductModel createProduct(ProductModel product) {
        return productModelRepository.save(product);
    }

    @Override
    public ProductModel updateProduct(ProductModel product) {
        return productModelRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productModelRepository.deleteById(id);
    }

    public List<ProductPlacementModel> findPlacementsByProductId(Long productId) {
        return productPlacementModelRepository.findByProductId(productId);
    }

}
