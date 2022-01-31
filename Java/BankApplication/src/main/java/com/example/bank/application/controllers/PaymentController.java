package com.example.bank.application.controllers;

import com.example.bank.application.BankApplication;
import com.example.bank.application.db.DatabaseObject;
import com.example.bank.application.models.BankAccount;
import com.example.bank.application.models.Payment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class PaymentController {
    @FXML
    public GridPane paymentGridPane;

    public static BankAccount currentBankAccount;

    public static void setCurrentBankAccount(BankAccount currentBankAccount) {
        PaymentController.currentBankAccount = currentBankAccount;
    }

    public void initialize() {
        ArrayList<DatabaseObject> payments = Payment.findByBankAccount(String.valueOf(currentBankAccount.getId()));
        int i = 0;
        if(payments != null){
            Label descriptionLabel = new Label("Description");
            GridPane.setConstraints(descriptionLabel, 0, 0);

            Label accountLabel = new Label("Account");
            GridPane.setConstraints(accountLabel, 1, 0);

            Label paymentMethodLabel = new Label("Payment Method");
            GridPane.setConstraints(paymentMethodLabel, 2, 0);

            Label referenceLabel = new Label("Reference");
            GridPane.setConstraints(referenceLabel, 3, 0);

            Label amountLabel = new Label("Amount");
            GridPane.setConstraints(amountLabel, 4, 0);

            Label dateLabel = new Label("Date");
            GridPane.setConstraints(dateLabel, 5, 0);

            paymentGridPane.setHgap(8);
            paymentGridPane.getChildren().addAll(descriptionLabel, accountLabel, paymentMethodLabel, referenceLabel, amountLabel, dateLabel);


            for (i = 0; i < payments.size(); i++) {
                Payment payment = (Payment) payments.get(i);

                Label description = new Label(String.valueOf(payment.getSourceId()));
                GridPane.setConstraints(description, 0, i + 1);

                Label account = new Label(String.valueOf(payment.getSourceId()));
                GridPane.setConstraints(account, 1, i + 1);

                Label paymentMethod = new Label(payment.getPaymentMethod());
                GridPane.setConstraints(paymentMethod, 2, i + 1);

                Label reference = new Label(payment.getReference());
                GridPane.setConstraints(reference, 3, i + 1);

                Label amount = new Label(String.valueOf(payment.getAmount()));
                GridPane.setConstraints(amount, 4, i + 1);

                Label date = new Label(payment.getTransferDate().toString());
                GridPane.setConstraints(date, 5, i + 1);
                paymentGridPane.getChildren().addAll(description, account, paymentMethod, reference, amount, date);
            }

        }

        Button back = new Button("Back");
        back.setOnAction(this::backToDashboard);
        GridPane.setConstraints(back,0,i + 1  );



        paymentGridPane.getChildren().addAll(back);
    }

    @FXML
    public void backToDashboard(ActionEvent actionEvent) {
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

}
