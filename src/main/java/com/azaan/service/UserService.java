package com.azaan.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.azaan.entity.Role;
import com.azaan.entity.User;
import com.azaan.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> searchStudents(String name) {

        return userRepository.findByRoleAndUsernameContainingIgnoreCase(
                Role.STUDENT,
                name
        );
    }

    public User findByUsername(String name) throws UsernameNotFoundException{
        return userRepository.findByUsername(name)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

    }
   
}