package com.example.EmployeeProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.EmployeeProject.Dao.TicketObject;
import com.example.EmployeeProject.Model.*;
import com.example.EmployeeProject.Repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class AdminServiceImple implements AdminService {

    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private BankAccountsRepository bankRepo;
    TicketObject ticketObject=new TicketObject();
    @Override
    public List<TicketObject> getAllAdminTickets(Integer adminId){
        try {
            Admin admin=adminRepo.findById(adminId).get();
            List<Ticket> tickets= admin.getTickets();
            List<TicketObject> ticketObjects=new ArrayList<>();
            for (Ticket t: tickets) {
                ticketObject.setTicketId(t.getTicketId());
                ticketObject.setEmployeeId(t.getTicketEmployee().getEmployeeId());
                ticketObject.setEmployeeName(t.getTicketEmployee().getEmployeeName());
                ticketObject.setDate(t.getDate());
                ticketObject.setType(t.getType());
                ticketObject.setIssue(t.getIssue());
                ticketObject.setStatus(t.getStatus());
                ticketObjects.add(ticketObject);
            }
            return ticketObjects;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Id not found");
        }
    }
    @Override
    public List<TicketObject> getAdminTicketsByDate(int adminId, String date1, String date2, String status){
        try {
            Admin admin=adminRepo.findById(adminId).get();
            List<Ticket> tickets=admin.getTickets();
            List<Ticket> requiredTickets=new ArrayList<>();
            List<TicketObject> ticketObjects=new ArrayList<>();
            LocalDate fromDate=LocalDate.parse(date1);
            LocalDate toDate=LocalDate.parse(date2);
            List<Ticket> ticketsInGivenDates=ticketRepo.findByDateBetween(fromDate, toDate);
            for (Ticket t: ticketsInGivenDates) {
                if (t.getStatus().equalsIgnoreCase(status)) {
                    requiredTickets.add(t);
                }
            }
            for (Ticket t: requiredTickets) {
                if (!tickets.contains(t)) {
                    requiredTickets.remove(t);
                }
            }
            for (Ticket t: requiredTickets) {
                ticketObject.setTicketId(t.getTicketId());
                ticketObject.setEmployeeId(t.getTicketEmployee().getEmployeeId());
                ticketObject.setEmployeeName(t.getTicketEmployee().getEmployeeName());
                ticketObject.setDate(t.getDate());
                ticketObject.setType(t.getType());
                ticketObject.setIssue(t.getIssue());
                ticketObject.setStatus(t.getStatus());
                ticketObjects.add(ticketObject);
            }
            return ticketObjects;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Id not found");
        }

    }

    @Override
    public String solveTicket(Integer ticketId) {
        try {
            Ticket ticket=ticketRepo.findById(ticketId).get();
            Employee employee=ticket.getTicketEmployee();
            List<BankAccounts> bankAccounts=employee.getBankAccounts();
            if (ticket.getType().equalsIgnoreCase("SALARY")) {
                BankAccounts primaryAcc=null;
                for (BankAccounts b: bankAccounts) {
                    if (b.getIsPrimary()) {
                        primaryAcc=b;
                    }
                }
                Double salary=employee.getSalary();
                Double balance=primaryAcc.getBalance();
                primaryAcc.setBalance(salary+balance);
                bankRepo.save(primaryAcc);
                ticket.setStatus("solved");
                ticketRepo.save(ticket);
                return ("Ticket solved... Salary Credited successfully"+"\n"+"Balance: "+primaryAcc.getBalance());
            }
            else {
                return "Ticket is still in pending!!";
            }
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket Id not found");
        }
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepo.save(admin);
    }
}
