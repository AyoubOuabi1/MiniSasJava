package com.library.management;

import com.library.management.model.User;

public class SimpleUser extends User {
    public SimpleUser() {
    }

    public SimpleUser(String email, String password) {
        super(email, password);
    }
}
