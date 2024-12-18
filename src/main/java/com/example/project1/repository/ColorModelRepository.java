package com.example.project1.repository;

import com.example.project1.model.ColorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorModelRepository extends JpaRepository<ColorModel,Long> {
}
