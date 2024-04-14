package com.example.EmployeeProject.Service;

import com.example.EmployeeProject.Model.BankAccounts;
import com.example.EmployeeProject.Model.Employee;
import com.example.EmployeeProject.Repository.BankAccountsRepository;
import com.example.EmployeeProject.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BankAccountsServiceImple  implements  BankAccountsService{

    @Autowired
    private BankAccountsRepository bankAccountsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<BankAccounts> getAllBankAccounts() {
        return bankAccountsRepository.findAll();
    }

    @Override
    public BankAccounts addBankaccount(Integer employeeId,BankAccounts bankAccount) {

        try {
            Employee employee=employeeRepository.findById(employeeId).get();
            if (bankAccount.getIsPrimary()) {
                List<BankAccounts> bankAccounts=employee.getBankAccounts();
                for (BankAccounts b: bankAccounts) {
                    b.setIsPrimary(false);
                }
                bankAccountsRepository.saveAll(bankAccounts);
            }
            bankAccount.setEmployee(employee);
            employee.getBankAccounts().add(bankAccount);
            employeeRepository.save(employee);
            BankAccounts bankAcc= bankAccountsRepository.save(bankAccount);
            return bankAcc;
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee Id not found");
        }
    }

    @Override
    public String creditSalaryToPrimaryAccount(Integer employeeId) {

        try{
            Employee employee =employeeRepository.findById(employeeId).get();
            List<BankAccounts> bankAccounts=employee.getBankAccounts();
            for(BankAccounts bankAcc:bankAccounts)
            {
                if(bankAcc.getIsPrimary())
                {
                    Double salary=employee.getSalary();
                    Double balance=bankAcc.getBalance();
                    bankAcc.setBalance(balance+salary);
                    bankAccountsRepository.save(bankAcc);
                    return ("Salary credited successfully..."+"\n"+"Balance: "+bankAcc.getBalance());
                }

            }
            return "Primary Account not found";
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
        }
    }
}
