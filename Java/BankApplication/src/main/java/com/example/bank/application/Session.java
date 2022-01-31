package com.example.bank.application;

import com.example.bank.application.models.BankAccount;
import com.example.bank.application.models.User;

public class Session {
    public static User currentUser;


    private Session(){}

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Session.currentUser = currentUser;
    }
}
