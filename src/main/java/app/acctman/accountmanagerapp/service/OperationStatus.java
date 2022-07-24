package app.acctman.accountmanagerapp.service;

public class OperationStatus {
    private boolean success;
    private double previousBalance;
    private double finalBalance;

    public OperationStatus() {
    }

    public OperationStatus(boolean success, double previousBalance, double finalBalance) {
        this.success = success;
        this.previousBalance = previousBalance;
        this.finalBalance = finalBalance;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public double getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(double previousBalance) {
        this.previousBalance = previousBalance;
    }

    public double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(double finalBalance) {
        this.finalBalance = finalBalance;
    }
}
