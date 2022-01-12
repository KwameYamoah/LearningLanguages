package com.example.bankapplication;

import com.example.bankapplication.concern.QueryType;
import com.example.bankapplication.concern.StatementString;
import com.example.bankapplication.concern.StatementType;
import com.example.bankapplication.concern.TableType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class BankApplication extends Application {

    @Override
    public void init() throws Exception {
        super.init();
        if(!Database.getInstance().open()){
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }

//        System.out.println(Database.getInstance().query(
//                QueryType.INSERT,
//                TableType.USER,
//                new StatementString("harry", StatementType.STRING),
//                new StatementString("535", StatementType.STRING),
//                new StatementString("harry@gmail.com", StatementType.STRING),
//                new StatementString("1954-03-04", StatementType.DATE),
//                new StatementString("2022-10-01", StatementType.DATE))
//        );
        System.out.println(Database.getInstance().updateQuery(
                QueryType.DELETE,
                TableType.USER,
                new StatementString("harry", StatementType.STRING),
                new StatementString("harry@gmail.com", StatementType.STRING),
                new StatementString("1954-03-04", StatementType.DATE))
        );

    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(BankApplication.class.getResource("views/sign_in.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
        Platform.exit();
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