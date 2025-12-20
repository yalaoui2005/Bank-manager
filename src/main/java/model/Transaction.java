package model;
import java.time.LocalDateTime;

public class Transaction {
    private final int accountId;
    private final TransactionType type;
    private final double amount;
    private final LocalDateTime timestamp;

    public Transaction(int accountId, TransactionType type, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public int getAccountId() { return accountId; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return timestamp + " | " + type + " | " + amount + " | account " + accountId;
    }
}

