    package com.example.project1.model;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.Min;
    import jakarta.validation.constraints.NotNull;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Table(name = "orders_products")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class OrderProductModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull(message = "Не может быть пустым")
        @ManyToOne
        private ProductModel product;

        @Min(value = 1, message = "Количество должно быть больше 0")
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

        @Min(value = 1, message = "Количество должно быть больше 0")
        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(@Min(value = 1, message = "Количество должно быть больше 0") int quantity) {
            this.quantity = quantity;
        }
    }
