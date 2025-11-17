package com.bankapp;

/**
 * Service class containing account operations such as deposit, withdraw and
 * transfer. Methods perform basic validation and throw application-specific
 * exceptions when invalid input or insufficient funds are encountered.
 */
public class AccountService {

    /**
     * Deposit the given amount into the provided account.
     *
     * @param account the account to deposit into
     * @param amount amount to deposit (must be non-negative)
     * @throws InvalidAmount when {@code amount} is negative
     */
    public void  deposit(Account account, double amount) throws InvalidAmount{
        if(amount<0){
            throw new InvalidAmount("Ammount can't be negative");
        }
        account.setBalance(account.getBalance()+amount);

        System.out.println("Deposite successful \nNew balance: " + account.getBalance());
        
    }

    /**
     * Withdraw the given amount from the provided account after validating
     * sufficient balance.
     *
     * @param account the account to withdraw from
     * @param amount amount to withdraw (must be non-negative and <= balance)
     * @throws InvalidAmount when {@code amount} is negative
     * @throws InsufficientAmount when the account does not have enough balance
     */
    public void withdraw(Account account, double amount) throws InsufficientAmount, InvalidAmount{
        if(amount<0){
            throw new InvalidAmount("Ammount can't be negative");
        }
        if(account.getBalance()<amount){
            throw new InsufficientAmount("You don't have sufficient balance for this transaction");
        }
        account.setBalance(account.getBalance()-amount);
        System.out.println("Withdraw Successfull \nBalance Remianing: "+ account.getBalance());
        
    }

    /**
     * Transfer an amount from one account to another. Uses {@link #withdraw}
     * and {@link #deposit} to perform the operation so existing validation and
     * logging is preserved.
     *
     * @param fromAccount source account
     * @param toAccount destination account
     * @param amount amount to transfer
     * @throws InvalidAmount when {@code amount} is negative
     * @throws InsufficientAmount when source account has insufficient funds
     */
    public void transfer(Account fromAccount, Account toAccount, double amount) throws InvalidAmount, InsufficientAmount {
        
       withdraw(fromAccount, amount);
       deposit(toAccount, amount);
        
    }
    
}
