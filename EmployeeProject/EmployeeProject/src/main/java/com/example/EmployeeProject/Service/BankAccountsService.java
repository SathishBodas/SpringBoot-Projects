package com.example.EmployeeProject.Service;

import com.example.EmployeeProject.Model.BankAccounts;
import java.util.List;
public interface BankAccountsService {

    List<BankAccounts> getAllBankAccounts();

    BankAccounts addBankaccount(Integer employeeId,BankAccounts bankAccount);
    String creditSalaryToPrimaryAccount(Integer employeeId);
}
