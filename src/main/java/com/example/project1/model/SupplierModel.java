package com.example.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название не должно быть пустым")
    private String name;

    @NotBlank(message = "Контактная информация не должнао быть пустой")
    private String contactInfo;

    @NotBlank(message = "Адрес не должен быть пустым")
    private String address;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryModel delivery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Название не должно быть пустым") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Название не должно быть пустым") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Контактная информация не должнао быть пустой") String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(@NotBlank(message = "Контактная информация не должнао быть пустой") String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public @NotBlank(message = "Адрес не должен быть пустым") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Адрес не должен быть пустым") String address) {
        this.address = address;
    }
}
