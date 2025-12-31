package model;

public class NoInterestStrategy implements InterestStrategy {

    @Override
    public double applyInterest(double currentBalance) {
        return currentBalance;
    }
}
