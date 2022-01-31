module com.example.bankapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports com.example.bank.application;
    opens com.example.bank.application to javafx.fxml;
    exports com.example.bank.application.controllers;
    exports com.example.bank.application.models;
    opens com.example.bank.application.controllers to javafx.fxml;
    exports com.example.bank.application.concern;
    opens com.example.bank.application.concern to javafx.fxml;
    exports com.example.bank.application.db;
    opens com.example.bank.application.db to javafx.fxml;
}