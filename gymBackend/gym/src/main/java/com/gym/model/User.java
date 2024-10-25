package com.gym.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    @Email
    private String email;
    private String fullName;
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role; // ADMIN, TRAINER, CUSTOMER

//    public String getName() {
//        return fullName;
//    }

    // Getters, setters, constructors
}