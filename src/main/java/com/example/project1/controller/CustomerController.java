package com.example.project1.controller;

import com.example.project1.model.CustomerModel;
import com.example.project1.service.CustomerService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getAllCustomers(Model model) {
        var customers = customerService.findAllCustomer();
        model.addAttribute("customers", customers);
        return "customers/index";
    }

    @GetMapping("/create")
    public String getCreateCustomer(Model model) {
        model.addAttribute("customerModel", new CustomerModel());
        return "customers/create";
    }

    @PostMapping("/create")
    public String postCreateCustomer(
            @ModelAttribute("customerModel") CustomerModel customerModel,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "customers/create";
        }
        customerService.createCustomer(customerModel);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {
        CustomerModel customer = customerService.findCustomerById(id);
        model.addAttribute("customerModel", customer);
        return "customers/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @ModelAttribute("customerModel") CustomerModel customerModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "customers/edit";
        }

        CustomerModel existingCustomer = customerService.findCustomerById(id);
        existingCustomer.setFirstName(customerModel.getFirstName());
        existingCustomer.setLastName(customerModel.getLastName());
        existingCustomer.setContactInfo(customerModel.getContactInfo());

        customerService.updateCustomer(existingCustomer);

        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        try {
            customerService.deleteCustomer(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Невозможно удалить этого клиента, так как он все еще используется.");
            return "redirect:/customers";
        }
        return "redirect:/customers";
    }
}
