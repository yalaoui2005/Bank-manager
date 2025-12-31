package controlers;

import model.Account;
import model.AccountType;
import services.BankServiceImpl;

public class BankServiceController {
    BankServiceImpl bankService = new BankServiceImpl();

    public  Boolean LoginRequest(String ownerName, String serialNumber, String password) {
        return bankService.verification(ownerName, serialNumber, password);
    }

    public Account RegisterRequest(String ownerName, String password,
                                   AccountType type, double initialDeposit) {
        return bankService.createAccount(ownerName, password, type, initialDeposit);
    }


    public Account DepositRequest(String ownerName, String serialNumber, double amount) {
        return bankService.deposit(ownerName, serialNumber, amount);
    }

    public Account WithdrawRequest(String ownerName, String serialNumber, double amount) {
        return bankService.withdraw(ownerName, serialNumber, amount);
    }

}
