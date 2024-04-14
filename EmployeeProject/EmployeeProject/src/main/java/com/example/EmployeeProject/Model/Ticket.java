package com.example.EmployeeProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name="TICKET")
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="TICKET_ID")
    private Integer ticketId;
    @ManyToOne
    @JoinTable(
            name="TICKET_ID_EMPLOYEE_ID_JT",
            joinColumns = @JoinColumn(name = "TICKET_ID"),
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID")
    )
    @JsonIgnoreProperties("tickets")
    private Employee ticketEmployee;
    @Column(name="DATE")
    private LocalDate date;
    @Column(name="TYPE")
    private String type;
    @Column(name="ISSUE")
    private String issue;
    @Column(name="STATUS")
    private String status;

    @ManyToOne
    @JoinTable(
            name="TICKET_ADMIN_JT",
            joinColumns = @JoinColumn(name="TICKET_ID"),
            inverseJoinColumns = @JoinColumn(name="ADMIN_ID")
    )
    @JsonIgnoreProperties("tickets")
    private Admin admin;


    public Ticket() {}

    public Ticket(Integer ticketId, Employee ticketEmployee, LocalDate date, String type, String issue, String status, Admin admin) {
        this.ticketId = ticketId;
        this.ticketEmployee = ticketEmployee;
        this.date=date;
        this.type=type;
        this.issue = issue;
        this.status = status;
        this.admin=admin;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Employee getTicketEmployee() {
        return ticketEmployee;
    }

    public void setTicketEmployee(Employee ticketEmployee) {
        this.ticketEmployee = ticketEmployee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
