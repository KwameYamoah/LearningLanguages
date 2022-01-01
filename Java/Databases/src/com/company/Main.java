package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static final String JDBC_URL = "jdbc:sqlite:/home/kyamoa/repos/LearningLanguages/Java/Databases/testjava.db";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS contacts(name TEXT, phone INTEGER, email TEXT) ");
            statement.execute("INSERT INTO contacts VALUES ('Tim', 1241242, 'tim@email.com')");
        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
}
