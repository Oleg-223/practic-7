package com.example.project1.service;

import com.example.project1.model.OrderModel;
import com.example.project1.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderModel findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
    @Override
    public OrderModel create(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }

    @Override
    public OrderModel update(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }

    @Override
    public List<OrderModel> findByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
