package repository;
import model.Account;
import model.AccountType;

import java.util.List;

public interface BankService {
    Account createAccount(String ownerName, String Password, AccountType type, double initialDeposit);

    Account deposit(String OwnerName, String SerialNumber, double amount);

    Account withdraw(String OwnerName, String SerialNumber, double amount);

    boolean verification(String ownerName, String serialNumber, String password);
    boolean verification2(String ownerName, String password);

    List<Account> listAccounts();

    Account getAccount(int accountId);
}
