package com.azaan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azaan.entity.Role;
import com.azaan.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByRoleAndUsernameContainingIgnoreCase(Role role, String name);

    

}