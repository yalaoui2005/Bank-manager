package model;

public class SavingInterestStrategy implements InterestStrategy {

    private final double interestRate;

    public SavingInterestStrategy(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
    }

    public SavingInterestStrategy() {
        this(0.02);
    }

    @Override
    public double applyInterest(double currentBalance) {
        return currentBalance + currentBalance * interestRate;
    }
}
