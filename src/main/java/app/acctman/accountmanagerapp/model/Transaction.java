package app.acctman.accountmanagerapp.model;

public class Transaction implements Comparable {

    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }

    public enum TransactionStatus {
        SUCCESSFUL,
        FAILED
    }

    private String accountNumber;
    private double amount;
    private long createdAt;
    private double finalAmount;
    private TransactionType type;
    private TransactionStatus status;

    public Transaction(){}

    public Transaction(String accountNumber, double amount, double finalAmount, long createdAt, TransactionType type, TransactionStatus status) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
        this.finalAmount = finalAmount;
        this.status = status;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Override
    public int compareTo(Object o) {
        Transaction tranx = (Transaction) o;
        if ( this.createdAt > tranx.getCreatedAt()) {
            return 1;
        }
        if ( this.createdAt == tranx.getCreatedAt()) {
            return 0;
        }

        return -1;
    }
}
