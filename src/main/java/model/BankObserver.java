package model;

public interface BankObserver {

    void onPanneReported(String reason);

    void onPanneResolved();
}

