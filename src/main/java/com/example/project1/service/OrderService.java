package com.example.project1.service;

import com.example.project1.model.OrderModel;

import java.util.List;

public interface OrderService {
    List<OrderModel> findAll();
    OrderModel findById(Long id);
    OrderModel create(OrderModel orderModel);
    OrderModel update(OrderModel orderModel);
    List<OrderModel> findByCustomerId(Long customerId);
    void delete(Long id);

}
