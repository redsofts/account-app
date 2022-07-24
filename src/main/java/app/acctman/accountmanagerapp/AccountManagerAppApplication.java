package app.acctman.accountmanagerapp;

import app.acctman.accountmanagerapp.model.AccountInfo;
import app.acctman.accountmanagerapp.model.DepositWithdrawalRequest;
import app.acctman.accountmanagerapp.serviceImpl.OperationsImpl;
import app.acctman.accountmanagerapp.util.UtilityClass;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication(scanBasePackages = "app.acctman.accountmanagerapp")
public class AccountManagerAppApplication implements CommandLineRunner {

    public OperationsImpl operations;
    String generatedAccountNumberz = UtilityClass.generateAccountNumber();

    public AccountManagerAppApplication(OperationsImpl operations) {
        this.operations = operations;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountManagerAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }

    /*@Bean
    InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");

        return resolver;
    }*/
}
