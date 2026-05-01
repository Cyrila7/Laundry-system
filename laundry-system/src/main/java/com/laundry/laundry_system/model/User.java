package com.laundry.laundry_system.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


// no-args constructor
public User() {}

// ======== GETTERS ========
public UUID getId() {
    return id;
}  
public String getFirstName() {
    return firstName;
}
public String getLastName() {
    return lastName;
}
public String getEmail() {
    return email;
}
public String getPassword() {
    return password;
}
public Role getRole() {
    return role;
}

// ======== SETTERS ========
public void setFirstName(String firstName) {
    this.firstName = firstName;
}
public void setLastName(String lastName) {
    this.lastName = lastName;
}
public void setEmail(String email) {
    this.email = email;
}
public void setPassword(String password) {
    this.password = password;
}
public void setRole(Role role) {
    this.role = role;
}
}