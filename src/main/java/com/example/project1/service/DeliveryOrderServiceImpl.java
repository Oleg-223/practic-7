package com.example.project1.service;

import com.example.project1.model.DeliveryOrderModel;
import com.example.project1.repository.DeliveryOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryOrderServiceImpl implements DeliveryOrderService {
    private final DeliveryOrderRepository deliveryOrderRepository;

    public DeliveryOrderServiceImpl(DeliveryOrderRepository deliveryOrderRepository) {
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Override
    public List<DeliveryOrderModel> findAll() {
        return deliveryOrderRepository.findAll();
    }

    @Override
    public DeliveryOrderModel findById(Long id) {
        return deliveryOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Delivery Order not found"));
    }

    @Override
    public DeliveryOrderModel create(DeliveryOrderModel deliveryOrderModel) {
        return deliveryOrderRepository.save(deliveryOrderModel);
    }


    @Override
    public void update(DeliveryOrderModel deliveryOrderModel) {
        deliveryOrderRepository.save(deliveryOrderModel);
    }

    @Override
    public void delete(Long id) {
        deliveryOrderRepository.deleteById(id);
    }
}
