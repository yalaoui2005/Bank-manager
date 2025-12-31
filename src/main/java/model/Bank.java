package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bank {

    private static Bank instance;
    private final List<Account> accounts = new ArrayList<>();
    private final List<BankObserver> observers = new ArrayList<>();

    private Bank() { }

    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        accounts.add(account);
    }

    public boolean removeAccount(int id) {
        return accounts.removeIf(a -> a.getId() == id);
    }

    public Account findAccount(int id) {
        for (Account a : accounts) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    public Account findAccountByOwnerNameAndSerialNumber(String ownerName, String serialNumber) {
        for (Account a : accounts) {
            if (a.getOwnerName().equals(ownerName) && a.getSerialNumber().equals(serialNumber)) return a;
        }
        return null;
    }

    public Account findAccountByNameAndPassword(String ownerName, String password) {
        for (Account a : accounts) {
            if (a.getOwnerName().equals(ownerName) && a.getPassword().equals(password)) return a;
        }
        return null;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }



    public void addObserver(BankObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null.");
        }
        observers.add(observer);
    }

    public void removeObserver(BankObserver observer) {
        observers.remove(observer);
    }

    public void notifyPanneReported(String reason) {
        for (BankObserver observer : observers) {
            observer.onPanneReported(reason);
        }
    }

    public void notifyPanneResolved() {
        for (BankObserver observer : observers) {
            observer.onPanneResolved();
        }
    }
}
