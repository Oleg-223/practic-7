package com.example.project1.service;

import com.example.project1.model.SupplierModel;

import java.util.List;

public interface SupplierService {
    List<SupplierModel> findAllSupplier();
    SupplierModel findSupplierById(Long id);
    SupplierModel createSupplier(SupplierModel supplier);
    SupplierModel updateSupplier(SupplierModel supplier);
    void deleteSupplier(Long id);
}
