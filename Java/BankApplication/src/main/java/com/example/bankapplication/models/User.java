package com.example.bankapplication.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    private ArrayList<BankAccount> bankAccounts;

    public User(String username, String password, String email, LocalDate dateOfBirth) {
        this.username = username;
        this.password = encryptPassword(password);
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public String encryptPassword(String password){
        return password;
    }

    public boolean addBankAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
        return true;
    }

    public String encryptSecurityCode(String securityCode){
        return securityCode;
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

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
