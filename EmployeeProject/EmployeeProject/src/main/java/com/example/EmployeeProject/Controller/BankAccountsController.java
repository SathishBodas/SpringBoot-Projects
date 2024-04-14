package com.example.EmployeeProject.Controller;

import com.example.EmployeeProject.Service.BankAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.EmployeeProject.Model.BankAccounts;
import java.util.List;

@RestController
public class BankAccountsController {
    @Autowired
    private BankAccountsService bankAccountsService;

    @PostMapping("employees/BankAccounts/{employeeId}/addBankAccount")
    public BankAccounts addBankAccount(@PathVariable("employeeId") Integer employeeId, @RequestBody BankAccounts bankAccount) {
        return bankAccountsService.addBankaccount(employeeId, bankAccount);
    }
    @PutMapping("employees/BankAccounts/{employeeId}/creditSalary")
    public String creditSalary(@PathVariable("employeeId") Integer employeeId)
    {
        return  bankAccountsService.creditSalaryToPrimaryAccount(employeeId);
    }

    @GetMapping("BankAccounts/getAllBanks")
    public List<BankAccounts> getAllBanks(){
        return bankAccountsService.getAllBankAccounts();
    }
}
