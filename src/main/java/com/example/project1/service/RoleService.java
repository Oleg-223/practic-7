package com.example.project1.service;

import com.example.project1.model.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> findAllRoles();
    RoleModel findById(Long id);
}
