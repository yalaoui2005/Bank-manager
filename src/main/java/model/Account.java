package model;

public class Account {
    private final int id;
    private final String serialNumber;
    private final String ownerName;
    private final String password;
    private final AccountType type;
    private final InterestStrategy interestStrategy;
    private double balance;

    public Account(int id,
                   String serialNumber,
                   String ownerName,
                   String password,
                   AccountType type,
                   double initialBalance,
                   InterestStrategy interestStrategy) {

        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        if (serialNumber == null || serialNumber.isBlank()) {
            throw new IllegalArgumentException("Serial number cannot be empty.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Account type cannot be null.");
        }
        if (interestStrategy == null) {
            throw new IllegalArgumentException("Interest strategy cannot be null.");
        }

        this.id = id;
        this.serialNumber = serialNumber;
        this.ownerName = ownerName;
        this.password = password;
        this.type = type;
        this.interestStrategy = interestStrategy;
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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getPassword() {
        return password;
    }

    /** Apply the account's configured interest strategy. */
    public void applyInterest() {
        balance = interestStrategy.applyInterest(balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                '}';
    }
}
