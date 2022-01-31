package com.example.bank.application.db;

import com.example.bank.application.concern.QueryType;
import com.example.bank.application.concern.StatementString;
import com.example.bank.application.concern.StatementType;
import com.example.bank.application.concern.TableType;
import com.example.bank.application.models.BankAccount;
import com.example.bank.application.models.Payment;
import com.example.bank.application.models.SavedAccount;
import com.example.bank.application.models.User;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Database {
    //TODO: can this be stored somewhere secure?
    public static final String DB_URL = "jdbc:postgresql://localhost/bank_app";
    public static final String USER = "kyamoa";
    public static final String PASSWORD = "1234";

    private static final Database database = new Database();

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
    public static final String COLUMN_BANK_ACCOUNT_ID = "id";
    public static final String COLUMN_BANK_ACCOUNT_USER_ID = "user_id";
    public static final String COLUMN_BANK_BALANCE = "balance";
    public static final String COLUMN_BANK_ACCOUNT_LONG_NUMBER = "long_number";
    public static final String COLUMN_BANK_ACCOUNT_ACCOUNT_NUMBER = "account_number";
    public static final String COLUMN_BANK_ACCOUNT_SORT_CODE = "sort_code";
    public static final String COLUMN_BANK_ACCOUNT_SECURITY_CODE = "security_code";
    public static final String COLUMN_BANK_ACCOUNT_CREATED_ON = "created_on";
    public static final String COLUMN_BANK_ACCOUNT_LAST_ACCESSED = "last_accessed";
    public static final String COLUMN_BANK_ACCOUNT_NAME = "name";

    public static final String TABLE_PAYMENTS = "payments";
    public static final String COLUMN_PAYMENT_ID = "id";
    public static final String COLUMN_PAYMENT_SOURCE_ID = "source_id";
    public static final String COLUMN_PAYMENT_TARGET_ID = "target_id";
    public static final String COLUMN_PAYMENT_AMOUNT = "amount";
    public static final String COLUMN_PAYMENT_TRANSFER_DATE = "transfer_date";
    public static final String COLUMN_PAYMENT_RECEIVED_DATE = "received_date";
    public static final String COLUMN_PAYMENT_REFERENCE = "reference";
    public static final String COLUMN_PAYMENT_PAYMENT_METHOD = "payment_method";


    public static final String TABLE_SAVED_ACCOUNT = "saved_accounts";
    public static final String COLUMN_SAVED_ACCOUNT_ID = "id";
    public static final String COLUMN_SAVED_ACCOUNT_BANK_ACCOUNT_ID = "bank_account_id";
    public static final String COLUMN_SAVED_ACCOUNT_USER_ID = "user_id";


    // SQL Strings
    public static final String USER_EMAIL_LIKE = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_EMAIL + " LIKE ?";
    public static final String USER_EMAIL = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_EMAIL + "=?";
    public static final String USER_USERNAME = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_USERNAME + "=?";
    public static final String USER_USERNAME_LIKE = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_USERNAME + " LIKE ?";
    public static final String USER_ALL = "SELECT * FROM " + TABLE_USERS;


    public static final String USER_INSERT = "INSERT INTO " + TABLE_USERS + "(" + COLUMN_USER_USERNAME + ", " + COLUMN_USER_PASSWORD +
            ", " + COLUMN_USER_DATE_OF_BIRTH + ", " + COLUMN_USER_EMAIL + ", " + COLUMN_USER_CREATED_ON + ", " + COLUMN_USER_LAST_LOGIN + ")" +
            " VALUES (?, crypt(?, gen_salt('bf')), ?, ?, ?, ?)";


    public static final String USER_DELETE = "DELETE FROM " + TABLE_USERS +
            " WHERE " + COLUMN_USER_USERNAME + " = ? AND " + COLUMN_USER_EMAIL + " = ? AND "
            + COLUMN_USER_DATE_OF_BIRTH + " = ?";

    public static final String USER_BY_ID = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_BANK_ACCOUNT_ID + " = ?";

    public static final String USER_UPDATE = "UPDATE " + TABLE_USERS + " SET " + COLUMN_USER_USERNAME + "=?, " + COLUMN_USER_PASSWORD + "=?, " +
                                             COLUMN_USER_DATE_OF_BIRTH + "=?, " + COLUMN_USER_EMAIL + "=?, " + COLUMN_USER_CREATED_ON + "=?, " +
                                             COLUMN_USER_LAST_LOGIN + "=? WHERE " + COLUMN_USER_ID + "=?";
    public static final String USER_CHECK_CREDENTIALS = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_USERNAME + " = ? AND " + COLUMN_USER_PASSWORD + " = crypt(?, " + COLUMN_USER_PASSWORD + ")";


    public static final String BANK_ACCOUNT_BY_ID = "SELECT * FROM " + TABLE_BANK_ACCOUNTS + " WHERE " + COLUMN_BANK_ACCOUNT_ID + " = ?";
    public static final String BANK_ACCOUNT_BY_USER_ID = "SELECT * FROM " + TABLE_BANK_ACCOUNTS + " WHERE " + COLUMN_BANK_ACCOUNT_USER_ID + " = ?";
    public static final String BANK_ACCOUNT_ALL = "SELECT * FROM " + TABLE_BANK_ACCOUNTS;


    public static final String BANK_ACCOUNT_INSERT = "INSERT INTO " + TABLE_BANK_ACCOUNTS + "(" + COLUMN_BANK_ACCOUNT_USER_ID + ", " + COLUMN_BANK_BALANCE + ", " +
                                                    COLUMN_BANK_ACCOUNT_LONG_NUMBER + ", " + COLUMN_BANK_ACCOUNT_ACCOUNT_NUMBER + ", " + COLUMN_BANK_ACCOUNT_SORT_CODE + ", " +
                                                    COLUMN_BANK_ACCOUNT_SECURITY_CODE + ", " + COLUMN_BANK_ACCOUNT_CREATED_ON + ", " + COLUMN_BANK_ACCOUNT_LAST_ACCESSED + ", " + COLUMN_BANK_ACCOUNT_NAME + ") " +
                                                    "VALUES(?, ?, ?, ?, ?, crypt(?, gen_salt('bf')), ?, ?, ?)";

    public static final String BANK_ACCOUNT_DELETE = "DELETE FROM " + TABLE_BANK_ACCOUNTS + " WHERE " + COLUMN_BANK_ACCOUNT_SORT_CODE + "=? AND " + COLUMN_BANK_ACCOUNT_ACCOUNT_NUMBER + "=?";
    public static final String BANK_ACCOUNT_UPDATE = "UPDATE " + TABLE_BANK_ACCOUNTS + " SET " + COLUMN_BANK_BALANCE + "=?, " + COLUMN_BANK_ACCOUNT_LONG_NUMBER + "=?, " + COLUMN_BANK_ACCOUNT_ACCOUNT_NUMBER +
                                                     "=?, " + COLUMN_BANK_ACCOUNT_SORT_CODE + "=?, " + COLUMN_BANK_ACCOUNT_SECURITY_CODE + "=?, " + COLUMN_USER_CREATED_ON + "=?, " +
                                                     COLUMN_BANK_ACCOUNT_LAST_ACCESSED + "=? WHERE " + COLUMN_BANK_ACCOUNT_ID + "=?";

    public static final String BANK_CHECK_DETAILS = "SELECT * FROM " + TABLE_BANK_ACCOUNTS + " WHERE " + COLUMN_BANK_ACCOUNT_SORT_CODE + "=? AND " + COLUMN_BANK_ACCOUNT_ACCOUNT_NUMBER + "=?";

    private static final String PAYMENT_ALL = "SELECT * FROM " + TABLE_PAYMENTS;
    private static final String PAYMENT_BY_ID = "SELECT * FROM payments WHERE id=?";
    private static final String PAYMENT_BY_BANK_ACCOUNT_ID = "SELECT * FROM payments WHERE source_id=? OR target_id=?";
    private static final String PAYMENT_INSERT = "INSERT INTO payments(source_id, target_id, amount, payment_method, reference, transfer_date, received_date)" +
            "values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SAVED_ACCOUNT_ALL = "SELECT * FROM saved_accounts";
    private static final String SAVED_ACCOUNT_BY_ID = "SELECT * FROM saved_accounts WHERE id=?";
    private static final String SAVED_ACCOUNT_BY_USER_ID = "SELECT * FROM saved_accounts WHERE user_id=?";
    private static final String SAVED_ACCOUNT_DELETE = "DELETE FROM saved_accounts WHERE id=?";
    private static final String SAVED_ACCOUNT_INSERT = "INSERT INTO saved_accounts(bank_account_id, user_id) VALUES(?, ?)";



    //Prepared Statements
    private static PreparedStatement userByIdPreparedStatement = null;
    private static PreparedStatement userEmailLikePreparedStatement = null;
    private static PreparedStatement userUsernameLikePreparedStatement = null;
    private static PreparedStatement userEmailPreparedStatement = null;
    private static PreparedStatement userUsernamePreparedStatement = null;
    private static PreparedStatement userSelectAllPreparedStatement = null;
    private static PreparedStatement userInsertPreparedStatement = null;
    private static PreparedStatement userDeletePreparedStatement = null;
    private static PreparedStatement userUpdatePreparedStatement = null;
    private static PreparedStatement userCheckCredentials = null;

    private static PreparedStatement bankAccountByIdPreparedStatement = null;
    private static PreparedStatement bankAccountByUserIdPreparedStatement = null;
    private static PreparedStatement bankAccountSelectAllPreparedStatement = null;
    private static PreparedStatement bankAccountInsertPreparedStatement = null;
    private static PreparedStatement bankAccountDeletePreparedStatement = null;
    private static PreparedStatement bankAccountUpdatePreparedStatement = null;
    private static PreparedStatement bankCheckDetails = null;

    private static PreparedStatement paymentSelectAllPreparedStatement = null;
    private static PreparedStatement paymentByIdPreparedStatement = null;
    private static PreparedStatement paymentByBankAccountIdPreparedStatement = null;
    private static PreparedStatement paymentInsertPreparedStatement = null;
    private static PreparedStatement paymentDeletePreparedStatement = null;

    private static PreparedStatement savedAccountSelectAllPreparedStatement = null;
    private static PreparedStatement savedAccountByIdPreparedStatement = null;
    private static PreparedStatement savedAccountByUserIdPreparedStatement = null;
    private static PreparedStatement savedAccountInsertPreparedStatement = null;
    private static PreparedStatement savedAccountDeletePreparedStatement = null;

    //TODO: Refactoring when database is completed, making sure things are in the right places
    //TODO: Caching queries
    private Database() {

    }

    public static Database getInstance() {
        return database;
    }

    public boolean open() {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        try {
            connection = DriverManager.getConnection(DB_URL, props);
            userUsernamePreparedStatement = connection.prepareStatement(USER_USERNAME);
            userEmailPreparedStatement = connection.prepareStatement(USER_EMAIL);
            userUsernameLikePreparedStatement = connection.prepareStatement(USER_USERNAME_LIKE);
            userEmailLikePreparedStatement = connection.prepareStatement(USER_EMAIL_LIKE);
            userSelectAllPreparedStatement = connection.prepareStatement(USER_ALL);
            userInsertPreparedStatement = connection.prepareStatement(USER_INSERT);
            userDeletePreparedStatement = connection.prepareStatement(USER_DELETE);
            userByIdPreparedStatement = connection.prepareStatement(USER_BY_ID);
            userUpdatePreparedStatement = connection.prepareStatement(USER_UPDATE);
            userCheckCredentials = connection.prepareStatement(USER_CHECK_CREDENTIALS);
            bankAccountByIdPreparedStatement = connection.prepareStatement(BANK_ACCOUNT_BY_ID);
            bankAccountByUserIdPreparedStatement = connection.prepareStatement(BANK_ACCOUNT_BY_USER_ID);
            bankAccountSelectAllPreparedStatement = connection.prepareStatement(BANK_ACCOUNT_ALL);
            bankAccountInsertPreparedStatement = connection.prepareStatement(BANK_ACCOUNT_INSERT);
            bankAccountDeletePreparedStatement = connection.prepareStatement(BANK_ACCOUNT_DELETE);
            bankAccountUpdatePreparedStatement = connection.prepareStatement(BANK_ACCOUNT_UPDATE);
            bankCheckDetails = connection.prepareStatement(BANK_CHECK_DETAILS);
            paymentSelectAllPreparedStatement = connection.prepareStatement(PAYMENT_ALL);
            paymentByIdPreparedStatement = connection.prepareStatement(PAYMENT_BY_ID);
            paymentByBankAccountIdPreparedStatement = connection.prepareStatement(PAYMENT_BY_BANK_ACCOUNT_ID);
            paymentInsertPreparedStatement = connection.prepareStatement(PAYMENT_INSERT);
            savedAccountSelectAllPreparedStatement = connection.prepareStatement(SAVED_ACCOUNT_ALL);
            savedAccountByIdPreparedStatement = connection.prepareStatement(SAVED_ACCOUNT_BY_ID);
            savedAccountByUserIdPreparedStatement = connection.prepareStatement(SAVED_ACCOUNT_BY_USER_ID);
            savedAccountInsertPreparedStatement = connection.prepareStatement(SAVED_ACCOUNT_INSERT);
            savedAccountDeletePreparedStatement = connection.prepareStatement(SAVED_ACCOUNT_DELETE);
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    //TODO: Later check what happens if records are too much, Is there a way to load some records(lazy load?) at a time

    public ArrayList<DatabaseObject> selectQuery(QueryType queryType, TableType table, StatementString... statementStrings) {
        ArrayList<DatabaseObject> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getSelectPreparedStatement(queryType, table);
            populatePreparedStatement(preparedStatement, statementStrings);
            System.out.println(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (table == TableType.USER) {
                        list.add(new User(
                                resultSet.getInt(COLUMN_USER_ID),
                                resultSet.getString(COLUMN_USER_USERNAME),
                                resultSet.getString(COLUMN_USER_PASSWORD),
                                LocalDate.parse(resultSet.getString(COLUMN_USER_DATE_OF_BIRTH)),
                                resultSet.getString(COLUMN_USER_EMAIL),
                                Timestamp.valueOf(resultSet.getString(COLUMN_USER_CREATED_ON)),
                                Timestamp.valueOf(resultSet.getString(COLUMN_USER_LAST_LOGIN))
                                )
                        );
                    }
                    else if(table == TableType.BANK_ACCOUNT){
                        list.add(new BankAccount(
                                resultSet.getInt(COLUMN_BANK_ACCOUNT_ID),
                                resultSet.getInt(COLUMN_BANK_ACCOUNT_USER_ID),
                                resultSet.getInt(COLUMN_BANK_BALANCE),
                                resultSet.getString(COLUMN_BANK_ACCOUNT_LONG_NUMBER),
                                resultSet.getString(COLUMN_BANK_ACCOUNT_ACCOUNT_NUMBER),
                                resultSet.getString(COLUMN_BANK_ACCOUNT_SORT_CODE),
                                resultSet.getString(COLUMN_BANK_ACCOUNT_SECURITY_CODE),
                                resultSet.getTimestamp(COLUMN_BANK_ACCOUNT_CREATED_ON),
                                resultSet.getTimestamp(COLUMN_BANK_ACCOUNT_LAST_ACCESSED),
                                resultSet.getString(COLUMN_BANK_ACCOUNT_NAME)

                        ));

                    }
                    else if(table == TableType.PAYMENT){
                        list.add(new Payment(
                                resultSet.getInt(COLUMN_PAYMENT_ID),
                                resultSet.getInt(COLUMN_PAYMENT_SOURCE_ID),
                                resultSet.getInt(COLUMN_PAYMENT_TARGET_ID),
                                resultSet.getInt(COLUMN_PAYMENT_AMOUNT),
                                resultSet.getString(COLUMN_PAYMENT_PAYMENT_METHOD),
                                resultSet.getString(COLUMN_PAYMENT_REFERENCE),
                                LocalDate.parse(resultSet.getString(COLUMN_PAYMENT_TRANSFER_DATE)),
                                LocalDate.parse(resultSet.getString(COLUMN_PAYMENT_RECEIVED_DATE))
                        ));
                    }
                    else if(table == TableType.SAVED_ACCOUNT){
                        list.add(new SavedAccount(
                                resultSet.getInt(COLUMN_SAVED_ACCOUNT_ID),
                                resultSet.getInt(COLUMN_SAVED_ACCOUNT_BANK_ACCOUNT_ID),
                                resultSet.getInt(COLUMN_SAVED_ACCOUNT_USER_ID)
                        ));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public boolean updateQuery(QueryType queryType, TableType table, StatementString... statementStrings) {
        try {
            PreparedStatement preparedStatement = getUpdatePreparedStatement(queryType, table);

            populatePreparedStatement(preparedStatement, statementStrings);
            System.out.println(preparedStatement);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows != 1) {
                System.out.printf("Something went wrong during %s%n", queryType.toString().toLowerCase());
                return false;
            }
            System.out.println("Operation was successful");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void populatePreparedStatement(PreparedStatement preparedStatement, StatementString[] statementStrings) throws SQLException {
        if (statementStrings.length > 0) {
            for (int i = 0; i < statementStrings.length; i++) {
                StatementString statementString = statementStrings[i];
                if (statementString.getType().isString()) {
                    preparedStatement.setString(i + 1, statementString.getValue());
                }
                else if (statementString.getType() == StatementType.DATE) {
                    preparedStatement.setDate(i + 1, Date.valueOf(statementString.getValue()));
                }
                else if(statementString.getType() == StatementType.TIMESTAMP){
                    preparedStatement.setTimestamp(i + 1, Timestamp.valueOf(statementString.getValue()));
                }
                else if(statementString.getType() == StatementType.INT){
                    preparedStatement.setInt(i + 1, Integer.parseInt(statementString.getValue()));
                }
            }
        }
    }

    private PreparedStatement getUpdatePreparedStatement(QueryType queryType, TableType table) throws Exception {
        PreparedStatement preparedStatement = null;
        switch (queryType) {
            case INSERT -> preparedStatement = getInsertPreparedStatement(table);
            case DELETE -> preparedStatement = getDeletePreparedStatement(table);
            case UPDATE -> preparedStatement = (table == TableType.USER) ? userUpdatePreparedStatement : bankAccountUpdatePreparedStatement;
        }
        if(preparedStatement == null)  throw new Exception("Invalid Query");

        return preparedStatement;
    }

    private PreparedStatement getInsertPreparedStatement(TableType table) {
        switch (table){
            case USER ->{ return userInsertPreparedStatement; }
            case BANK_ACCOUNT -> { return bankAccountInsertPreparedStatement; }
            case PAYMENT -> { return paymentInsertPreparedStatement; }
            case SAVED_ACCOUNT -> { return savedAccountInsertPreparedStatement; }
            default -> { return null; }
        }
    }

    private PreparedStatement getDeletePreparedStatement(TableType table) {
        switch (table){
            case USER ->{ return userDeletePreparedStatement; }
            case BANK_ACCOUNT -> { return bankAccountDeletePreparedStatement; }
            case PAYMENT -> { return paymentDeletePreparedStatement; }
            case SAVED_ACCOUNT -> { return savedAccountDeletePreparedStatement; }
            default -> { return null; }
        }
    }

    private PreparedStatement getUpdatePreparedStatement(TableType table) {
        switch (table){
            case USER ->{ return userUpdatePreparedStatement; }
            case BANK_ACCOUNT -> { return bankAccountUpdatePreparedStatement; }
            case PAYMENT -> { return null; }
            case SAVED_ACCOUNT -> { return null; }
            default -> { return null; }
        }
    }

    private PreparedStatement getSelectPreparedStatement(QueryType queryType, TableType table) throws Exception {
        PreparedStatement preparedStatement = null;
        switch (queryType) {
            case SELECT_ALL ->  preparedStatement = getSelectAllPreparedStatement(table);
            case SELECT_BY_ID ->  preparedStatement = getSelectByIdPreparedStatement(table);
            case CHECK_CREDENTIALS -> preparedStatement =  (table == TableType.USER) ? userCheckCredentials : bankCheckDetails;
            case SELECT_USER_USERNAME -> preparedStatement = (table == TableType.USER) ? userUsernamePreparedStatement : null;
            case SELECT_USER_EMAIL -> preparedStatement = (table == TableType.USER) ? userEmailPreparedStatement : null;
            case SELECT_USER_EMAIL_LIKE -> preparedStatement = (table == TableType.USER) ? userEmailLikePreparedStatement : null;
            case SELECT_USER_USERNAME_LIKE -> preparedStatement = (table == TableType.USER) ? userUsernameLikePreparedStatement : null;
            case SELECT_BANK_ACCOUNTS_BY_USER_ID ->  preparedStatement = (table == TableType.USER) ? null : bankAccountByUserIdPreparedStatement;
            case SELECT_PAYMENT_BY_BANK_ACCOUNT -> preparedStatement = paymentByBankAccountIdPreparedStatement;
            case SELECT_SAVED_ACCOUNT_BY_USER_ID -> preparedStatement = savedAccountByUserIdPreparedStatement;
        }
        if(preparedStatement == null)  throw new Exception("Invalid Query");
        return preparedStatement;
    }

    private PreparedStatement getSelectAllPreparedStatement(TableType table) {
        switch (table){
            case USER ->{ return userSelectAllPreparedStatement; }
            case BANK_ACCOUNT -> { return bankAccountSelectAllPreparedStatement; }
            case PAYMENT -> { return paymentSelectAllPreparedStatement; }
            case SAVED_ACCOUNT -> { return savedAccountSelectAllPreparedStatement; }
            default -> { return null; }
        }
    }

    private PreparedStatement getSelectByIdPreparedStatement(TableType table) {
        switch (table){
            case USER ->{ return userByIdPreparedStatement; }
            case BANK_ACCOUNT -> { return bankAccountByIdPreparedStatement; }
            case PAYMENT -> { return paymentByIdPreparedStatement; }
            case SAVED_ACCOUNT -> { return savedAccountByIdPreparedStatement; }
            default -> { return null; }
        }
    }

    public static void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
        System.out.println("Transaction started");
    }

    public static void rollbackTransaction() throws SQLException {
        connection.rollback();
        System.out.println("Rolling back changes");
    }

    public static void endTransaction() throws SQLException {
        connection.setAutoCommit(true);
        System.out.println("Transaction completed");
    }

    public void close() {
        try {
            userUsernamePreparedStatement.close();
            userEmailPreparedStatement.close();
            userUsernameLikePreparedStatement.close();
            userEmailLikePreparedStatement.close();
            userSelectAllPreparedStatement.close();
            userInsertPreparedStatement.close();
            userDeletePreparedStatement.close();
            userByIdPreparedStatement.close();
            userUpdatePreparedStatement.close();
            userCheckCredentials.close();
            bankAccountByIdPreparedStatement.close();
            bankAccountByUserIdPreparedStatement.close();
            bankAccountSelectAllPreparedStatement.close();
            bankAccountInsertPreparedStatement.close();
            bankAccountDeletePreparedStatement.close();
            bankAccountUpdatePreparedStatement.close();

            paymentSelectAllPreparedStatement.close();
            paymentByIdPreparedStatement.close();
            paymentByBankAccountIdPreparedStatement.close();
            paymentInsertPreparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
