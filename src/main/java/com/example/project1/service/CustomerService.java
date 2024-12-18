package com.example.project1.service;

import com.example.project1.model.CustomerModel;

import java.util.List;

public interface CustomerService {
    List<CustomerModel> findAllCustomer();
    CustomerModel findCustomerById(Long id);
    CustomerModel createCustomer(CustomerModel customer);
    CustomerModel updateCustomer(CustomerModel customer);
    void deleteCustomer(Long id);
}
