package com.expenseTracker.expenseTracker;

import com.expenseTracker.enums.TransactionType;

import java.io.*;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransactionManager {

    private List<Transaction> transactionList = new ArrayList<>();

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }


    public void loadFromFile(String fileName) throws IOException {
        transactionList.clear();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            transactionList.add(Transaction.fromString(line));
        }
        reader.close();
    }

    public void saveToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for(Transaction transaction: transactionList){
            writer.write(transaction.getType() + "," + transaction.getCategory() + "," + transaction.getAmount() + "," + transaction.getDate());

            writer.newLine();
        }
        writer.close();
    }

    public void printMonthlyTransactionSummary(int month){
        double totalIncome = 0;
        double totalExpense = 0;
        System.out.println("\nMonthly Summary for " +
                Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ":\n");

        for (Transaction transaction: transactionList){
            if(transaction.getDate().getMonthValue()== month){
                System.out.println(transaction);
                if(transaction.getType() == TransactionType.INCOME)
                    totalIncome += transaction.getAmount();
                else
                    totalExpense += transaction.getAmount();
            }
        }
        System.out.printf("Total Income: %.2f\nTotal Expense: %.2f\nBalance: %.2f\n\n",
                totalIncome, totalExpense, totalIncome - totalExpense);
    }

}
