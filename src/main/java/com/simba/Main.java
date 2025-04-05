package com.simba;

import com.simba.atm.TellerMachine;
import com.simba.enums.AccountType;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TellerMachine tellerMachine = new TellerMachine();
        Scanner scanner = new Scanner(System.in);
        boolean running = true; // Control the loop

        while (running) {
            System.out.println("Welcome to the Bank of the West ATM");
            System.out.println("Please enter your choice:");
            System.out.println("1. Add Customer");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Please enter the account type:");
                    System.out.println("1. Current Account");
                    System.out.println("2. Savings Account");
                    int accountType = scanner.nextInt(); // No need for a new scanner
                    System.out.println("Please enter the opening balance:");
                    double openingBalance = scanner.nextDouble();
                    System.out.println("Please enter the currency:");
                    String currency = scanner.next();
                    tellerMachine.addCustomer(AccountType.fromOrdinal(accountType), openingBalance, currency);
                }
                case 2 -> {
                    System.out.println("Please enter the amount to withdraw:");
                    BigDecimal amount = scanner.nextBigDecimal();
                    System.out.println("Please enter the currency:");
                    String currency = scanner.next();
                    System.out.println("Please enter the customer ID:");
                    int customerId = scanner.nextInt();
                    System.out.println("Please enter the account type:");
                    System.out.println("1. Current Account");
                    System.out.println("2. Savings Account");
                    int accountType = scanner.nextInt(); // Reuse the existing scanner
                    tellerMachine.withdrawMoney(amount, currency, customerId, AccountType.fromOrdinal(accountType));
                }
                case 3 -> {
                    System.out.println("Please enter the amount to deposit:");
                    BigDecimal amount = scanner.nextBigDecimal();
                    System.out.println("Please enter the currency:");
                    String currency = scanner.next();
                    System.out.println("Please enter the customer ID:");
                    int customerId = scanner.nextInt();
                    System.out.println("Please enter the account type:");
                    System.out.println("1. Current Account");
                    System.out.println("2. Savings Account");
                    int accountType = scanner.nextInt(); // Reuse the existing scanner
                    tellerMachine.depositMoney(amount, currency, customerId, AccountType.fromOrdinal(accountType));
                }
                case 4 -> {
                    System.out.println("Please enter the customer ID:");
                    int customerId = scanner.nextInt();
                    tellerMachine.checkBalance(customerId);
                }
                case 5 -> {
                    System.out.println("Thank you for using the Bank of the West ATM");
                    running = false; // Set to false to exit the loop
                }
                default -> System.out.println("Invalid choice");
            }
        }
        scanner.close(); // Close the scanner when done
    }
}