package com.example.bank.application.controllers;

import com.example.bank.application.BankApplication;
import com.example.bank.application.Session;
import com.example.bank.application.db.Database;
import com.example.bank.application.db.DatabaseObject;
import com.example.bank.application.models.BankAccount;
import com.example.bank.application.models.SavedAccount;
import com.example.bank.application.models.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransferController {
    @FXML
    public ChoiceBox<String> bankAccount;

    @FXML
    public TextField transferAmount;
    @FXML
    public TextField accountNumber;
    @FXML
    public TextField sortCode;
    @FXML
    public Label balance;
    @FXML
    public GridPane transferGridPane;

    @FXML
    private User currentUser;

    private ArrayList<DatabaseObject> userBankAccounts;
    private ArrayList<DatabaseObject> savedAccounts;
    private ChoiceBox<String> to;
    public void initialize() {
        currentUser = Session.getCurrentUser();
        userBankAccounts = BankAccount.findByUserId(currentUser.getId());

        savedAccounts = SavedAccount.findByUserId(currentUser.getId());

        System.out.println(savedAccounts);
        if(savedAccounts.size() > 0){
            Label toLabel = new Label("To");
            GridPane.setConstraints(toLabel, 0, 3);

            to = new ChoiceBox<>();
            GridPane.setConstraints(to, 1, 3);
            for (DatabaseObject account : savedAccounts) {
                SavedAccount savedAccount = (SavedAccount) account;

                BankAccount tempBankAccount = (BankAccount) BankAccount.findById(savedAccount.getBankAccountId());
                if (tempBankAccount != null) to.getItems().add(String.format("%s %s", tempBankAccount.getName(), tempBankAccount.getBalance()));
                else System.out.println("Error setting saved accounts");
            }
            to.getSelectionModel(). selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    onSavedAccountChange();
                }
            });
            transferGridPane.getChildren().addAll(toLabel, to);
        }

        for(int i = 0; i < userBankAccounts.size(); i++){
            BankAccount tempBankAccount = (BankAccount) userBankAccounts.get(i);
            bankAccount.getItems().add(String.format("%s %s", tempBankAccount.getName(), tempBankAccount.getBalance()));
        }
        if(bankAccount.getItems().size() > 0){
            bankAccount.getSelectionModel().select(0);
        }
    }

    @FXML
    public void transfer() {
        int bankAccountIndex = bankAccount.getSelectionModel().getSelectedIndex();
        BankAccount source = (BankAccount) BankAccount.findByUserId(currentUser.getId()).get(bankAccountIndex);
        BankAccount target = BankAccount.checkCredentials(accountNumber.getText(), sortCode.getText());
        if(source.equals(target)) {
            //TODO: Update error label, maybe throw exception and catch at higher level
            System.out.println("Source and target cant be the same");
            return;
        }
        if(target == null){
            //TODO: Update error label, maybe throw exception and catch at higher level
            System.out.println("Invalid target");
            return;
        }

        try{
            int amount = Integer.parseInt(transferAmount.getText());
            Database.startTransaction();
            source.withdraw(amount);
            target.deposit(amount);
            //TODO:: create payment
            Database.endTransaction();
            backToDashboard();
        }
        catch (NumberFormatException e){
            //TODO: Update error label
            System.out.println("Error parsing amount textField");
        }
        catch(Exception e){
            try {
                Database.rollbackTransaction();
            } catch (SQLException ex) {
                System.out.println("Failed to rollback - "  + ex.getMessage());
            }
        }
    }

    @FXML
    public void backToDashboard() {
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

    @FXML
    public void onBankAccountChange() {
        int bankAccountIndex = bankAccount.getSelectionModel().getSelectedIndex();
        BankAccount bankAccount = (BankAccount) BankAccount.findByUserId(currentUser.getId()).get(bankAccountIndex);
        balance.setText(String.valueOf(bankAccount.getBalance()));
    }

    public void onSavedAccountChange(){
        System.out.println("Got ehere");
        int savedAccountIndex = to.getSelectionModel().getSelectedIndex();
        SavedAccount savedAccount = (SavedAccount) savedAccounts.get(savedAccountIndex);
        System.out.println(savedAccount);

        BankAccount currentBankAccount = null;
        for(DatabaseObject bankAccount: userBankAccounts){
            BankAccount tempBankAccount = (BankAccount)bankAccount;
             if(tempBankAccount.getId() == savedAccount.getBankAccountId()){
                 currentBankAccount = tempBankAccount;
                 break;
             }
        }
        if(currentBankAccount == null)
        {
            System.out.println("Error setting values for saved account");
            return ;
        }
        accountNumber.setText(currentBankAccount.getAccountNumber());
        sortCode.setText(currentBankAccount.getSortCode());
    }
}
