package app.acctman.accountmanagerapp.controllers;

import app.acctman.accountmanagerapp.model.AccountInfo;
import app.acctman.accountmanagerapp.serviceImpl.OperationsImpl;
import app.acctman.accountmanagerapp.util.UtilityClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/account-manager")
public class ViewController {

    OperationsImpl operations;
    String generatedAccountNumberz = UtilityClass.generateAccountNumber();

    public ViewController(OperationsImpl operations) {
        this.operations = operations;
    }

    @GetMapping("/page")
    public String viewPage(Model model) {


        return "index";
    }
}