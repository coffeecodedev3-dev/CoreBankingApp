package com.bankapp;

/**
 * Simple data model representing a bank account.
 *
 * <p>An {@code Account} holds an account number, user name, password (used
 * only for simple validation in this example) and the current balance.</p>
 */
public class Account {
    /** Unique account identifier as a string. */
    private String accountNumber;

    /** Name of the account holder. */
    private String userName;

    /** Account password used for basic authentication in the console app. */
    private String password;

    /** Current account balance. */
    private double balance;

    /**
     * Create a new account instance.
     *
     * @param accountNumber unique identifier for the account
     * @param userName owner name
     * @param password account password
     * @param balance initial balance
     */
    public Account(String accountNumber, String userName, String password, double balance){
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
    }

    /** @return the account number */
    public String getAccountNumber() {
        return accountNumber;
    }

    /** @return the account holder's user name */
    public String getUserName() {
        return userName;
    }

    /** @return the current account balance */
    public double getBalance() {
        return balance;
    }

    /**
     * Update the account balance. Intended to be used by service methods.
     *
     * @param newbalance new balance value
     */
    public void setBalance(double newbalance) {
        this.balance = newbalance;
    }

    /**
     * Validates the provided password against the account's password.
     *
     * @param inputPassword password to validate
     * @return true if the password matches, false otherwise
     */
    public boolean validatePassword(String inputPassword){
        return password.equals(inputPassword);
    }
}
