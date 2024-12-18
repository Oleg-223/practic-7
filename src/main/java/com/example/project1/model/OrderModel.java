package com.example.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CustomerModel customer;

    @NotNull(message = "Не может быть пустым")
    @ManyToMany
    private List<OrderProductModel> order_products;

    @NotNull(message = "Не может быть пустым")
    private LocalDate orderDate;

    public String getProductsList() {
        return order_products.stream()
                .map(orderProduct -> orderProduct.getProduct().getName() + " (количество: " + orderProduct.getQuantity() + ")")
                .collect(Collectors.joining(", "));
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Не может быть пустым") CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(@NotNull(message = "Не может быть пустым") CustomerModel customer) {
        this.customer = customer;
    }

    public @NotNull(message = "Не может быть пустым") List<OrderProductModel> getOrder_products() {
        return order_products;
    }

    public void setOrder_products(@NotNull(message = "Не может быть пустым") List<OrderProductModel> order_products) {
        this.order_products = order_products;
    }

    public @NotNull(message = "Не может быть пустым") LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(@NotNull(message = "Не может быть пустым") LocalDate orderDate) {
        this.orderDate = orderDate;
    }

}
