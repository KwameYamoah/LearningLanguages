package com.example.bank.application.models;

import com.example.bank.application.concern.QueryType;
import com.example.bank.application.concern.StatementString;
import com.example.bank.application.concern.StatementType;
import com.example.bank.application.concern.TableType;
import com.example.bank.application.db.Database;
import com.example.bank.application.db.DatabaseObject;

import java.time.LocalDate;
import java.util.ArrayList;

public class Payment extends DatabaseObject{
    private Integer id;
    private int sourceId;
    private int targetId;
    private Integer  amount;
    private String paymentMethod;
    private String reference;
    private LocalDate transferDate;
    private LocalDate receivedDate;

    public Payment(Integer id, int sourceId, int targetId, Integer amount, String paymentMethod, String reference, LocalDate transferDate, LocalDate receivedDate) {
        this.id = id;
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.reference = reference;
        this.transferDate = transferDate;
        this.receivedDate = receivedDate;
    }

    public static boolean create(Payment payment){
        return Database.getInstance().updateQuery(
                QueryType.INSERT,
                TableType.PAYMENT,
                new StatementString(String.valueOf(payment.getSourceId()), StatementType.INT),
                new StatementString(String.valueOf(payment.getTargetId()), StatementType.INT),
                new StatementString(String.valueOf(payment.getAmount()), StatementType.INT),
                new StatementString(payment.getPaymentMethod(), StatementType.STRING),
                new StatementString(payment.getReference(), StatementType.STRING),
                new StatementString(payment.getTransferDate().toString(), StatementType.DATE),
                new StatementString(payment.getReceivedDate().toString(), StatementType.DATE)
        );
    }

    @Override
    protected boolean save() {
        return false;
    }

    @Override
    protected boolean delete() {
        return false;
    }

    public static ArrayList<DatabaseObject> getAllPayments() {
        return  Database.getInstance().selectQuery(QueryType.SELECT_ALL, TableType.PAYMENT);
    }

    public static ArrayList<DatabaseObject> findById(String id) {
        return Database.getInstance().selectQuery(QueryType.SELECT_BY_ID, TableType.PAYMENT, new StatementString(id, StatementType.STRING));
    }

    public static ArrayList<DatabaseObject> findByBankAccount(String id) {
        return Database.getInstance().selectQuery(
                QueryType.SELECT_PAYMENT_BY_BANK_ACCOUNT,
                TableType.PAYMENT,
                new StatementString(id, StatementType.INT),
                new StatementString(id, StatementType.INT)
        );
    }



    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "sourceId=" + sourceId +
                ", targetId=" + targetId +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", reference='" + reference + '\'' +
                ", transferDate=" + transferDate +
                ", receivedDate=" + receivedDate +
                '}';
    }
}
