package com.bankapp;

/**
 * Entry point for the console-based Bank application. Creates a {@link Bank}
 * instance and starts its interactive menu.
 */
public class Main {
    /**
     * Application entry point. Initializes the bank and starts the main menu
     * loop. Any uncaught exceptions are caught and logged to standard output.
     *
     * @param args command-line arguments (ignored)
     */
    public static void main(String[] args) {
        Bank myBank = new Bank();
        try {
            myBank.startMenu();
        } catch (Exception e) {
            System.out.println("A critical, unhandled error occurred: " + e.getMessage());
        }
    }
}
