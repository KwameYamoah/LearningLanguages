package com.example.bank.application.concern;

public class StatementString {
    private final String value;
    private final StatementType type;

    //TODO:: maybe statement string value can accept multiple types
    public StatementString(String value, StatementType type) {
        switch (type){
            case STRING_LIKE_BEFORE ->   this.value = "%" + value;
            case STRING_LIKE_AFTER -> this.value = value + "%";
            case STRING_LIKE_AROUND -> this.value = "%" + value + "%";
            default -> this.value = value;
        }
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public StatementType getType() {
        return type;
    }
}

