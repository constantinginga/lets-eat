package com.example.letseat.repository.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class User {

    public String email, role;

    public User() {}

    public User(String email) {
        this.email = email;
        this.role = Roles.USER.name().toLowerCase();
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
