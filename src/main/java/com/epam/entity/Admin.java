package com.epam.entity;

public class Admin extends Account{

    public Admin() {
    }

    public Admin(long id, String login, String email, String password) {
        super(id, login, email, password);
    }
}
