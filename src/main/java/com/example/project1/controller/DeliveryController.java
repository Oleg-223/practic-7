package com.example.project1.controller;

import com.example.project1.model.DeliveryModel;
import com.example.project1.model.ProductModel;
import com.example.project1.service.DeliveryService;
import com.example.project1.service.ProductService;
import com.example.project1.service.SupplierService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final ProductService productService;
    private final SupplierService supplierService;

    public DeliveryController(DeliveryService deliveryService, ProductService productService, SupplierService supplierService) {
        this.deliveryService = deliveryService;
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public String getAllDeliveries(Model model) {
        var deliveries = deliveryService.findAllDelivery();
        model.addAttribute("deliveries", deliveries);
        return "deliveries/index";
    }

    @GetMapping("/create")
    public String getCreateDelivery(Model model) {
        model.addAttribute("deliveryModel", new DeliveryModel());
        model.addAttribute("products", productService.findAllProduct());
        model.addAttribute("suppliers", supplierService.findAllSupplier());
        return "deliveries/create";
    }

    @PostMapping("/create")
    public String postCreateDelivery(
            @ModelAttribute("deliveryModel") DeliveryModel deliveryModel,
            @RequestParam(value = "productIds", required = false) List<Long> productIds,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors() || productIds == null || productIds.isEmpty()) {
            model.addAttribute("products", productService.findAllProduct());
            model.addAttribute("suppliers", supplierService.findAllSupplier());
            return "deliveries/create";
        }

        List<ProductModel> products = productService.findProductsByIds(productIds);
        deliveryModel.setProducts(products);
        deliveryService.createDelivery(deliveryModel);
        return "redirect:/deliveries";
    }


    @GetMapping("/edit/{id}")
    public String editDelivery(@PathVariable("id") Long id, Model model) {
        DeliveryModel delivery = deliveryService.findDeliveryById(id);
        model.addAttribute("deliveryModel", delivery);
        model.addAttribute("products", productService.findAllProduct());
        model.addAttribute("suppliers", supplierService.findAllSupplier());
        return "deliveries/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateDelivery(@PathVariable("id") Long id,
                                 @ModelAttribute("deliveryModel") DeliveryModel deliveryModel,
                                 @RequestParam("productIds") List<Long> productIds,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAllProduct());
            model.addAttribute("suppliers", supplierService.findAllSupplier());
            return "deliveries/edit";
        }

        List<ProductModel> products = productService.findProductsByIds(productIds);
        deliveryModel.setProducts(products);

        DeliveryModel existingDelivery = deliveryService.findDeliveryById(id);
        existingDelivery.setSupplier(deliveryModel.getSupplier());
        existingDelivery.setDeliveryDate(deliveryModel.getDeliveryDate());
        existingDelivery.setProducts(products);

        deliveryService.updateDelivery(existingDelivery);
        return "redirect:/deliveries";
    }

    @GetMapping("/delete/{id}")
    public String deleteDelivery(@PathVariable("id") Long id, Model model) {
        try {
            deliveryService.deleteDelivery(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Невозможно удалить эту поставку, она все еще используется.");
            return "redirect:/deliveries";
        }
        return "redirect:/deliveries";
    }
}
