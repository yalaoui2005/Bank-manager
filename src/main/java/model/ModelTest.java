package model;

import repository.BankService;
import services.BankServiceImpl;

public class ModelTest {
    public static void main(String[] args) {
        BankService service = new BankServiceImpl();

        Account a1 = service.createAccount("Alice","12345678","pizza4321", AccountType.CHECKING, 1000);
        Account a2 = service.createAccount("Bob","87654321","cake1234", AccountType.SAVINGS, 200);

        service.deposit(a2.getId(), 50);
        boolean ok = service.withdraw(a1.getId(), 1200); // should fail (insufficient)
        boolean ok2 = service.withdraw(a1.getId(), 300); // should succeed

        System.out.println("Withdraw 1200 from Alice: " + ok);
        System.out.println("Withdraw 300 from Alice: " + ok2);

        System.out.println("\nAll accounts:");
        for (Account a : service.listAccounts()) {
            System.out.println(a);
        }
    }
}

