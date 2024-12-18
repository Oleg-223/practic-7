package com.example.project1.repository;

import com.example.project1.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerModelRepository extends JpaRepository<CustomerModel,Long>  {
}
