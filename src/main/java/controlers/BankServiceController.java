package controlers;

import model.Account;
import model.AccountType;
import repository.BankService;
import services.BankServiceImpl;

public class BankServiceController {

    private final BankService bankService = new BankServiceImpl();

    public Boolean LoginRequest(String ownerName, String serialNumber, String password) {
        return bankService.verification(ownerName, serialNumber, password);
    }

    public Account RegisterRequest(String ownerName,
                                   String password,
                                   AccountType type,
                                   double initialDeposit) {
        return bankService.createAccount(ownerName, password, type, initialDeposit);
    }

    public Account DepositRequest(String ownerName, String serialNumber, double amount) {
        return bankService.deposit(ownerName, serialNumber, amount);
    }

    public Account WithdrawRequest(String ownerName, String serialNumber, double amount) {
        return bankService.withdraw(ownerName, serialNumber, amount);
    }


    public void ReportPanne(String reason) {
        bankService.reportPanne(reason);
    }

    public void ResolvePanne() {
        bankService.resolvePanne();
    }

    public Account ApplyInterest(String ownerName, String serialNumber) {
        return bankService.applyInterestToAccount(ownerName, serialNumber);
    }
}
