package com.example.project1.service;

import com.example.project1.model.UserModel;
import com.example.project1.model.RoleModel;

import java.util.List;

public interface UserService {
    List<UserModel> findAllExceptFirst();
    UserModel findById(Long id);
    UserModel create(UserModel userModel, Long roleId);
    void update(UserModel userModel, Long roleId);
    void delete(Long id);
    List<RoleModel> findAllRoles();
    UserModel findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsernameAndIdNot(String username, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
}
