package com.example.EmployeeProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="EMPLOYEE")
public class Employee
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="EMPLOYEE_ID")
    private Integer employeeId;
    @Column(name="NAME")
    private String employeeName;
    @Column(name="EXPERIENCE")
    private Integer yearsOfExperience;
    @Column(name="GRADE")
    private String grade;
    @Column(name="SALARY")
    private Double salary;



    @OneToMany(mappedBy="employee")
    @JsonIgnoreProperties("employee")
    private List<BankAccounts> bankAccounts=new ArrayList<>();

    @OneToMany(mappedBy = "ticketEmployee")
    @JsonIgnoreProperties("ticketEmployee")
    private List<Ticket> tickets;

    public Employee() {}

    public Employee(Integer employeeId, String employeeName, Integer yearsOfExperience, String grade,
                    Double salary, List<BankAccounts> bankAccounts,List<Ticket> tickets) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.yearsOfExperience = yearsOfExperience;
        this.grade = grade;
        this.salary = salary;
        this.bankAccounts = bankAccounts;
        this.tickets=tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(String grade, int experience) {
        Double sal=0.00;
        if (grade.equals("A")) {
            if (experience<=2) {
                sal=30000.00;
            }
            else if (experience>2 && experience<=4) {
                sal=35000.00;
            }
            else if(experience>4){
                sal=45000.00;
            }
        }
        else if (grade.equals("B")) {
            if (experience<=2) {
                sal=25000.00;
            }
            else if (experience>2 && experience<=4) {
                sal=30000.00;
            }
            else if(experience>4) {
                sal=40000.00;
            }
        }
        else if (grade.equals("C")) {
            if (experience<=2) {
                sal=20000.00;
            }
            else if (experience>2 && experience<=4) {
                sal=25000.00;
            }
            else if(experience>4){
                sal=35000.00;
            }
        }
        else if (grade.equals("D")) {
            if (experience<=2) {
                sal=15000.00;
            }
            else if (experience>2 && experience<=4) {
                sal=20000.00;
            }
            else if(experience>4) {
                sal=30000.00;
            }
        }

        this.salary=sal;
    }

    public List<BankAccounts> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccounts> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
