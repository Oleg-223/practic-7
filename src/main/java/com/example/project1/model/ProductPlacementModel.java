package com.example.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="product_placements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPlacementModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Не может быть пустым")
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductModel product;

    @NotNull(message = "Не может быть пустым")
    @ManyToOne(cascade = CascadeType.ALL)
    private StorageCellModel storageCell;

    @Min(value = 1, message = "Не меньше 1")
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Не может быть пустым") ProductModel getProduct() {
        return product;
    }

    public void setProduct(@NotNull(message = "Не может быть пустым") ProductModel product) {
        this.product = product;
    }

    public @NotNull(message = "Не может быть пустым") StorageCellModel getStorageCell() {
        return storageCell;
    }

    public void setStorageCell(@NotNull(message = "Не может быть пустым") StorageCellModel storageCell) {
        this.storageCell = storageCell;
    }

    @Min(value = 1, message = "Не меньше 1")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 1, message = "Не меньше 1") int quantity) {
        this.quantity = quantity;
    }
}
