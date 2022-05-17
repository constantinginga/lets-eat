package com.example.letseat.repository.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Locale;

@IgnoreExtraProperties
public class User {

    public String email, role, uid;

    public User() {}

    public User(String email, String uid) {
        this.email = email;
        this.role = Roles.USER.name().toLowerCase();
        this.uid = uid;
    }

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

    public String getUid() { return uid; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
