package com.example.project1.service;

import com.example.project1.model.DeliveryOrderModel;

import java.util.List;

public interface DeliveryOrderService {
    List<DeliveryOrderModel> findAll();
    DeliveryOrderModel findById(Long id);
    DeliveryOrderModel create(DeliveryOrderModel deliveryOrderModel);
    void update(DeliveryOrderModel deliveryOrderModel);
    void delete(Long id);
}
