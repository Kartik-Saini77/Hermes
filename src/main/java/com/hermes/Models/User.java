package com.hermes.Models;

import com.hermes.Models.Enums.Role;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDateTime;

public class User {

    @BsonProperty("firstName")
    private String firstName;
    @BsonProperty("lastName")
    private String lastName;
    @BsonProperty("email")
    private String email;
    @BsonProperty("role")
    private Role role;
    @BsonProperty("password")
    private String password;
    @BsonProperty("createdAt")
    private LocalDateTime createdAt;
    @BsonProperty("isVerified")
    private boolean isVerified;

    public User() {
        role=Role.USER;
        createdAt = LocalDateTime.now();
        isVerified = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", isVerified=" + isVerified +
                '}';
    }
}
