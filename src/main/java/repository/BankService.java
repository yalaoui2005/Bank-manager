package repository;

import model.Account;
import model.AccountType;

import java.util.List;

public interface BankService {
    Account createAccount(String ownerName, String password, AccountType type, double initialDeposit);

    Account deposit(String ownerName, String serialNumber, double amount);

    Account withdraw(String ownerName, String serialNumber, double amount);

    boolean verification(String ownerName, String serialNumber, String password);
    boolean verification2(String ownerName, String password);

    List<Account> listAccounts();

    Account getAccount(int accountId);

    void reportPanne(String reason);
    void resolvePanne();

    Account applyInterestToAccount(String ownerName, String serialNumber);
}
