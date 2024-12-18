package com.example.project1.service;

import com.example.project1.exceptions.ResourceNotFoundException;
import com.example.project1.model.UserModel;
import com.example.project1.model.RoleModel;
import com.example.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserModel> findAllExceptFirst() {
        List<UserModel> users = userRepository.findAll();
        if (users.size() > 1) {
            users.remove(0);
        }
        return users;
    }

    @Override
    public UserModel findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserModel create(UserModel userModel, Long roleId) {
        RoleModel role = roleService.findById(roleId);
        userModel.setRole(role);
        return userRepository.save(userModel);
    }

    @Override
    public void update(UserModel userModel, Long roleId) {
        UserModel existingUser = userRepository.findById(userModel.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userModel.getId()));

        existingUser.setFirstName(userModel.getFirstName());
        existingUser.setLastName(userModel.getLastName());
        existingUser.setPatronymic(userModel.getPatronymic());
        existingUser.setEmail(userModel.getEmail());
        existingUser.setUsername(userModel.getUsername());
        existingUser.setPhone(userModel.getPhone());

        RoleModel role = roleService.findById(roleId);
        existingUser.setRole(role);

        if (userModel.getPassword() != null && !userModel.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userModel.getPassword()));
        }

        userRepository.save(existingUser);
    }

    @Override
    public List<RoleModel> findAllRoles() {
        return roleService.findAllRoles();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean existsByUsernameAndIdNot(String username, Long id) {
        return userRepository.findByUsernameAndIdNot(username, id).isPresent();
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, Long id) {
        return userRepository.findByEmailAndIdNot(email, id).isPresent();
    }
}
