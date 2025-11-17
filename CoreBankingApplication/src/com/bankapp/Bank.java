package com.bankapp;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Bank is a simple console-based banking simulator that manages multiple
 * {@link Account} instances. It provides a menu-driven UI for registering
 * accounts, logging in, and performing basic operations such as deposit,
 * withdraw, transfer and balance inquiry.
 *
 * <p>All input/output is done via {@link java.util.Scanner} on standard input
 * and {@link System#out} for output.</p>
 */
public class Bank {

    /** Scanner used to read user input from standard input. */
    Scanner sc = new Scanner(System.in);

    /**
     * Map of account number to {@link Account} objects managed by this bank.
     */
    private HashMap<String, Account> accounts;

    /** Service that performs account operations (deposit/withdraw/transfer). */
    private AccountService accountService;

    /** Counter used to generate new account numbers. Starts at 1000. */
    private long accountCounter;

    public Bank() {
        accounts = new HashMap<>();
        accountService = new AccountService();
        accountCounter = 1000;
    }

    /**
     * Starts the main menu loop for the bank simulator. This method repeatedly
     * prompts the user for actions until the user chooses to exit.
     *
     * @throws InvalidAccount when login/transfer operations reference an invalid account
     */
    public void startMenu() throws InvalidAccount {
        while (true) {
            System.out.println("//// BANK SIMULATOR ////"
                    + "\nPlease select the option you want to perform"
                    + "\n1. Register a new Account"
                    + "\n2. Login to existing account"
                    + "\n3. Exit");
            int choice = readInt("Please choose an option: ");

            switch (choice) {
                case 1:
                    registerAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option! please try again");
            }
        }

    }

    /**
     * Reads an integer value from standard input. Repeats prompt until a valid
     * integer is entered.
     *
     * @param prompt message shown to the user
     * @return the parsed integer
     */
    private int readInt(String prompt) {
        while (true) {
            System.out.println(prompt + ": ");
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Invalid choice! Reason: " + e.getMessage());
            }
        }

    }

    /**
     * Prompts the user for account number and password and validates the
     * credentials. On successful validation, opens the logged-in menu for the
     * authenticated account.
     *
     * @throws InvalidAccount if the provided account number does not exist
     */
    private void login() throws InvalidAccount {
        System.out.println("Enter your account number");
        String accountNumber = sc.nextLine();
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new InvalidAccount("Invalid Account number");
        }

        System.out.println("Enter your password");
        String accountPassword = sc.nextLine();

        if (account.validatePassword(accountPassword)) {
            System.out.println("Login successful! \nWelcome," + account.getUserName());
            showLoggedInMenu(account);
        } else {
            System.out.println("Incorrect account number or password");
        }
    }

    /**
     * Displays the operations menu for a logged-in user and dispatches the
     * selected actions (deposit, withdraw, transfer, balance check, logout).
     *
     * @param account the authenticated {@link Account}
     * @throws InvalidAccount if a transfer is attempted to a non-existent account
     */
    private void showLoggedInMenu(Account account) throws InvalidAccount {
        while (true) {
            System.out.println("Please select the options from below"
                    + "\n1. Deposit"
                    + "\n2. Withdraw"
                    + "\n3. Transfer"
                    + "\n4. Check Balance"
                    + "\n5. Logout");

            int choice = readInt("choice");
            switch (choice) {
                case 1:
                    double amount = readDouble("Enter the amount");
                    try {
                        accountService.deposit(account, amount);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    amount = readDouble("Enter the amount");
                    try {
                        accountService.withdraw(account, amount);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Enter recipient account number");
                    String accountNumber = sc.nextLine();
                    Account recipientAccount = accounts.get(accountNumber);
                    if (recipientAccount == null) {
                        throw new InvalidAccount("Invalid account number");
                    }

                    amount = readDouble("Enter the amount");

                    try {
                        accountService.transfer(account, recipientAccount, amount);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Reads a double (monetary) value from standard input. Continues to prompt
     * until a valid double is entered.
     *
     * @param prompt message shown to the user
     * @return the parsed double value
     */
    private double readDouble(String prompt) {
        while (true) {
            System.out.println(prompt + ": ");
            String input = sc.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (Exception e) {
                System.out.println("Invalid Amount! Reason:" + e.getMessage());
            }
        }
    }

    /**
     * Registers a new account by prompting the user for name, password and an
     * initial deposit. A new account number is generated automatically and the
     * account is stored in the internal accounts map.
     */
    private void registerAccount() {
        System.out.println("Enter account holder name");
        String name = sc.nextLine();

        System.out.println("Please setup your account password");
        String password = sc.nextLine();

        double deposit = readDouble("Enter your initial deposit in numbers");

        String newAccountNumber = String.valueOf(accountCounter);
        accountCounter++;

        Account newAccount = new Account(newAccountNumber, name, password, deposit);

        accounts.put(newAccountNumber, newAccount);

        System.out.println("Account created successfully! Your new account number is: " + newAccountNumber);

    }

}
