package app.acctman.accountmanagerapp.serviceImpl;

import app.acctman.accountmanagerapp.model.AccountInfo;
import app.acctman.accountmanagerapp.model.DepositWithdrawalRequest;
import app.acctman.accountmanagerapp.model.Transaction;
import app.acctman.accountmanagerapp.service.OperationStatus;
import app.acctman.accountmanagerapp.service.Operations;
import app.acctman.accountmanagerapp.util.UtilityClass;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class OperationsImpl  implements Operations {

    /* Contains all accounts */
    private List<AccountInfo> accountDatabase = new ArrayList<>();

    private SecureRandom random = new SecureRandom();

    /* A backing map to the account info database that can be used for locating accounts by account numbers */
    private ConcurrentHashMap<String, AccountInfo> accountInfoMap = new ConcurrentHashMap<>();

    /*  */
    private List<Transaction> transactions = new ArrayList<>();

    //Map to fetch transactions by account number
    Map<String, List<Transaction>> transactionsByAccountNoMap = new ConcurrentHashMap<>();

    ConcurrentHashMap<String, Transaction> transactionsMap = new ConcurrentHashMap<>();
    List<Transaction> list = new ArrayList<Transaction>(transactionsMap.values());
    List<DepositWithdrawalRequest> depositWithdrawalRequests = new ArrayList<>();

    /**
     * Get an account by an account number
     * @param accountNo the account number of the account
     * @return the {@link AccountInfo} associated with the account number or {@code null} if none exists.
     */
    public AccountInfo getAccountByAccountNumber(String accountNo) {
        return accountInfoMap.get(accountNo);
    }

    @Override
    public OperationStatus deposit(String accountNo, double amount) {
        AccountInfo accountInfo = this.accountInfoMap.get(accountNo);
        OperationStatus status = accountInfo.deposit(amount);

        //create transaction
        Transaction tranx = new Transaction(accountNo, amount, status.getFinalBalance(),
                generateTimestamp(),
                Transaction.TransactionType.DEPOSIT,
                status.isSuccess() ? Transaction.TransactionStatus.SUCCESSFUL : Transaction.TransactionStatus.FAILED);

        transactions.add(tranx);
        List<Transaction> acctTranxList = transactionsByAccountNoMap.get(accountNo);
        if (acctTranxList == null) {
            acctTranxList = new ArrayList<>();
            transactionsByAccountNoMap.put(accountNo, acctTranxList);
        }
        acctTranxList.add(tranx);

        return status;
    }

    @Override
    public List<Transaction> getTransactionsByAccountNoAndType(String accountNo, Transaction.TransactionType type) {
        List<Transaction> transactionList = transactionsByAccountNoMap.get(accountNo);
        if (transactionList == null) return Collections.emptyList();
        return transactionList
                .stream()
                .filter(t -> t.getType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public AccountInfo createAccount(String accountName, String phoneNumber) {
        AccountInfo accountInfo = null;
        boolean created = false;
        while (! created) {
            String acctNo = generateAccountNumber();
            //Check the database to ensure we do not have an already existing account with the number
            if ( ! accountInfoMap.containsKey(acctNo) ) {
                accountInfo = new AccountInfo(accountName, phoneNumber, acctNo, generateRandomBalance());
                accountDatabase.add(accountInfo);
                accountInfoMap.put(acctNo, accountInfo);
                created = true;
            }
        }

        return accountInfo;
    }

    @Override
    public AccountInfo getAccount(String accountNo) {
        if (accountNo == null || accountNo.isBlank())
            throw new IllegalArgumentException("Account number cannot be null or empty");

        return this.accountInfoMap.get(accountNo);
    }



    @Override
    public List<Transaction> listAllTransaction() {
        List<Transaction> list = new ArrayList<>(transactionsMap.values());
        return list;
    }

    /**
     * Generate random 10 digits to be used as account numbers
     * @return
     */
    public static String generateAccountNumber(){
        return String.valueOf((long)(Math.random()*1000000 + 3333300000L));
    }

    public double generateRandomBalance() {
        final double balance = random.nextDouble(0D, 10000000D);
       return Math.round(balance * 100.0) / 100.0;
    }

    private static long generateTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }
}
