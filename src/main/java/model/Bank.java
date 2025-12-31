package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bank {
    private static Bank instance;
    private final List<Account> accounts = new ArrayList<>();

    private Bank(){}

    public static Bank getInstance(){
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

    public Account findAccountByOwnerNameAndSerialNumber(String OwnerName, String SerialNumber) {
        for (Account a : accounts) {
            if (a.getOwnerName().equals(OwnerName) && a.getSerialNumber().equals(SerialNumber)) return a;
        }
        return null;
    }

    public Account findAccountByNameAndPassword(String OwnerName, String password) {
        for (Account a : accounts) {
            if (a.getOwnerName().equals(OwnerName) && a.getSerialNumber().equals(password)) return a;
        }
        return null;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }
}
