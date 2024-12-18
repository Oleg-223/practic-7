package com.example.project1.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Не может быть пустым")
    private String name;

    @NotBlank(message = "Не может быть пустым")
    private String description;

    @Min(value = 0, message = "Больше 0")
    @Column(scale = 2)
    private BigDecimal price;

    @Min(value = 0, message = "Больше 0")
    private int quantity;

    @NotNull(message = "Не может быть пустым")
    @ManyToOne
    private ColorModel color;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryModel delivery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Не может быть пустым") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Не может быть пустым") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Не может быть пустым") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Не может быть пустым") String description) {
        this.description = description;
    }

    public @Min(value = 0, message = "Больше 0") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@Min(value = 0, message = "Больше 0") BigDecimal price) {
        this.price = price;
    }

    @Min(value = 0, message = "Больше 0")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 0, message = "Больше 0") int quantity) {
        this.quantity = quantity;
    }

    public @NotNull(message = "Не может быть пустым") ColorModel getColor() {
        return color;
    }

    public void setColor(@NotNull(message = "Не может быть пустым") ColorModel color) {
        this.color = color;
    }

}
