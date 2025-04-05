package com.simba.atm;

import com.simba.account.Account;
import com.simba.account.Money;
import com.simba.customer.Customer;
import com.simba.enums.AccountType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TellerMachine {
    private final String bankName;
    private List<Customer> customers;
    private final AtomicInteger ACCOUNT_ID_COUNTER = new AtomicInteger(1);

    public TellerMachine() {
        this.bankName = "Bank of the West";
        this.customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getBankName() {
        return bankName;
    }

    public void withdrawMoney(BigDecimal amount, String currency, int customerId, AccountType accountType) {
        Customer getCustomer = fetchCustomer(customerId);

        Account getAccount = resolveAccount(accountType, getCustomer, currency);

        Money totalBalance = getAccount.getBalance();

        if (totalBalance.getAmount().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        totalBalance.setAmount(totalBalance.getAmount().subtract(amount));
        getAccount.setBalance(totalBalance);
        System.out.println("Withdrawal successful of " + amount + " " + currency);
    }

    private static Account resolveAccount(AccountType accountType, Customer getCustomer, String currency) {
        Account getAccount = null;

        if (getCustomer.getAccounts().isEmpty()) {
            throw new RuntimeException("Accounts not found for customer");
        }

        for (Account account : getCustomer.getAccounts()) {
            if (account.getAccountType() == accountType && account.getCurrency().equalsIgnoreCase(currency)) {
                getAccount = account;
            }
        }

        if (getAccount == null) {
            throw new RuntimeException("Account not found");
        }
        return getAccount;
    }

    private Customer fetchCustomer(int customerId) {
        Customer getCustomer = null;

        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                getCustomer = customer;
            }
        }

        if (getCustomer == null) {
            throw new RuntimeException("Customer not found");
        }
        return getCustomer;
    }

    public void depositMoney(BigDecimal amount, String currency, int customerId, AccountType accountType) {
        Customer getCustomer = fetchCustomer(customerId);
        Account getAccount = resolveAccount(accountType, getCustomer, currency);

        Money totalBalance = getAccount.getBalance();
        totalBalance.setAmount(totalBalance.getAmount().add(amount));
        getAccount.setBalance(totalBalance);
        System.out.println("Deposit successful of " + amount + " " + currency);
    }

    public void addCustomer(AccountType accountType, double openingBalance, String currency) {
        List<Account> accounts1 = new ArrayList<>();
        Money money = new Money(BigDecimal.valueOf(openingBalance), currency.toUpperCase());
        Account a1 = new Account(AccountType.CURRENT, money, currency);
        accounts1.add(a1);
        Customer c1 = new Customer(ACCOUNT_ID_COUNTER.getAndIncrement(), accounts1);
        customers.add(c1);
        System.out.println("Customer added successfully with account type " + accountType);
    }

    public void checkBalance(int customerId) {
        Customer customer = fetchCustomer(customerId);
        System.out.println("Customer ID: " + customer.getId());
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (Account account : customer.getAccounts()) {
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Account Balance: " + account.getBalance());
            totalBalance = totalBalance.add(account.getBalance().getAmount());
        }
        System.out.println("Total Balance: " + totalBalance);
    }

    @Override
    public String toString() {
        return "TellerMachine{" +
                "bankName='" + bankName + '\'' +
                ", customers=" + customers +
                ", ACCOUNT_ID_COUNTER=" + ACCOUNT_ID_COUNTER +
                '}';
    }
}
