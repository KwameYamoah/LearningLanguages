package com.example.bank.application;

import com.example.bank.application.concern.QueryType;
import com.example.bank.application.concern.StatementString;
import com.example.bank.application.concern.StatementType;
import com.example.bank.application.concern.TableType;
import com.example.bank.application.db.Database;
import com.example.bank.application.db.DatabaseObject;
import com.example.bank.application.models.BankAccount;
import com.example.bank.application.models.Payment;
import com.example.bank.application.models.SavedAccount;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BankApplication extends Application {
    public static Stage mainStage;

    @Override
    public void init() throws Exception {
        super.init();
        if(!Database.getInstance().open()){
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BankApplication.class.getResource("views/session/sign_in.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BankApp");
        stage.setScene(scene);
        mainStage = stage;
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Database.getInstance().close();
    }

    public static void main(String[] args) {
        launch();
    }
}