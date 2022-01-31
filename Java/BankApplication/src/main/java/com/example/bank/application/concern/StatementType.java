package com.example.bank.application.concern;

public enum StatementType{
    STRING, DATE, STRING_LIKE_AFTER, STRING_LIKE_BEFORE, STRING_LIKE_AROUND, INT,TIMESTAMP;

    public boolean isString(){
        return (this == STRING || this == STRING_LIKE_AFTER || this == STRING_LIKE_BEFORE || this == STRING_LIKE_AROUND);
    }
}
