package view;

import java.util.Scanner;
import controlers.BankServiceController;
import model.AccountType;

import javax.swing.*;

public class BankInterface {
    BankServiceController bankServiceController ;
    public void AuthentificationOptions(int choice) {
        if(choice == 1){
            System.out.println("Please enter your information :");
            Scanner scanner = new Scanner(System.in);

            System.out.print("1) Name : ");
            String OwnerName = scanner.nextLine();

            System.out.print("2) Serial Number : ");
            String SerialNumber = scanner.nextLine();

            System.out.print("3) Password : ");
            String Password = scanner.nextLine();
            System.out.println(bankServiceController.LoginRequest(OwnerName, SerialNumber, Password));
        }else if(choice == 2){
            System.out.println("Please enter your information :");
            Scanner scanner = new Scanner(System.in);

            System.out.print("1) Name : ");
            String OwnerName = scanner.nextLine();

            System.out.print("2) Password : ");
            String Password = scanner.nextLine();

            System.out.print("3) your Initial Deposit : ");
            double initialDeposit = scanner.nextDouble();

            System.out.print("4) choose your account type, 1) Savings account 2) Checking account: ");
            int accountType = scanner.nextInt();
            if (accountType == 1){
                System.out.println(bankServiceController.RegisterRequest(OwnerName, Password,  AccountType.SAVINGS, initialDeposit));
            }else if(accountType == 2){
                System.out.println(bankServiceController.RegisterRequest(OwnerName, Password,  AccountType.CHECKING, initialDeposit));
            }
        }
    }

    public void BankOptions(int choice) {

    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BankInterface bankInterface = new BankInterface();
        bankInterface.bankServiceController = new BankServiceController();

        System.out.println("Welcome to the Bank");
        System.out.println("-------------------");

        System.out.println("Authentication Options:");
        System.out.println("1) Login");
        System.out.println("2) Create Account");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        bankInterface.AuthentificationOptions(choice);

        scanner.close();
    }

}
