package model;

public class ModelTest {
    public static void main(String[] args) {
        BankService service = new BankServiceImpl();

        Account a1 = service.createAccount("Alice", AccountType.CHECKING, 1000);
        Account a2 = service.createAccount("Bob", AccountType.SAVINGS, 200);

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

