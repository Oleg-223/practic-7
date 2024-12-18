package com.example.project1.service;

import com.example.project1.model.ProductPlacementModel;
import com.example.project1.repository.ProductPlacementModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPlacementServiceImpl implements ProductPlacementService {
    private final ProductPlacementModelRepository productPlacementModelRepository;

    public ProductPlacementServiceImpl(ProductPlacementModelRepository productPlacementModelRepository) {
        this.productPlacementModelRepository = productPlacementModelRepository;
    }

    @Override
    public List<ProductPlacementModel> findAllProductPlacement() {
        return productPlacementModelRepository.findAll();
    }

    @Override
    public ProductPlacementModel findProductPlacementById(Long id) {
        return productPlacementModelRepository.findById(id).orElse(null);
    }

    @Override
    public ProductPlacementModel createProductPlacement(ProductPlacementModel productPlacement) {
        return productPlacementModelRepository.save(productPlacement);
    }

    @Override
    public ProductPlacementModel updateProductPlacement(ProductPlacementModel productPlacement) {
        return productPlacementModelRepository.save(productPlacement);
    }

    @Override
    public void deleteProductPlacement(Long id) {
        productPlacementModelRepository.deleteById(id);
    }

    @Override
    public List<ProductPlacementModel> findPlacementsByProductId(Long productId) {
        return productPlacementModelRepository.findByProductId(productId);
    }
}
