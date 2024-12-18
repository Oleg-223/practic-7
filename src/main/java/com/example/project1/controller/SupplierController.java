package com.example.project1.controller;

import com.example.project1.model.SupplierModel;
import com.example.project1.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public String getAllSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.findAllSupplier());
        return "suppliers/index";
    }

    @GetMapping("/create")
    public String getCreateSupplierForm(Model model) {
        model.addAttribute("supplier", new SupplierModel());
        return "suppliers/create";
    }

    @PostMapping("/create")
    public String createSupplier(@ModelAttribute("supplier") SupplierModel supplier) {
        supplierService.createSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String getEditSupplierForm(@PathVariable("id") Long id, Model model) {
        SupplierModel supplier = supplierService.findSupplierById(id);
        model.addAttribute("supplier", supplier);
        return "suppliers/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateSupplier(@PathVariable("id") Long id, @ModelAttribute("supplier") SupplierModel supplierModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "suppliers/edit";
        }

        SupplierModel existingSupplier = supplierService.findSupplierById(id);
        existingSupplier.setName(supplierModel.getName());
        existingSupplier.setContactInfo(supplierModel.getContactInfo());
        existingSupplier.setAddress(supplierModel.getAddress());

        supplierService.updateSupplier(existingSupplier);

        return "redirect:/suppliers";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id, Model model) {
        try {
            supplierService.deleteSupplier(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Невозможно удалить этого поставщика, так как он все еще используется.");
        }
        return "redirect:/suppliers";
    }

}
