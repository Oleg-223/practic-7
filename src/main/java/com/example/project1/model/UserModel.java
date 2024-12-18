package com.example.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле не должно быть пустым")
    private String lastName;

    @NotBlank(message = "Поле не должно быть пустым")
    private String firstName;

    private String patronymic;

    @NotBlank(message = "Поле не должно быть пустым")
    private String phone;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Поле не должно быть пустым")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleModel role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Название не должно быть пустым") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Название не должно быть пустым") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "Название не должно быть пустым") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "Название не должно быть пустым") String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public @NotBlank(message = "Название не должно быть пустым") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "Название не должно быть пустым") String phone) {
        this.phone = phone;
    }

    public @NotBlank(message = "Название не должно быть пустым") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Название не должно быть пустым") String email) {
        this.email = email;
    }

    @Override
    public @NotBlank(message = "Название не должно быть пустым") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Название не должно быть пустым") String username) {
        this.username = username;
    }

    @Override
    public @NotBlank(message = "Название не должно быть пустым") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Название не должно быть пустым") String password) {
        this.password = password;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }
}
