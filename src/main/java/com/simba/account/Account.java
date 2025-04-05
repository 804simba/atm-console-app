package com.simba.account;

import com.simba.enums.AccountType;

import java.util.UUID;

public class Account {
    private String accountNumber;
    private AccountType accountType;
    private String currency;
    private Money balance;

    public Account(AccountType accountType, Money balance, String currency) {
        this.accountNumber = UUID.randomUUID().toString();
        this.accountType = accountType;
        this.currency = currency;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                '}';
    }
}
