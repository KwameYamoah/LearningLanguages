package com.example.bankapplication.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BankController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField dateOfBirth;

    public void onSignIn(){
        System.out.println("Trying to sign in");
        if(!validateUser(username.getText(), password.getText())){
            System.out.println("Authentication failed");
            //TODO update error field
            return;
        }
        System.out.println("Authentication passed");
        //TODO change scene to user dashboard
        System.out.println("Changing scene");
    }

    private boolean validateUser(String username, String password) {
            return false;
    }


    public void onExit() {
        System.out.println("Exiting...");
        Platform.exit();
    }
}