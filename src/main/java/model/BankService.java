package model;
import java.util.List;

public interface BankService {
    Account createAccount(String ownerName, AccountType type, double initialDeposit);

    boolean deposit(int accountId, double amount);

    boolean withdraw(int accountId, double amount);

    List<Account> listAccounts();

    Account getAccount(int accountId);
}
