package com.example.bank.application.models;

import com.example.bank.application.concern.QueryType;
import com.example.bank.application.concern.StatementString;
import com.example.bank.application.concern.StatementType;
import com.example.bank.application.concern.TableType;
import com.example.bank.application.db.Database;
import com.example.bank.application.db.DatabaseObject;

import java.util.ArrayList;

public class SavedAccount extends DatabaseObject {
    private Integer id;
    private int userId;
    private int bankAccountId;

    public SavedAccount(Integer id, int bank_account_id, int user_id) {
        this.id = id;
        this.userId = user_id;
        this.bankAccountId = bank_account_id;
    }

    public static boolean create(SavedAccount savedAccount) {
        return Database.getInstance().updateQuery(
                QueryType.INSERT,
                TableType.SAVED_ACCOUNT,
                new StatementString(String.valueOf(savedAccount.bankAccountId), StatementType.INT),
                new StatementString(String.valueOf(savedAccount.userId), StatementType.INT)
        );
    }

    public static DatabaseObject findById(int id){
        ArrayList<DatabaseObject> results = Database.getInstance().selectQuery(
                QueryType.SELECT_BY_ID,
                TableType.SAVED_ACCOUNT,
                new StatementString(String.valueOf(id), StatementType.INT));

        return (results.size() > 0)? results.get(0): null;
    }

    public static ArrayList<DatabaseObject> findByUserId(int user_id){
        return Database.getInstance().selectQuery(
                QueryType.SELECT_SAVED_ACCOUNT_BY_USER_ID,
                TableType.SAVED_ACCOUNT,
                new StatementString(String.valueOf(user_id), StatementType.INT));
    }

    @Override
    public boolean save() {
        return Database.getInstance().updateQuery(
                QueryType.UPDATE,
                TableType.SAVED_ACCOUNT,
                new StatementString(String.valueOf(userId), StatementType.INT),
                new StatementString(String.valueOf(bankAccountId), StatementType.INT),
                new StatementString(String.valueOf(id), StatementType.INT));
    }

    @Override
    public boolean delete() {
        return Database.getInstance().updateQuery(
                QueryType.DELETE,
                TableType.SAVED_ACCOUNT,
                new StatementString(String.valueOf(id), StatementType.INT));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    @Override
    public String toString() {
        return "SavedAccount{" +
                "id=" + id +
                ", user_id=" + userId +
                ", bank_account_id=" + bankAccountId +
                '}';
    }
}
