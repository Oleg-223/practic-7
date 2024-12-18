package com.example.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="colors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Не может быть пустым")
    private String colorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Не может быть пустым") String getColorName() {
        return colorName;
    }

    public void setColorName(@NotBlank(message = "Не может быть пустым") String colorName) {
        this.colorName = colorName;
    }
}
