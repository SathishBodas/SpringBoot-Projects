package com.example.EmployeeProject.Service;

import com.example.EmployeeProject.Model.BankAccounts;
import com.example.EmployeeProject.Model.Employee;
import com.example.EmployeeProject.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImple implements EmployeeService{
    @Autowired
    private BankAccountsRepository bankAccountsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployee() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmp(Employee employee)
    {

        try {
            employee.setSalary(employee.getGrade(), employee.getYearsOfExperience());
            return employeeRepository.save(employee);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bank account Id not found");
        }
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        try {
            return employeeRepository.findById(employeeId).get();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String getBalanceInPrimaryAccount(Integer employeeId) {
        try {
            Employee empDetails = employeeRepository.findById(employeeId).get();
            List<BankAccounts> bankAccounts = empDetails.getBankAccounts();
            for (BankAccounts bankAcc : bankAccounts) {
                if (bankAcc.getIsPrimary()) {
                    return "Bank Name: " + bankAcc.getBankName() + "\n" + "Balance :" + bankAcc.getBalance();
                }
            }
            return "No primary account found";
        }catch ( Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
        }

    }

    @Override
    public String changePrimaryAccount(Integer empId, Integer primaryAccountId) {
        try {
                Employee employeeDetails = employeeRepository.findById(empId).get();
                List<BankAccounts> bankAccounts=employeeDetails.getBankAccounts();
                BankAccounts primaryAccount = null;
                for(BankAccounts bankAcc:bankAccounts)
                {
                    if(bankAcc.getBankAccountId()==primaryAccountId) //checking with primary account
                    {
                        primaryAccount=bankAcc;
                        bankAcc.setIsPrimary(true);
                        bankAccountsRepository.save(bankAcc);

                    }
                    else {
                        bankAcc.setIsPrimary(false);
                        bankAccountsRepository.save(bankAcc);
                    }
                }
                bankAccountsRepository.saveAll(bankAccounts);
                if(primaryAccount!=null)
                {
                    Double bal=primaryAccount.getBalance();
                    return ("Primary account has been changed successfully..."+"\n"+ "Balance: "+bal);
                }
                else {
                    return "Primary Account Id is not found";
                }

        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
        }

    }

    @Override
    public String creditToPrimaryAccount(Integer employeeId, Double amount) {

        try {
            Employee employee=employeeRepository.findById(employeeId).get();
            List<BankAccounts> bankAccounts=employee.getBankAccounts();
           // BankAccounts primaryAcc=null;
            for (BankAccounts bankAcc: bankAccounts) {
                if (bankAcc.getIsPrimary()) {

                    Double balance=bankAcc.getBalance();
                    bankAcc.setBalance(balance+amount);
                    bankAccountsRepository.save(bankAcc);
                    return ("Credit Successful...Balance: "+bankAcc.getBalance());

                }
            }
            return "Primary Account not found!!";
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
        }
    }

    @Override
    public String debitFromPrimaryAccount(Integer employeeId, Double amount) {
        try {
            Employee employee = employeeRepository.findById(employeeId).get();
            List<BankAccounts> bankAccounts = employee.getBankAccounts();
            //BankAccount primaryAcc=null;
            for (BankAccounts bankAcc : bankAccounts) {
                if (bankAcc.getIsPrimary())
                {
                    Double balance = bankAcc.getBalance();
                    if (balance >= amount)
                    {
                        bankAcc.setBalance(balance - amount);
                        bankAccountsRepository.save(bankAcc);
                        //bankAcoountsRepository.saveAll(bankAccounts);
                        return ("Debit Successful...Balance: " + bankAcc.getBalance());
                    }
                    else {
                        return "Debit failed due to Insufficient Balance...";
                    }

                }
            }
            return "Primary Account not found!!";
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id not found");
        }
    }

}
