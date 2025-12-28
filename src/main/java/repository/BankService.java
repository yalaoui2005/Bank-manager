package repository;
import model.Account;
import model.AccountType;

import java.util.List;

public interface BankService {
    Account createAccount(String ownerName,String SerialNumber, String Password, AccountType type, double initialDeposit);

    boolean deposit(int accountId, double amount);

    boolean withdraw(int accountId, double amount);

    boolean verification(String ownerName, String serialNumber, String password);

    List<Account> listAccounts();

    Account getAccount(int accountId);
}
