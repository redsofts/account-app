package app.acctman.accountmanagerapp.model;

import app.acctman.accountmanagerapp.service.OperationStatus;

public class AccountInfo {

    private String accountName;
    private String phoneNumber;
    private String accountNumber;
    private volatile double totalBalance;

    public AccountInfo(String accountName, String phoneNumber, String accountNumber, double totalBalance) {
        this.accountName = accountName;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "accountName='" + accountName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", totalBalance=" + totalBalance +
                '}';
    }

    // Getters and Setters
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public OperationStatus deposit(double amount) {
        if (amount > 0) {
            double previous = this.totalBalance;
            double finalBalance = previous + amount;
            this.totalBalance += amount;
            return new OperationStatus(true, previous, finalBalance);
        }

        return new OperationStatus();
    }

    /**
     * Withdraw an amount from the account.
     *
     * This method is synchronized so that we can ensure that the balance never falls below 0
     *
     * @param amount the amount to withdraw
     * @return {@code true} if the withdrawalis successful or {@code false} if otherwise
     */
    public synchronized boolean withdraw(double amount) {
        assert amount > 0 : "Amount to withdraw must be greater than 0";

        if (this.totalBalance > 0 && ((this.totalBalance - amount) >= 0.0D)) {
            this.totalBalance -= amount;
            return true;
        }

        return false;
    }

}

