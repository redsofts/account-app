package app.acctman.accountmanagerapp.service;

import app.acctman.accountmanagerapp.model.AccountInfo;
import app.acctman.accountmanagerapp.model.DepositWithdrawalRequest;
import app.acctman.accountmanagerapp.model.Transaction;

import java.util.Collection;
import java.util.List;

public interface Operations {

    public AccountInfo createAccount(String accountName, String phoneNumber);

    public AccountInfo getAccount(String accountNo);

    public OperationStatus deposit(String accountNo, double amount);

    public List<Transaction> getTransactionsByAccountNoAndType(String accountNo, Transaction.TransactionType type);


    List<Transaction> listAllTransaction();

}
