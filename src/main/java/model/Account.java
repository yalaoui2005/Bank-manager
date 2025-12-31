package model;

public class Account {
    private final int id;
    private final String SerialNumber;
    private final String ownerName;
    private final String password;
    private final AccountType type;
    private double balance;

    public Account(int id,String SerialNumber, String ownerName,String password, AccountType type, double initialBalance) {
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.id = id;
        this.SerialNumber = SerialNumber;
        this.ownerName = ownerName;
        this.password = password;
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

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getSerialNumber() {
        return SerialNumber;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                '}';
    }
}
