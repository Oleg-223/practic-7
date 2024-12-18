package com.example.project1.service;

import com.example.project1.model.OrderProductModel;

import java.util.List;

public interface OrderProductService {
    List<OrderProductModel> findAll();
    OrderProductModel findById(Long id);
    OrderProductModel create(OrderProductModel orderProductModel);
    OrderProductModel update(OrderProductModel orderProductModel);
    void delete(Long id);
}
