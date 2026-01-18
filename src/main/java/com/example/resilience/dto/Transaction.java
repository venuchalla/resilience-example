package com.example.resilience.dto;

public class Transaction {

    private String userId;

    private double amount;

    private String country;

    private int transactionCountLastHour;

    private boolean fraud;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTransactionCountLastHour() {
        return transactionCountLastHour;
    }

    public void setTransactionCountLastHour(int transactionCountLastHour) {
        this.transactionCountLastHour = transactionCountLastHour;
    }

    public boolean isFraud() {
        return fraud;
    }

    public void setFraud(boolean fraud) {
        this.fraud = fraud;
    }
}
