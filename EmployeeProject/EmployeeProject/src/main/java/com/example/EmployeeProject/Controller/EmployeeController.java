package com.example.EmployeeProject.Controller;

import com.example.EmployeeProject.Model.*;
import com.example.EmployeeProject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/postEmployee")
    public Employee addAllEmployees(@RequestBody Employee emp)
    {

        return employeeService.saveEmp(emp);
    }
    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees()
    {
        return employeeService.getAllEmployee();
    }
    @GetMapping("getBalance/{empId}")
    public  String getBalance(@PathVariable("empId") int empId)
    {
        return employeeService.getBalanceInPrimaryAccount(empId);
    }
    @PutMapping("/changePrimaryAccount/{empId}/{primaryAccountId}")
    public String changePrimaryAccount(@PathVariable("empId") int empId,@PathVariable("primaryAccountId") int primaryAccountId)
    {
        return employeeService.changePrimaryAccount(empId,primaryAccountId);
    }

    @PutMapping("/CreditMoney/{empId}/{money}")
    public String creditMoney(@PathVariable("empId") int empId,@PathVariable("money") Double money)
    {
        return employeeService.creditToPrimaryAccount(empId,money);
    }
    @PutMapping("DebitMoney/{empId}/{money}")
    public String debitMoney(@PathVariable("empId") int empId,@PathVariable("money") Double money)
    {
        return employeeService.debitFromPrimaryAccount(empId,money);
    }

}
