package com.example.project1.service;

import com.example.project1.model.OrderProductModel;
import com.example.project1.repository.OrderProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public List<OrderProductModel> findAll() {
        return orderProductRepository.findAll();
    }

    @Override
    public OrderProductModel findById(Long id) {
        return orderProductRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Product not found"));
    }

    @Override
    public OrderProductModel create(OrderProductModel orderProductModel) {
        return orderProductRepository.save(orderProductModel);
    }

    @Override
    public OrderProductModel update(OrderProductModel orderProductModel) {
        return orderProductRepository.save(orderProductModel);
    }

    @Override
    public void delete(Long id) {
        orderProductRepository.deleteById(id);
    }
}
