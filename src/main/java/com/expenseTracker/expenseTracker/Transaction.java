package com.expenseTracker.expenseTracker;

import com.expenseTracker.enums.TransactionType;

import java.time.LocalDate;

public class Transaction {
 private TransactionType type;
 private String category;
 private double amount;
 private LocalDate date;

 public Transaction(TransactionType type,String category, double amount, LocalDate date){
     this.type = type;
     this.category = category;
     this.amount = amount;
     this.date = date;

 }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
    public static Transaction fromString(String line) {
        String[] parts = line.split(",");
        return new Transaction(
                TransactionType.valueOf(parts[0]),
                parts[1],
                Double.parseDouble(parts[2]),
                LocalDate.parse(parts[3])
        );
    }

    @Override
    public String toString() {
        return String.format("%s - %-10s ₹%.2f on %s", type, category, amount, date);
    }


}
