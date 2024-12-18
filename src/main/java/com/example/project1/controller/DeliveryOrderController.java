package com.example.project1.controller;

import com.example.project1.model.DeliveryOrderModel;
import com.example.project1.model.OrderModel;
import com.example.project1.model.CustomerModel;
import com.example.project1.model.UserModel;
import com.example.project1.service.DeliveryOrderService;
import com.example.project1.service.OrderService;
import com.example.project1.service.CustomerService;
import com.example.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/delivery-orders")
public class DeliveryOrderController {


    private final DeliveryOrderService deliveryOrderService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final UserService userService;

    @Autowired
    public DeliveryOrderController(DeliveryOrderService deliveryOrderService, OrderService orderService,
                                   CustomerService customerService, UserService userService) {
        this.deliveryOrderService = deliveryOrderService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllDeliveryOrders(Model model) {
        List<DeliveryOrderModel> deliveryOrders = deliveryOrderService.findAll();
        model.addAttribute("deliveryOrders", deliveryOrders);
        return "deliveryOrders/index";
    }

    @GetMapping("/create")
    public String getCreateDeliveryOrder(Model model) {
        List<CustomerModel> customers = customerService.findAllCustomer();
        model.addAttribute("deliveryOrderModel", new DeliveryOrderModel());
        model.addAttribute("customers", customers);
        model.addAttribute("orders", List.of());
        return "deliveryOrders/create";
    }

    @PostMapping("/create")
    public String postCreateDeliveryOrder(@ModelAttribute("deliveryOrderModel") DeliveryOrderModel deliveryOrderModel,
                                          @AuthenticationPrincipal UserDetails currentUser,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "deliveryOrders/create";
        }

        if (currentUser != null) {
            UserModel user = userService.findByUsername(currentUser.getUsername());
            deliveryOrderModel.setUser(user);
        }

        if (deliveryOrderModel.getOrderDate() == null) {
            deliveryOrderModel.setOrderDate(LocalDate.now());
        }

        if (deliveryOrderModel.getOrder() == null ||
                deliveryOrderModel.getOrder().getOrderDate().isAfter(deliveryOrderModel.getOrderDate())) {
            return "redirect:/delivery-orders/create";
        }

        deliveryOrderService.create(deliveryOrderModel);
        return "redirect:/delivery-orders";
    }

    @GetMapping("/edit/{id}")
    public String editDeliveryOrder(@PathVariable("id") Long id, Model model) {
        DeliveryOrderModel deliveryOrder = deliveryOrderService.findById(id);
        if (deliveryOrder == null) {
            return "redirect:/delivery-orders";
        }
        List<OrderModel> orders = orderService.findByCustomerId(deliveryOrder.getOrder().getCustomer().getId());
        List<CustomerModel> customers = customerService.findAllCustomer();
        model.addAttribute("deliveryOrderModel", deliveryOrder);
        model.addAttribute("orders", orders);
        model.addAttribute("customers", customers);
        return "deliveryOrders/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateDeliveryOrder(@PathVariable("id") Long id,
                                      @ModelAttribute("deliveryOrderModel") DeliveryOrderModel deliveryOrderModel,
                                      @AuthenticationPrincipal UserDetails currentUser,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "deliveryOrders/edit";
        }

        DeliveryOrderModel existingOrder = deliveryOrderService.findById(id);
        if (existingOrder == null) {
            return "redirect:/delivery-orders";
        }

        deliveryOrderModel.setCustomer(existingOrder.getCustomer());
        deliveryOrderModel.setUser(userService.findByUsername(currentUser.getUsername()));

        if (deliveryOrderModel.getOrder() == null || deliveryOrderModel.getOrder().getId() == null) {
            bindingResult.rejectValue("order", "order.empty", "Пожалуйста, выберите заказ");
            return "deliveryOrders/edit";
        }

        deliveryOrderModel.setId(id);
        deliveryOrderService.update(deliveryOrderModel);
        return "redirect:/delivery-orders";
    }




    @GetMapping("/delete/{id}")
    public String deleteDeliveryOrder(@PathVariable("id") Long id, Model model) {
        try {
            deliveryOrderService.delete(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Невозможно удалить этот заказ на доставку, так как он все еще используется.");
            return "redirect:/delivery-orders";
        }
        return "redirect:/delivery-orders";
    }

    @GetMapping("/orders/{customerId}")
    @ResponseBody
    public List<OrderModel> getOrdersByCustomer(@PathVariable Long customerId) {
        List<OrderModel> orders = orderService.findByCustomerId(customerId);
        System.out.println("Количество заказов для клиента " + customerId + ": " + orders.size());
        return orders;
    }
}
