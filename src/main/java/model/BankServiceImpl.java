package model;

import java.util.List;

public class BankServiceImpl implements BankService {
    private final Bank bank;
    private int nextId = 1;

    public BankServiceImpl() {
        this.bank = new Bank();
    }

    public BankServiceImpl(Bank bank) {
        if (bank == null) throw new IllegalArgumentException("Bank cannot be null.");
        this.bank = bank;
    }

    @Override
    public Account createAccount(String ownerName, AccountType type, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }
        int id = nextId++;
        Account account = new Account(id, ownerName, type, initialDeposit);
        bank.addAccount(account);
        return account;
    }

    @Override
    public boolean deposit(int accountId, double amount) {
        Account account = bank.findAccount(accountId);
        if (account == null) return false;
        account.deposit(amount);
        return true;
    }


    @Override
    public boolean withdraw(int accountId, double amount) {
        Account account = bank.findAccount(accountId);
        if (account == null) return false;
        return account.withdraw(amount);
    }

    @Override
    public List<Account> listAccounts() {
        return bank.getAccounts();
    }

    @Override
    public Account getAccount(int accountId) {
        return bank.findAccount(accountId);
    }
}
