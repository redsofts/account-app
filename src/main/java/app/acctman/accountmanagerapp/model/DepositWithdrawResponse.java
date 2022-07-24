package app.acctman.accountmanagerapp.model;

public class DepositWithdrawResponse {

    private double totalBalance;
    private double amount;

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
