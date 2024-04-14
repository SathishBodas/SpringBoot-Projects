package com.example.EmployeeProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
@Entity
@Table(name="BANKACCOUNTS")
public class BankAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BANK_ACCOUNT_ID")
    private Integer bankAccountId;
    @Column(name="BANK_NAME")
    private String bankName;
    @Column(name="BALANCE")
    private Double balance;

    @Column(name="PRIMARY_ACCOUNT")
    private Boolean isPrimary;

    @ManyToOne
    @JoinTable(
            name="EMPLOYEE_BANK_MT",
            joinColumns = @JoinColumn(name="BANK_ACCOUNT_ID"),
            inverseJoinColumns = @JoinColumn(name="EMPLOYEE_ID")
    )
    @JsonIgnoreProperties("bankAccounts")
    private Employee employee;

    public BankAccounts(Integer bankAccountId, String bankName, Double balance,Boolean isPrimary, Employee employee) {
        this.bankAccountId = bankAccountId;
        this.bankName = bankName;
        this.balance = balance;
        this.isPrimary= isPrimary;
        this.employee = employee;
    }
    public BankAccounts(){

    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
