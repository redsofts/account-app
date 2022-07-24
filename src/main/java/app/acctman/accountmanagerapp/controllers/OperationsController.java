package app.acctman.accountmanagerapp.controllers;

import app.acctman.accountmanagerapp.model.AccountInfo;
import app.acctman.accountmanagerapp.model.Transaction;
import app.acctman.accountmanagerapp.service.OperationStatus;
import app.acctman.accountmanagerapp.service.Operations;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private SecureRandom random = new SecureRandom();

    private Operations operations;

    public OperationsController(Operations operations) {
        this.operations = operations;
    }

    @GetMapping("/genAccts")
    public Collection<AccountInfo> genAccounts() {
        List<AccountInfo> accounts = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            accounts.add(this.operations.createAccount("Account Name" + i, generatePhoneNo()));
        }
        return accounts;
    }

    @PostMapping("/depositFunds")
    public ApiResponse depositFunds(
            @RequestParam("acctNo") String acctNo,
            @RequestParam("depositAmt") Double depositAmount) throws Exception {

        ApiResponse response = new ApiResponse();

        if (acctNo.length() != 10) {
            response.setMessage("Invalid account number. Account number must be 10 digits");
            return response;
        }

        if (depositAmount <= 0) {
            response.setMessage("Invalid deposit amount. Deposit amount must be greater than 0");
            return response;
        }

        //Check that account number is valid
        AccountInfo accountInfo = operations.getAccount(acctNo);
        if (accountInfo == null) {
            response.setMessage("Invalid account number. The account number does not exist.");
            return response;
        }

        //Run the deposit 20 times
        for (int i = 0; i < 20; i++) {
            this.operations.deposit(acctNo, depositAmount);
            Thread.sleep(200L);
        }

        List<Transaction> transactions = operations.getTransactionsByAccountNoAndType(acctNo,
                Transaction.TransactionType.DEPOSIT);

        Collections.reverse(transactions);

        response.setStatus(true);
        response.setTransactionsList(transactions);
        response.setMessage("Account deposit was successful");

        return response;
    }

    private String generatePhoneNo() {
        StringBuilder num = new StringBuilder(11);
        num.append("080");
        //generate random 8 digits for the phone number
        for (int i = 0; i < 8; i++) {
            num.append(random.nextInt((9 - 1) + 1) + 1);
        }

        return num.toString();
    }
}
