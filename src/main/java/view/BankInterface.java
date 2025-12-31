package view;

import java.util.Scanner;
import controlers.BankServiceController;
import model.Account;
import model.AccountType;

import javax.swing.*;

public class BankInterface {
    BankServiceController bankServiceController ;
    private String currentOwnerName;
    private String currentSerialNumber;


    public boolean AuthentificationOptions(int choice) {

        Scanner scanner = new Scanner(System.in);
        boolean success = false;

        if (choice == 1) {
            System.out.println("Please enter your information :");

            System.out.print("1) Name : ");
            String ownerName = scanner.nextLine();

            System.out.print("2) Serial Number : ");
            String serialNumber = scanner.nextLine();

            System.out.print("3) Password : ");
            String password = scanner.nextLine();

            success = bankServiceController.LoginRequest(ownerName, serialNumber, password);

            if (success) {
                currentOwnerName = ownerName;
                currentSerialNumber = serialNumber;
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }

        } else if (choice == 2) {

            System.out.println("Please enter your information :");

            System.out.print("1) Name : ");
            String ownerName = scanner.nextLine();

            System.out.print("2) Password : ");
            String password = scanner.nextLine();

            System.out.print("3) your Initial Deposit : ");
            double initialDeposit = scanner.nextDouble();

            System.out.print("4) choose your account type, 1) Savings 2) Checking: ");
            int accountType = scanner.nextInt();

            AccountType type = (accountType == 1)
                    ? AccountType.SAVINGS
                    : AccountType.CHECKING;

            Account account = bankServiceController.RegisterRequest(
                    ownerName, password, type, initialDeposit
            );

            System.out.println("Account created successfully!");
            System.out.println("Your Serial Number: " + account.getSerialNumber());

            currentOwnerName = ownerName;
            currentSerialNumber = account.getSerialNumber();

            return true;
        }
        return success;
    }


        public void BankOptions() {

        Scanner scanner = new Scanner(System.in);
        boolean userConnected = true;

        while (userConnected) {

            System.out.println("\nBank Operations:");
            System.out.println("1) Deposit");
            System.out.println("2) Withdraw");
            System.out.println("3) Logout");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Amount to deposit: ");
                    double deposit = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        Account acc = bankServiceController.DepositRequest(
                                currentOwnerName, currentSerialNumber, deposit
                        );
                        System.out.println("Deposit successful");
                        showAccount(acc);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Amount to withdraw: ");
                    double withdraw = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        Account acc = bankServiceController.WithdrawRequest(
                                currentOwnerName, currentSerialNumber, withdraw
                        );
                        System.out.println("Withdraw successful");
                        showAccount(acc);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Logged out.");
                    userConnected = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void showAccount(Account account) {
        System.out.println("\nAccount State");
        System.out.println("------------------");
        System.out.println("Owner : " + account.getOwnerName());
        System.out.println("Serial: " + account.getSerialNumber());
        System.out.println("Type  : " + account.getType());
        System.out.println("Balance: " + account.getBalance());
    }



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BankInterface bankInterface = new BankInterface();
        bankInterface.bankServiceController = new BankServiceController();

        boolean bankOpen = true;

        System.out.println("===== WELCOME TO THE BANK =====");

        while (bankOpen) {

            System.out.println("\nAuthentication Options:");
            System.out.println("1) Login");
            System.out.println("2) Create Account");
            System.out.println("0) Exit Bank");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                bankOpen = false;
                System.out.println("Bank closed.");
            }
            else if (choice == 1 || choice == 2) {

                boolean authenticated = bankInterface.AuthentificationOptions(choice);

                if (authenticated) {
                    bankInterface.BankOptions();
                }
            }
            else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }


}
