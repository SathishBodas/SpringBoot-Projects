package com.example.EmployeeProject.Repository;

import com.example.EmployeeProject.Model.BankAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountsRepository extends JpaRepository<BankAccounts,Integer> {
}
