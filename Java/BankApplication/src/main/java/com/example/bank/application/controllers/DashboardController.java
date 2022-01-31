package com.example.bank.application.controllers;

import com.example.bank.application.BankApplication;
import com.example.bank.application.db.DatabaseObject;
import com.example.bank.application.Session;
import com.example.bank.application.models.BankAccount;
import com.example.bank.application.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class DashboardController {
    @FXML
    public ChoiceBox<String> bankAccount;
    @FXML
    public Label balance;
    @FXML
    public Label username;
    private User currentUser;
    public void initialize() {
        currentUser = Session.getCurrentUser();
        username.setText(currentUser.getUsername().toUpperCase());

        ArrayList<DatabaseObject> account = BankAccount.findByUserId(currentUser.getId());

        for(int i = 0; i < account.size(); i++){
            BankAccount tempBankAccount = (BankAccount)account.get(i);
            bankAccount.getItems().add(String.format("%d. %s", i + 1,tempBankAccount.getName()));
        }
        if(bankAccount.getItems().size() > 0){
            bankAccount.getSelectionModel().select(0);
        }


    }

    @FXML
    public void changeBankAccount() {
        int bankAccountIndex = bankAccount.getSelectionModel().getSelectedIndex();
        BankAccount bankAccount = (BankAccount) BankAccount.findByUserId(currentUser.getId()).get(bankAccountIndex);
        PaymentController.currentBankAccount = bankAccount;
        balance.setText(String.valueOf(bankAccount.getBalance()));
    }

    @FXML
    public void changeToTransfer(){
        System.out.println("Changing scene...");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BankApplication.class.getResource("views/transfer.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            BankApplication.mainStage.setScene(scene);
            BankApplication.mainStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToPayment() {
        System.out.println("Changing scene...");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BankApplication.class.getResource("views/payment.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            BankApplication.mainStage.setScene(scene);
            BankApplication.mainStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
