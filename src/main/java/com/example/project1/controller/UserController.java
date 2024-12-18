package com.example.project1.controller;

import com.example.project1.model.RoleModel;
import com.example.project1.model.UserModel;
import com.example.project1.service.RoleService;
import com.example.project1.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserModel> users = userService.findAllExceptFirst();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/create")
    public String getCreateUser(Model model) {
        List<RoleModel> roles = roleService.findAllRoles();
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("roles", roles);
        return "users/create";
    }


    @PostMapping("/create")
    public String postCreateUser(
            @ModelAttribute("userModel") UserModel userModel,
            @RequestParam("roleId") Long roleId,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "users/create";
        }

        if (userService.existsByUsername(userModel.getUsername())) {
            model.addAttribute("usernameError", "Пользователь с таким логином уже существует.");
            return "users/create";
        }

        if (userService.existsByEmail(userModel.getEmail())) {
            model.addAttribute("emailError", "Пользователь с таким email уже существует.");
            return "users/create";
        }

        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        userService.create(userModel, roleId);
        return "redirect:/users";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id,
                             @ModelAttribute("userModel") UserModel userModel,
                             @RequestParam(value = "roleId") Long roleId,
                             BindingResult result,
                             Model model
    ) {
        if (result.hasErrors()) {
            return "users/edit";
        }

        if (userService.existsByUsernameAndIdNot(userModel.getUsername(), id)) {
            model.addAttribute("usernameError", "Пользователь с таким логином уже существует.");
            return "users/edit";
        }

        if (userService.existsByEmailAndIdNot(userModel.getEmail(), id)) {
            model.addAttribute("emailError", "Пользователь с таким email уже существует.");
            return "users/edit";
        }

        userModel.setId(id);
        userService.update(userModel, roleId);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        UserModel userModel = userService.findById(id);
        model.addAttribute("userModel", userModel);
        model.addAttribute("roles", roleService.findAllRoles());
        return "users/edit";
    }


    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        UserModel userModel = userService.findById(id);
        model.addAttribute("userModel", userModel);
        return "users/edit";
    }



    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}

