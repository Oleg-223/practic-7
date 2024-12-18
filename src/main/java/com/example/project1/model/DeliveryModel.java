package com.example.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Не может быть пустым")
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierModel supplier;

    @NotNull(message = "Не может быть пустым")
    private LocalDate deliveryDate;

    @ManyToMany
    @JoinTable(
            name = "delivery_products",
            joinColumns = @JoinColumn(name = "delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductModel> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Не может быть пустым") SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(@NotNull(message = "Не может быть пустым") SupplierModel supplier) {
        this.supplier = supplier;
    }

    public @NotNull(message = "Не может быть пустым") LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(@NotNull(message = "Не может быть пустым") LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
