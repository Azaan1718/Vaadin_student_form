package com.azaan.entity;
import com.github.javaparser.quality.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="users") 
public class User {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable=false,unique=true)
    private String username;

    @NotBlank
    @Size(min=8)
    @Column(nullable=false)
    private String password;

    @NotBlank
    @Column(nullable=false)
    private String name;

    @Email
    @NotBlank
    @Column(nullable=false,unique=true)
    private String email;

     
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
     }

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public Role getRole() {
         return role;
     }

     public void setRole(Role role) {
         this.role = role;
     }

     public User(Long id, @NotBlank String username, @NotBlank @Size(min = 8) String password, @NotBlank String name,
            @Email @NotBlank String email, @NotBlank Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
     }

     public User(){
        
     }

}
