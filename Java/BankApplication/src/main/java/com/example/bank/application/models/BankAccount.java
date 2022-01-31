package com.example.bank.application.models;

import com.example.bank.application.db.Database;
import com.example.bank.application.db.DatabaseObject;
import com.example.bank.application.concern.QueryType;
import com.example.bank.application.concern.StatementString;
import com.example.bank.application.concern.StatementType;
import com.example.bank.application.concern.TableType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BankAccount extends DatabaseObject {
    private Integer id;
    private Integer user_id;
    private int balance;
    private String longNumber;
    private String accountNumber;
    private String sortCode;
    private String securityCode;
    private Timestamp createdOn;
    private Timestamp lastAccessed;
    private String name;


    public BankAccount(Integer id, Integer user_id, int balance, String longNumber, String accountNumber, String sortCode, String securityCode, Timestamp createdOn, Timestamp lastAccessed, String name) {
        this.id = id;
        this.user_id = user_id;
        this.balance = balance;
        this.longNumber = longNumber;
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.securityCode = securityCode;
        this.createdOn = createdOn;
        this.lastAccessed = lastAccessed;
        this.name = name;
    }




    public static boolean create(BankAccount bankAccount) {
        return Database.getInstance().updateQuery(
                QueryType.INSERT,
                TableType.BANK_ACCOUNT,
                new StatementString(String.valueOf(bankAccount.user_id), StatementType.INT),
                new StatementString(String.valueOf(bankAccount.balance), StatementType.INT),
                new StatementString(bankAccount.longNumber, StatementType.STRING),
                new StatementString(bankAccount.accountNumber, StatementType.STRING),
                new StatementString(bankAccount.sortCode, StatementType.STRING),
                new StatementString(bankAccount.securityCode, StatementType.STRING),
                new StatementString(bankAccount.createdOn.toString(), StatementType.TIMESTAMP),
                new StatementString(bankAccount.lastAccessed.toString(), StatementType.TIMESTAMP),
                new StatementString(bankAccount.name, StatementType.STRING)
        );
    }

    public static BankAccount checkCredentials(String accountNumber, String sortCode){
        ArrayList<DatabaseObject> results = Database.getInstance().selectQuery(
                QueryType.CHECK_CREDENTIALS,
                TableType.BANK_ACCOUNT,
                new StatementString(sortCode, StatementType.STRING),
                new StatementString(accountNumber, StatementType.STRING));
        System.out.println(results);
        return (results.size() > 0)? (BankAccount) results.get(0): null;
    }

    public boolean withdraw(int amount) throws Exception {
        if(balance - amount < 0) throw new Exception("Not enough money to withdraw");
        balance-= amount;
        return save();
    }
    public boolean deposit(int amount){
        balance += amount;
        return save();
    }


    @Override
    public boolean save(){
        if(id == null){
            return create(this);
        }

        return Database.getInstance().updateQuery(
                QueryType.UPDATE,
                TableType.BANK_ACCOUNT,
                new StatementString(String.valueOf(balance), StatementType.INT),
                new StatementString(longNumber, StatementType.STRING),
                new StatementString(accountNumber, StatementType.STRING),
                new StatementString(sortCode, StatementType.STRING),
                new StatementString(securityCode, StatementType.STRING),
                new StatementString(createdOn.toString(), StatementType.TIMESTAMP),
                new StatementString(lastAccessed.toString(), StatementType.TIMESTAMP),
                new StatementString(String.valueOf(id), StatementType.INT)
        );
    }

    @Override
    public boolean delete(){
        return Database.getInstance().updateQuery(
                QueryType.DELETE,
                TableType.USER,
                new StatementString(sortCode, StatementType.STRING),
                new StatementString(accountNumber, StatementType.STRING)
        );
    }
    public static DatabaseObject findById(int id) {
        ArrayList<DatabaseObject> results = Database.getInstance().selectQuery(
                QueryType.SELECT_BY_ID,
                TableType.BANK_ACCOUNT,
                new StatementString(String.valueOf(id), StatementType.INT));

        return (results.size() > 0)? results.get(0): null;
    }

    public static ArrayList<DatabaseObject> findByUserId(int id) {
        return Database.getInstance().selectQuery(
                QueryType.SELECT_BANK_ACCOUNTS_BY_USER_ID,
                TableType.BANK_ACCOUNT,
                new StatementString(String.valueOf(id), StatementType.INT));
    }


    public static List<DatabaseObject> getAll() {
        return Database.getInstance().selectQuery(
                QueryType.SELECT_ALL,
                TableType.BANK_ACCOUNT);
    }

    public String getLongNumber() {
        return longNumber;
    }

    public void setLongNumber(String longNumber) {
        this.longNumber = longNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Timestamp lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return balance == that.balance && Objects.equals(id, that.id) && Objects.equals(user_id, that.user_id) && Objects.equals(longNumber, that.longNumber) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(sortCode, that.sortCode) && Objects.equals(securityCode, that.securityCode) && Objects.equals(createdOn, that.createdOn) && Objects.equals(lastAccessed, that.lastAccessed) && Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                ", sortCode='" + sortCode + '\'' +
                '}';
    }
}
