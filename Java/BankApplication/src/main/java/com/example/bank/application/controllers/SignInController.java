package com.example.bank.application.controllers;

import com.example.bank.application.BankApplication;
import com.example.bank.application.Session;
import com.example.bank.application.models.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignInController {
    //TODO: Allow user to save details, To field must also include current bank cards And Save Ones

    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    public void onSignIn(){
        System.out.println("Trying to sign in");
        if(!validateUser(username.getText(), password.getText())){
            //TODO update error field
            return;
        }
        System.out.println("Authentication passed");
        changeToDashboard();
    }

    @FXML
    private void changeToDashboard() {
        System.out.println("Changing scene...");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BankApplication.class.getResource("views/dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            BankApplication.mainStage.setScene(scene);
            BankApplication.mainStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateUser(String username, String password) {
        User user = User.checkCredentials(username, password);
        if(user != null){
            System.out.println("User " + user.getUsername() + " is successfully logged in" );
            Session.setCurrentUser(user);
            return true;
        }
        else{
            System.out.println("Login failed");
            return false;
        }
    }

    @FXML
    public void onExit() {
        System.out.println("Exiting...");
        Platform.exit();
    }
}