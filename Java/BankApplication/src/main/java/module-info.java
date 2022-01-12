module com.example.bankapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports com.example.bankapplication;
    opens com.example.bankapplication to javafx.fxml;
    exports com.example.bankapplication.controllers;
    exports com.example.bankapplication.models;
    opens com.example.bankapplication.controllers to javafx.fxml;
    exports com.example.bankapplication.concern;
    opens com.example.bankapplication.concern to javafx.fxml;
}