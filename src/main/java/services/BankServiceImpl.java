package services;

import model.Account;
import model.AccountType;
import model.Bank;
import repository.BankService;

import java.util.List;
import java.util.Random;

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
    public Account createAccount(String ownerName, String Password, AccountType type, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }
        if(verification2(ownerName, Password)) {
            throw new IllegalArgumentException("Account already exists.");
        }
        int id = nextId++;
        String SerialNumber = generateCardNumber();
        System.out.println("banck current ID: " + nextId);
        Account account = new Account(id, SerialNumber, ownerName, Password, type, initialDeposit);
        bank.addAccount(account);
        return account;
    }

    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));

            if ((i + 1) % 4 == 0 && i != 15) {
                cardNumber.append("-");
            }
        }

        return cardNumber.toString();
    }

    @Override
    public boolean verification(String ownerName, String serialNumber, String password) {
        Account account = bank.findAccountByOwnerNameAndSerialNumber(ownerName, serialNumber);

        if (account == null) {
            return false;
        }
        return account.getPassword().equals(password);
    }
    @Override
    public boolean verification2(String ownerName, String password) {
        Account account = bank.findAccountByNameAndPassword(ownerName, password);
        if (account == null) {
            return false;
        }
        return true;
    }


    @Override
    public Account deposit(String OwnerName, String SerialNumber, double amount) {
        Account account = bank.findAccountByOwnerNameAndSerialNumber(OwnerName, SerialNumber);
        if (account == null || amount <= 0) {
            throw new IllegalArgumentException("Invalid deposit");
        }
        account.setBalance(account.getBalance() + amount);
        return account;
    }


    @Override
    public Account withdraw(String OwnerName, String SerialNumber, double amount) {
        Account account = bank.findAccountByOwnerNameAndSerialNumber(OwnerName, SerialNumber);
        if (account == null || amount <= 0 || amount > account.getBalance()) {
            throw new IllegalArgumentException("Invalid withdraw");
        }
        account.setBalance(account.getBalance() - amount);
        return account;
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
