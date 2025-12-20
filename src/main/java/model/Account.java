package model;

public class Account {
    private final int id;
    private final String ownerName;
    private final AccountType type;
    private double balance;

    public Account(int id, String ownerName, AccountType type, double initialBalance) {
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.id = id;
        this.ownerName = ownerName;
        this.type = type;
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public AccountType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be > 0.");
        }
        balance += amount;
    }

    /**
     * @return true if withdrawal succeeded, false if insufficient funds.
     */
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be > 0.");
        }
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owner='" + ownerName + '\'' +
                ", type=" + type +
                ", balance=" + String.format("%.2f", balance) +
                '}';
    }
}
