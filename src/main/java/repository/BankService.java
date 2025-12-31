package repository;
import model.Account;
import model.AccountType;

import java.util.List;

public interface BankService {
    Account createAccount(String ownerName,String SerialNumber, String Password, AccountType type, double initialDeposit);

    boolean deposit(String OwnerName, String SerialNumber, double amount);

    boolean withdraw(String OwnerName, String SerialNumber, double amount);

    boolean verification(String ownerName, String serialNumber, String password);

    List<Account> listAccounts();

    Account getAccount(int accountId);
}
