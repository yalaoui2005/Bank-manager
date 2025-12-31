package controlers;

import model.Account;
import model.AccountType;
import services.BankServiceImpl;

public class BankServiceController {
    BankServiceImpl bankService = new BankServiceImpl();

    public  Boolean LoginRequest(String ownerName, String serialNumber, String password) {
        return bankService.verification(ownerName, serialNumber, password);
    }

    public  Boolean RegisterRequest(String ownerName, String Password, AccountType type, double initialDeposit) {
        Account newaccount = bankService.createAccount(ownerName, Password, type, initialDeposit);
        return bankService.verification(ownerName, newaccount.getSerialNumber(), Password);
    }

    public  Boolean DepositRequest(String ownerName, String serialNumber, double amount) {
        return bankService.deposit(ownerName, serialNumber, amount);
    }

    public Boolean WithdrawRequest(String OwnerName, String SerialNumber, double amount){
        return bankService.withdraw(OwnerName, SerialNumber, amount);
    }
}
