package services;

import model.BankObserver;

public class ServiceStatusObserver implements BankObserver {

    @Override
    public void onPanneReported(String reason) {
        System.out.println("[ALERT] Bank service is DOWN. Reason: " + reason);
    }

    @Override
    public void onPanneResolved() {
        System.out.println("[INFO] Bank service is back UP.");
    }
}
