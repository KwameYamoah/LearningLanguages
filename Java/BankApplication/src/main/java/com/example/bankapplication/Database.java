package com.example.bankapplication;

import com.example.bankapplication.concern.QueryType;
import com.example.bankapplication.concern.StatementString;
import com.example.bankapplication.concern.StatementType;
import com.example.bankapplication.concern.TableType;
import com.example.bankapplication.models.User;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Database {
    public static final String DB_URL = "jdbc:postgresql://localhost/bank_app";
    public static final String USER = "kyamoa";
    public static final String PASSWORD = "1234";

    private static Database database = new Database();
    private static Connection connection = null;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_DATE_OF_BIRTH = "date_of_birth";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_CREATED_ON = "created_on";
    public static final String COLUMN_USER_LAST_LOGIN = "last_login";


    public static final String TABLE_BANK_ACCOUNTS = "bank_accounts";

    // SQL Strings
    public static final String USER_EMAIL_LIKE = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_EMAIL + " LIKE ?";
    public static final String USER_USERNAME_LIKE = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_USERNAME + " LIKE ?";
    public static final String USER_ALL = "SELECT * FROM " + TABLE_USERS;


    public static final String USER_INSERT = "INSERT INTO " + TABLE_USERS + "(" + COLUMN_USER_USERNAME +", " + COLUMN_USER_PASSWORD +
                                                ", " + COLUMN_USER_EMAIL + ", " + COLUMN_USER_DATE_OF_BIRTH + "," + COLUMN_USER_CREATED_ON + ")" +
                                             " VALUES (?, ?, ?, ?, ?)";


    public static final String USER_DELETE = "DELETE FROM " + TABLE_USERS +
                                             " WHERE " + COLUMN_USER_USERNAME + " = ? AND " + COLUMN_USER_EMAIL + " = ? AND "
                                                + COLUMN_USER_DATE_OF_BIRTH + " = ?" ;

    //Prepared Statements
    private static PreparedStatement userEmailLikePreparedStatement = null;
    private static PreparedStatement userUsernameLikePreparedStatement = null;
    private static PreparedStatement userAllPreparedStatement = null;

    private static PreparedStatement userInsertPreparedStatement = null;

    private static PreparedStatement userDeletePreparedStatement = null;

    public static PreparedStatement userUpdatePreparedStatement = null;
    //TODO: Refactoring when database is completed, making sure things are in the right places

    private Database() {

    }

    public static Database getInstance() {
        return database;
    }

    //TODO: Later check what happens if records are too much, Is there a way to load some records(lazy load?) at a time
    public ArrayList<User> getUsers() {
        return getUserBy(userAllPreparedStatement, null);
    }

    public ArrayList<User> getUsersByUsernameLike(String query) {
        return getUserBy(userUsernameLikePreparedStatement, query);
    }

    public ArrayList<User> getUsersByEmailLike(String query) {
        return getUserBy(userEmailLikePreparedStatement, query);
    }

    public boolean updateQuery(QueryType queryType, TableType table, StatementString... statementStrings){
        try{
            PreparedStatement preparedStatement = getPreparedStatement(queryType, table);
            for(int i = 0; i < statementStrings.length; i++){
                StatementString statementString = statementStrings[i];
                if(statementString.getType() == StatementType.STRING){
                    preparedStatement.setString(i + 1, statementString.getValue());
                }
                else if(statementString.getType() == StatementType.DATE){
                    preparedStatement.setDate(i + 1, Date.valueOf(statementString.getValue()));
                }
            }
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows != 1){
                throw new Exception(String.format("Something went wrong during %s", queryType.toString().toLowerCase()));
            }
            System.out.println("Operation was successful");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private PreparedStatement getPreparedStatement(QueryType queryType, TableType table) throws Exception {
        switch (queryType){
            case INSERT -> { return (table == TableType.USER) ? userInsertPreparedStatement : null; }
            case DELETE -> { return (table == TableType.USER) ? userDeletePreparedStatement : null; }
            case UPDATE -> { return (table == TableType.USER) ? userUpdatePreparedStatement : null; }
            default -> { throw new Exception("Invalid Query"); }
        }
    }

    public ArrayList<User> getUserBy(PreparedStatement preparedStatement, String query){
        ArrayList<User> users = new ArrayList<>();
        try{
            if(query != null){
                preparedStatement.setString(1, query + "%");
            }
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    users.add(new User(
                            resultSet.getString(COLUMN_USER_USERNAME),
                            resultSet.getString(COLUMN_USER_PASSWORD),
                            resultSet.getString(COLUMN_USER_EMAIL),
                            LocalDate.parse(resultSet.getString(COLUMN_USER_DATE_OF_BIRTH))));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public boolean open(){
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        try {
            connection = DriverManager.getConnection(DB_URL, props);
            userUsernameLikePreparedStatement = connection.prepareStatement(USER_USERNAME_LIKE);
            userEmailLikePreparedStatement = connection.prepareStatement(USER_EMAIL_LIKE);
            userAllPreparedStatement = connection.prepareStatement(USER_ALL);
            userInsertPreparedStatement = connection.prepareStatement(USER_INSERT);
            userDeletePreparedStatement = connection.prepareStatement(USER_DELETE);
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean close(){
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
