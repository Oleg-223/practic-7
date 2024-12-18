package com.example.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Не может быть пустым")
    private String firstName;

    @NotBlank(message = "Не может быть пустым")
    private String lastName;

    @NotBlank(message = "Не может быть пустым")
    private String contactInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Не может быть пустым") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "Не может быть пустым") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Не может быть пустым") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Не может быть пустым") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "Не может быть пустым") String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(@NotBlank(message = "Не может быть пустым") String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
