package com.example.bankapplication.concern;

public class StatementString {
    private String value;
    private StatementType type;

    public StatementString(String value, StatementType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public StatementType getType() {
        return type;
    }
}

