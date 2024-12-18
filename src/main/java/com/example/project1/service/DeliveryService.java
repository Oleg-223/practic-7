package com.example.project1.service;

import com.example.project1.model.DeliveryModel;

import java.util.List;

public interface DeliveryService {
    List<DeliveryModel> findAllDelivery();
    DeliveryModel findDeliveryById(Long id);
    DeliveryModel createDelivery(DeliveryModel delivery);
    DeliveryModel updateDelivery(DeliveryModel delivery);
    void deleteDelivery(Long id);
}
