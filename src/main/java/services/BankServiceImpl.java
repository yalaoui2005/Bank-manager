package services;

import model.Account;
import model.AccountType;
import model.Bank;
import model.InterestStrategy;
import model.SavingInterestStrategy;
import model.NoInterestStrategy;
import model.BankObserver;
import repository.BankService;

import java.util.List;
import java.util.Random;

public class BankServiceImpl implements BankService {
    private final Bank bank;
    private final BankObserver statusObserver;
    private int nextId = 0;

    public BankServiceImpl() {
        this(Bank.getInstance());
    }

    public BankServiceImpl(Bank bank) {
        if (bank == null) throw new IllegalArgumentException("Bank cannot be null.");
        this.bank = bank;
        this.statusObserver = new ServiceStatusObserver();
        this.bank.addObserver(statusObserver);
    }

    @Override
    public Account createAccount(String ownerName, String password, AccountType type, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }
        if (verification2(ownerName, password)) {
            throw new IllegalArgumentException("Account already exists.");
        }

        int id = nextId++;
        String serialNumber = generateCardNumber();


        InterestStrategy strategy = (type == AccountType.SAVINGS)
                ? new SavingInterestStrategy()
                : new NoInterestStrategy();

        Account account = new Account(id, serialNumber, ownerName, password, type, initialDeposit, strategy);
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
        return account != null;
    }

    @Override
    public Account deposit(String ownerName, String serialNumber, double amount) {
        Account account = bank.findAccountByOwnerNameAndSerialNumber(ownerName, serialNumber);
        if (account == null || amount <= 0) {
            throw new IllegalArgumentException("Invalid deposit");
        }
        account.setBalance(account.getBalance() + amount);
        return account;
    }

    @Override
    public Account withdraw(String ownerName, String serialNumber, double amount) {
        Account account = bank.findAccountByOwnerNameAndSerialNumber(ownerName, serialNumber);
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

    // -------- Extra API for the Observer pattern --------

    /** Report that the system is in panne (down). All observers will be notified. */
    @Override
    public void reportPanne(String reason) {
        bank.notifyPanneReported(reason);
    }

    /** Report that the system is back to normal. All observers will be notified. */
    @Override
    public void resolvePanne() {
        bank.notifyPanneResolved();
    }

    /** Apply interest to a specific account using its strategy. */
    @Override
    public Account applyInterestToAccount(String ownerName, String serialNumber) {
        Account account = bank.findAccountByOwnerNameAndSerialNumber(ownerName, serialNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        account.applyInterest();
        return account;
    }
}
