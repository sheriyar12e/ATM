package sheriyarapplication;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private double balance;
    private int pin;
    private ArrayList<String> transactionHistory;
    private Scanner scanner;

    // Constructor to initialize the account with a balance and a PIN
    public ATM(double initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        addTransaction("Account created with balance: " + initialBalance);
    }

    // Main method for the ATM simulation
    public static void main(String[] args) {
        ATM atm = new ATM(50000.0, 1234); // Created an account with a balance of 50000 and PIN 1234
        atm.start();
    }

    // Method for the ATM operations
    private void start() {
        if (authenticateUser()) {
            boolean exit = false;
            while (!exit) {
                showMenu();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        withdrawCash();
                        break;
                    case 3:
                        depositCash();
                        break;
                    case 4:
                        changePin();
                        break;
                    case 5:
                        showTransactionHistory();
                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }

    // Method to tell the user by verifying the PIN
    private boolean authenticateUser() {
        System.out.print("Enter your PIN: ");
        int inputPin = scanner.nextInt();
        return this.pin == inputPin;
    }

    // Method to display the ATM menu
    private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Deposit Cash");
        System.out.println("4. Change PIN");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to check and display the current balance
    private void checkBalance() {
        System.out.println("Your current balance is: $" + this.balance);
    }

    // Method to withdraw cash from the account
    private void withdrawCash() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= this.balance) {
            this.balance -= amount;
            addTransaction("Withdrew: $" + amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // Method to deposit cash into the account
    private void depositCash() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        this.balance += amount;
        addTransaction("Deposited: $" + amount);
        System.out.println("Deposit successful.");
    }

    // Method to change the account PIN
    private void changePin() {
        System.out.print("Enter new PIN: ");
        int newPin = scanner.nextInt();
        this.pin = newPin;
        addTransaction("PIN changed.");
        System.out.println("PIN changed successfully.");
    }

    // Method to show the transaction history
    private void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : this.transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Method to add a transaction to the transaction history
    private void addTransaction(String transaction) {
        this.transactionHistory.add(transaction);
    }
}
