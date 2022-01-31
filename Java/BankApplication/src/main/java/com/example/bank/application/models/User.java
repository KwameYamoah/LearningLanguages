package com.example.bank.application.models;

import com.example.bank.application.db.Database;
import com.example.bank.application.db.DatabaseObject;
import com.example.bank.application.concern.QueryType;
import com.example.bank.application.concern.StatementString;
import com.example.bank.application.concern.StatementType;
import com.example.bank.application.concern.TableType;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends DatabaseObject {
    private Integer id;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private String email;
    private Timestamp createdOn;
    private Timestamp lastLogin;

    public User(Integer id, String username, String password, LocalDate dateOfBirth, String email,  Timestamp createdOn, Timestamp lastLogin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.createdOn = createdOn;
        this.lastLogin = lastLogin;
    }

    public boolean addBankAccount(BankAccount bankAccount){
        return BankAccount.create(bankAccount);
    }

    public static boolean create(User user) {
        return Database.getInstance().updateQuery(
                QueryType.INSERT,
                TableType.USER,
                new StatementString(user.username, StatementType.STRING),
                new StatementString(user.password, StatementType.STRING),
                new StatementString(user.dateOfBirth.toString(), StatementType.DATE),
                new StatementString(user.email, StatementType.STRING),
                new StatementString(user.createdOn.toString(), StatementType.TIMESTAMP),
                new StatementString(user.lastLogin.toString(), StatementType.TIMESTAMP)
        );
    }

    @Override
    public boolean save(){
        if(id == null){
            return create(this);
        }

        return Database.getInstance().updateQuery(
                QueryType.UPDATE,
                TableType.USER,
                new StatementString(username, StatementType.STRING),
                new StatementString(password, StatementType.STRING),
                new StatementString(dateOfBirth.toString(), StatementType.DATE),
                new StatementString(email, StatementType.STRING),
                new StatementString(createdOn.toString(), StatementType.DATE),
                new StatementString(lastLogin.toString(), StatementType.DATE),
                new StatementString(String.valueOf(id), StatementType.INT)
        );
    }

    @Override
    public boolean delete(){
        return Database.getInstance().updateQuery(
                QueryType.DELETE,
                TableType.USER,
                new StatementString(username, StatementType.STRING),
                new StatementString(email, StatementType.STRING),
                new StatementString(dateOfBirth.toString(), StatementType.DATE)
        );
    }

    public static User checkCredentials(String username, String password){
        ArrayList<DatabaseObject> results = Database.getInstance().selectQuery(
                QueryType.CHECK_CREDENTIALS,
                TableType.USER,
                new StatementString(username, StatementType.STRING),
                new StatementString(password, StatementType.STRING));
        return (results.size() > 0)? (User)results.get(0): null;
    }

    public static DatabaseObject findById(int id) {
        ArrayList<DatabaseObject> results = Database.getInstance().selectQuery(
                QueryType.SELECT_BY_ID,
                TableType.USER,
                new StatementString(String.valueOf(id), StatementType.INT));

        return (results.size() > 0)? results.get(0): null;
    }

    public static ArrayList<DatabaseObject> findByUsername(String username) {
        return Database.getInstance().selectQuery(QueryType.SELECT_USER_USERNAME, TableType.USER, new StatementString(username, StatementType.STRING));
    }

    public static ArrayList<DatabaseObject> findByUsernameLike(String username) {
        return Database.getInstance().selectQuery(QueryType.SELECT_USER_USERNAME_LIKE, TableType.USER, new StatementString(username, StatementType.STRING_LIKE_AFTER));
    }

    public static ArrayList<DatabaseObject> findByEmail(String email) {
        return Database.getInstance().selectQuery(QueryType.SELECT_USER_EMAIL, TableType.USER, new StatementString(email, StatementType.STRING));
    }

    public static ArrayList<DatabaseObject> findByEmailLike(String email) {
        return Database.getInstance().selectQuery(QueryType.SELECT_USER_EMAIL_LIKE, TableType.USER, new StatementString(email, StatementType.STRING_LIKE_AFTER));
    }

    public static List<DatabaseObject> getAll() {
        return Database.getInstance().selectQuery(
                QueryType.SELECT_ALL,
                TableType.USER);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ArrayList<DatabaseObject> getBankAccounts() {
        return BankAccount.findByUserId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
