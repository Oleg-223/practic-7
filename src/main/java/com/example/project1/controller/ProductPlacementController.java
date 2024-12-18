package com.example.project1.controller;

import com.example.project1.model.ProductModel;
import com.example.project1.model.ProductPlacementModel;
import com.example.project1.service.ProductPlacementService;
import com.example.project1.service.ProductService;
import com.example.project1.service.StorageCellService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product-placements")
public class ProductPlacementController {

    private final ProductPlacementService productPlacementService;
    private final ProductService productService;
    private final StorageCellService storageCellService;

    public ProductPlacementController(
            ProductPlacementService productPlacementService,
            ProductService productService,
            StorageCellService storageCellService
    ) {
        this.productPlacementService = productPlacementService;
        this.productService = productService;
        this.storageCellService = storageCellService;
    }

    @GetMapping
    public String getAllProductPlacements(Model model) {
        var productPlacements = productPlacementService.findAllProductPlacement();
        model.addAttribute("productPlacements", productPlacements);
        return "product-placements/index";
    }

    @GetMapping("/create")
    public String getCreateProductPlacement(Model model) {
        model.addAttribute("productPlacementModel", new ProductPlacementModel());
        model.addAttribute("products", productService.findAllProduct());
        model.addAttribute("storageCells", storageCellService.findAllStorageCell());
        return "product-placements/create";
    }

    @PostMapping("/create")
    public String postCreateProductPlacement(
            @ModelAttribute("productPlacementModel") ProductPlacementModel productPlacementModel,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAllProduct());
            model.addAttribute("storageCells", storageCellService.findAllStorageCell());
            return "product-placements/create";
        }

        productPlacementService.createProductPlacement(productPlacementModel);

        updateProductQuantity(productPlacementModel.getProduct().getId());

        return "redirect:/product-placements";
    }


    @GetMapping("/edit/{id}")
    public String editProductPlacement(@PathVariable("id") Long id, Model model) {
        ProductPlacementModel productPlacement = productPlacementService.findProductPlacementById(id);
        model.addAttribute("productPlacementModel", productPlacement);
        model.addAttribute("products", productService.findAllProduct());
        model.addAttribute("storageCells", storageCellService.findAllStorageCell());
        return "product-placements/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProductPlacement(@PathVariable("id") Long id,
                                         @ModelAttribute("productPlacementModel") ProductPlacementModel productPlacementModel,
                                         BindingResult result,
                                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAllProduct());
            model.addAttribute("storageCells", storageCellService.findAllStorageCell());
            return "product-placements/edit";
        }

        ProductPlacementModel existingProductPlacement = productPlacementService.findProductPlacementById(id);
        existingProductPlacement.setProduct(productPlacementModel.getProduct());
        existingProductPlacement.setStorageCell(productPlacementModel.getStorageCell());
        existingProductPlacement.setQuantity(productPlacementModel.getQuantity());

        productPlacementService.updateProductPlacement(existingProductPlacement);

        updateProductQuantity(existingProductPlacement.getProduct().getId());

        return "redirect:/product-placements";
    }

    private void updateProductQuantity(Long productId) {
        List<ProductPlacementModel> placements = productPlacementService.findPlacementsByProductId(productId);

        int totalQuantity = placements.stream().mapToInt(ProductPlacementModel::getQuantity).sum();

        ProductModel product = productService.findProductById(productId);
        product.setQuantity(totalQuantity);

        productService.updateProduct(product);
    }

@GetMapping("/delete/{id}")
public String deleteProductPlacement(@PathVariable("id") Long id, Model model) {
    try {
        ProductPlacementModel productPlacement = productPlacementService.findProductPlacementById(id);
        updateProductQuantity(productPlacement.getProduct().getId(), productPlacement.getQuantity());
        productPlacementService.deleteProductPlacement(id);
    } catch (DataIntegrityViolationException e) {
        model.addAttribute("error", "Невозможно удалить это размещение продукта, так как оно все еще используется.");
    }
    return "redirect:/product-placements";
}

    private void updateProductQuantity(Long productId, int quantity) {
        List<ProductPlacementModel> placements = productPlacementService.findPlacementsByProductId(productId);

        if (quantity <= 0) {
            return;
        }

        int totalQuantity = placements.stream().mapToInt(ProductPlacementModel::getQuantity).sum() - quantity;

        ProductModel product = productService.findProductById(productId);
        product.setQuantity(totalQuantity);

        productService.updateProduct(product);
    }

}
