package com.example.EmployeeProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.EmployeeProject.Model.*;
import com.example.EmployeeProject.Repository.*;
import com.example.EmployeeProject.Dao.TicketObject;
import java.util.ArrayList;
import java.util.List;
@Service
public class TicketServiceImple implements TicketService {

        @Autowired
        private TicketRepository ticketRepo;
        @Autowired
        private EmployeeRepository employeeRepo;
        @Autowired
        private AdminRepository adminRepo;
        @Override
        public List<TicketObject> getEmployeeTickets(Integer employeeId){
            try {
                Employee employee=employeeRepo.findById(employeeId).get();
                List<Ticket> tickets=ticketRepo.findByTicketEmployee(employee);
                List<TicketObject> ticketObjects=new ArrayList<>();
                for (Ticket t: tickets) {
                    TicketObject ticketObject=new TicketObject();
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
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
            }
        }
    @Override

        public TicketObject getTicketById(Integer ticketId) {
            try {
                Ticket ticket=ticketRepo.findById(ticketId).get();
                TicketObject ticketObject=new TicketObject();
                ticketObject.setTicketId(ticketId);
                int employeeId=ticket.getTicketEmployee().getEmployeeId();
                ticketObject.setEmployeeName(ticket.getTicketEmployee().getEmployeeName());
                ticketObject.setEmployeeId(employeeId);
                ticketObject.setDate(ticket.getDate());
                ticketObject.setType(ticket.getType());
                ticketObject.setIssue(ticket.getIssue());
                ticketObject.setStatus(ticket.getStatus());
                return ticketObject;
            }
            catch(Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket Id not found");
            }
        }
    @Override
        public TicketObject addTicket(Integer employeeId, Ticket ticket) {
            try {
                Employee employee=employeeRepo.findById(employeeId).get();
                ticket.setTicketEmployee(employee);
                ticket.setStatus("unsolved");
                TicketObject ticketObject=new TicketObject();
                ticketObject.setEmployeeId(employeeId);
                ticketObject.setEmployeeName(employee.getEmployeeName());
                String type=ticket.getType();
                ticketObject.setType(type);
                ticketObject.setDate(ticket.getDate());
                ticketObject.setIssue(ticket.getIssue());
                List<Admin> admins=adminRepo.findAll();
                ticketObject.setStatus("unsolved");
                for (Admin admin: admins) {
                    if (admin.getTypeOfTicket().equalsIgnoreCase(type)) {
                        ticket.setAdmin(admin);
                        admin.getTickets().add(ticket);
                        adminRepo.save(admin);
                    }
                }
                adminRepo.saveAll(admins);
                Ticket t=ticketRepo.save(ticket);
                int id=t.getTicketId();
                ticketObject.setTicketId(id);
                return ticketObject;
            }
            catch(Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id not found");
            }
        }
    }


