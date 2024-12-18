package com.example.project1.controller;

import com.example.project1.model.OrderModel;
import com.example.project1.service.OrderService;
import com.example.project1.service.CustomerService;
import com.example.project1.service.OrderProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final OrderProductService orderProductService;

    public OrderController(OrderService orderService, CustomerService customerService, OrderProductService orderProductService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderProductService = orderProductService;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        List<OrderModel> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders/index";
    }

    @GetMapping("/create")
    public String getCreateOrder(Model model) {
        model.addAttribute("orderModel", new OrderModel());
        model.addAttribute("customers", customerService.findAllCustomer());
        model.addAttribute("orderProducts", orderProductService.findAll());
        return "orders/create";
    }

    @PostMapping("/create")
    public String postCreateOrder(
            @ModelAttribute("orderModel") OrderModel orderModel,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "orders/create";
        }

        if (orderModel.getOrderDate() == null) {
            orderModel.setOrderDate(LocalDate.now());
        }

        orderService.create(orderModel);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
        OrderModel order = orderService.findById(id);
        model.addAttribute("orderModel", order);
        model.addAttribute("customers", customerService.findAllCustomer());
        model.addAttribute("orderProducts", orderProductService.findAll());
        return "orders/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable("id") Long id,
                              @ModelAttribute("orderModel") OrderModel orderModel,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "orders/edit";
        }

        if (orderModel.getOrderDate() == null) {
            orderModel.setOrderDate(orderService.findById(id).getOrderDate());
        }

        if (orderModel.getOrderDate().isBefore(LocalDate.now())) {
            return "redirect:/orders/edit/" + id;
        }

        orderModel.setId(id);
        orderService.update(orderModel);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id, Model model) {
        try {
            orderService.delete(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Невозможно удалить этот заказ, так как он все еще используется.");
            return "redirect:/orders";
        }
        return "redirect:/orders";
    }
}
