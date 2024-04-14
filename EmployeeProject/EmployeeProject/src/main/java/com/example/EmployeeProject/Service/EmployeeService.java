package com.example.EmployeeProject.Service;

import java.util.List;
import com.example.EmployeeProject.Model.*;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    Employee saveEmp(Employee employee);

    Employee getEmployeeById(Integer employeeId);
    String getBalanceInPrimaryAccount(Integer employeeId);

    String changePrimaryAccount(Integer employeeId,Integer primaryAccountId);

    String creditToPrimaryAccount(Integer employeeId,Double amount);

    String debitFromPrimaryAccount(Integer employeeId,Double money);

}
