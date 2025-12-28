package services;

import model.Account;
import model.AccountType;
import model.Bank;
import repository.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {
    private final Bank bank;
    private int nextId = 0;

    public BankServiceImpl() {
        this.bank = Bank.getInstance();
    }

    public BankServiceImpl(Bank bank) {
        if (bank == null) throw new IllegalArgumentException("Bank cannot be null.");
        this.bank = bank;
    }

    @Override
    public Account createAccount(String ownerName,String SerialNumber, String Password, AccountType type, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }
        int id = nextId++;
        System.out.println("banck current ID: " + nextId);
        Account account = new Account(id, SerialNumber, ownerName, Password, type, initialDeposit);
        bank.addAccount(account);
        return account;
    }

    @Override
    public boolean verification(String ownerName, String SerialNumber, String Password){

    }

    @Override
    public boolean deposit(int accountId, double amount) {
        Account account = bank.findAccount(accountId);
        if (account == null) return false;

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be > 0.");
        }
        account.setBalance(account.getBalance() + amount);
        return true;
    }


    @Override
    public boolean withdraw(int accountId, double amount) {
        Account account = bank.findAccount(accountId);
        if (account == null) return false;
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be > 0.");
        }
        if (amount > account.getBalance()) {
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        return true;
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
