package com.example.letseat.repository.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;

@IgnoreExtraProperties
public class UserList {

    public HashMap<String, User> users;

    public UserList() {}

    public UserList(HashMap<String, User> users) {
        this.users = users;
    }

    public boolean containsKey(String key) {
        return users.containsKey(key);
    }

    public User get(String key) {
        return users.get(key);
    }
}
