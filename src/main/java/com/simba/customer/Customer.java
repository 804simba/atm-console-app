package com.simba.customer;


import com.simba.account.Account;

import java.util.List;

public class Customer {
    private int id;
    private List<Account> accounts;

    public Customer(int id, List<Account> accounts) {
        this.id = id;
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", accounts=" + accounts +
                '}';
    }
}
