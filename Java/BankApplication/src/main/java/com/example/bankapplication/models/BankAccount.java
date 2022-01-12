package com.example.bankapplication.models;

public class BankAccount {
    private String longNumber;
    private String accountNumber;
    private String sortCode;
    private String securityCode;

    public BankAccount(String longNumber, String accountNumber, String sortCode, String securityCode) {
        this.longNumber = longNumber;
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.securityCode = securityCode;
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
}
