package com.example.EmployeeProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.EmployeeProject.Model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
