package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bank {
    private final List<Account> accounts = new ArrayList<>();

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

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }
}
