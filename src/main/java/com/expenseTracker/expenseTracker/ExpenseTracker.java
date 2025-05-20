package com.expenseTracker.expenseTracker;

import com.expenseTracker.enums.TransactionType;

import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseTracker {

   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       TransactionManager transactionManager = new TransactionManager();
       while (true) {
           System.out.println("1. Add Transaction");
           System.out.println("2. Load Transactions from File");
           System.out.println("3. Save Transactions to File");
           System.out.println("4. View Monthly Summary");
           System.out.println("5. Exit");
           System.out.print("Choose option: ");
           int choice = sc.nextInt();

           switch (choice) {
               case 1 -> {
                   System.out.print("Enter type (income/expense): ");
                   String typeStr = sc.next().toUpperCase();
                   TransactionType type = TransactionType.valueOf(typeStr);

                   System.out.print("Enter category: ");
                   String category = sc.next();

                   System.out.print("Enter amount: ");
                   double amount = sc.nextDouble();

                   System.out.print("Enter date (yyyy-mm-dd): ");
                   LocalDate date = LocalDate.parse(sc.next());

                   transactionManager.addTransaction(new Transaction(type, category, amount, date));
               }
               case 2 -> {
                   System.out.print("Enter file path: ");
                   String path = sc.next();
                   try {
                       transactionManager.loadFromFile(path);
                       System.out.println("Data loaded.");
                   } catch (Exception e) {
                       System.out.println("Error loading file: " + e.getMessage());
                   }
               }
               case 3 -> {
                   System.out.print("Enter file path: ");
                   String path = sc.next();
                   try {
                       transactionManager.saveToFile(path);
                       System.out.println("Data saved.");
                   } catch (Exception e) {
                       System.out.println("Error saving file: " + e.getMessage());
                   }
               }
               case 4 -> {
                   System.out.print("Enter month (1-12): ");
                   int month = sc.nextInt();
                   transactionManager.printMonthlyTransactionSummary(month);
               }
               case 5 -> {
                   System.out.println("Exiting.");
                   sc.close();
                   return;
               }
               default -> System.out.println("Invalid choice.");
           }
       }
   }

}
