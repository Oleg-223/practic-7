package com.example.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="storage_cells")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageCellModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Номер должен быть не меньше 1")
    private int cellNumber;

    @NotBlank(message = "Место нужно заполнить")
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Min(value = 1, message = "Номер должен быть не меньше 1")
    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(@Min(value = 1, message = "Номер должен быть не меньше 1") int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public @NotBlank(message = "Место нужно заполнить") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Место нужно заполнить") String location) {
        this.location = location;
    }
}
