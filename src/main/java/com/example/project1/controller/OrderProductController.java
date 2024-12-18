    package com.example.project1.controller;

    import com.example.project1.model.OrderProductModel;
    import com.example.project1.model.ProductModel;
    import com.example.project1.service.OrderProductService;
    import com.example.project1.service.ProductService;
    import org.springframework.dao.DataIntegrityViolationException;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @Controller
    @RequestMapping("/order-products")
    public class OrderProductController {

        private final OrderProductService orderProductService;
        private final ProductService productService;

        public OrderProductController(OrderProductService orderProductService, ProductService productService) {
            this.orderProductService = orderProductService;
            this.productService = productService;
        }

        @GetMapping
        public String getAllOrderProducts(Model model) {
            List<OrderProductModel> orderProducts = orderProductService.findAll();
            model.addAttribute("orderProducts", orderProducts);
            return "orderProducts/index";
        }

        @GetMapping("/create")
        public String getCreateOrderProduct(Model model) {
            model.addAttribute("orderProductModel", new OrderProductModel());
            model.addAttribute("products", productService.findAllProduct());
            return "orderProducts/create";
        }

        @PostMapping("/create")
        public String postCreateOrderProduct(
                @ModelAttribute("orderProductModel") OrderProductModel orderProductModel,
                BindingResult result
        ) {
            if (result.hasErrors()) {
                return "orderProducts/create";
            }

            if (orderProductModel.getProduct() == null || orderProductModel.getProduct().getId() == null) {
                result.rejectValue("product", "error.orderProductModel", "Продукт должен быть выбран");
                return "orderProducts/create";
            }

            int availableQuantity = orderProductModel.getProduct().getQuantity();
            if (orderProductModel.getQuantity() > availableQuantity) {
                result.rejectValue("quantity", "error.orderProductModel", "Недостаточно доступного количества продукта");
                return "orderProducts/create";
            }

            orderProductService.create(orderProductModel);

            updateProductQuantity(orderProductModel.getProduct().getId(), -orderProductModel.getQuantity());

            return "redirect:/order-products";
        }

        @GetMapping("/edit/{id}")
        public String editOrderProduct(@PathVariable("id") Long id, Model model) {
            OrderProductModel orderProduct = orderProductService.findById(id);
            model.addAttribute("orderProductModel", orderProduct);
            model.addAttribute("products", productService.findAllProduct());
            return "orderProducts/edit";
        }

        @PostMapping("/edit/{id}")
        public String updateOrderProduct(@PathVariable("id") Long id,
                                         @ModelAttribute("orderProductModel") OrderProductModel orderProductModel,
                                         BindingResult result) {
            if (result.hasErrors()) {
                return "orderProducts/edit";
            }

            OrderProductModel existingOrderProduct = orderProductService.findById(id);
            int existingQuantity = existingOrderProduct.getQuantity();
            int newQuantity = orderProductModel.getQuantity();

            if (newQuantity != existingQuantity) {
                int availableQuantity = orderProductModel.getProduct().getQuantity();
                if (newQuantity > availableQuantity) {
                    result.rejectValue("quantity", "error.orderProductModel", "Недостаточно доступного количества продукта");
                    return "orderProducts/edit";
                }

                int quantityDifference = newQuantity - existingQuantity;
                updateProductQuantity(orderProductModel.getProduct().getId(), -quantityDifference);
            }

            orderProductModel.setId(id);
            orderProductService.update(orderProductModel);
            return "redirect:/order-products";
        }

        private void updateProductQuantity(Long productId, int quantityChange) {
            ProductModel product = productService.findProductById(productId);
            product.setQuantity(product.getQuantity() + quantityChange);
            productService.updateProduct(product);
        }

        @GetMapping("/delete/{id}")
        public String deleteOrderProduct(@PathVariable("id") Long id, Model model) {
            try {
                OrderProductModel orderProduct = orderProductService.findById(id);
                updateProductQuantity(orderProduct.getProduct().getId(), orderProduct.getQuantity());
                orderProductService.delete(id);
            } catch (DataIntegrityViolationException e) {
                model.addAttribute("error", "Невозможно удалить этот продукт заказа, так как он все еще используется.");
            }
            return "redirect:/order-products";
        }
    }
