package com.example.project1.repository;

import com.example.project1.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByUsernameAndIdNot(String username, Long id);

    Optional<UserModel> findByEmailAndIdNot(String email, Long id);
}
