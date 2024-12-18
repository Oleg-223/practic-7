package com.example.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import java.time.LocalDate;

@Entity
@Table(name = "Delivery_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Заказ не может быть пустым")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @NotNull(message = "Клиент не может быть пустым")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @NotNull(message = "Не может быть пустым")
    private LocalDate orderDate;

    @NotNull(message = "Не может быть пустым")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public @NotNull(message = "Не может быть пустым") LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(@NotNull(message = "Не может быть пустым") LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public @NotNull(message = "Не может быть пустым") String getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Не может быть пустым") String status) {
        this.status = status;
    }
}
