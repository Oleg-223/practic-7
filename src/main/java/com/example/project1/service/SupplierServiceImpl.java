package com.example.project1.service;

import com.example.project1.model.SupplierModel;
import com.example.project1.repository.SupplierModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierModelRepository supplierModelRepository;

    public SupplierServiceImpl(SupplierModelRepository supplierModelRepository) {
        this.supplierModelRepository = supplierModelRepository;
    }

    @Override
    public List<SupplierModel> findAllSupplier() {
        return supplierModelRepository.findAll();
    }

    @Override
    public SupplierModel findSupplierById(Long id) {
        return supplierModelRepository.findById(id).get();
    }

    @Override
    public SupplierModel createSupplier(SupplierModel supplier) {
        return supplierModelRepository.save(supplier);
    }

    @Override
    public SupplierModel updateSupplier(SupplierModel supplier) {
        return supplierModelRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierModelRepository.deleteById(id);
    }
}
