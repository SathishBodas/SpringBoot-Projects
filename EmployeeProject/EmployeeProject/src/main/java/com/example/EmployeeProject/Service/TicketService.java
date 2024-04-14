package com.example.EmployeeProject.Service;

import com.example.EmployeeProject.Model.Ticket;
import com.example.EmployeeProject.Dao.TicketObject;

import java.util.List;

public interface TicketService {

    List<TicketObject> getEmployeeTickets(Integer employeeId);
    TicketObject getTicketById(Integer ticketId);

    TicketObject addTicket(Integer employeeId, Ticket ticket);
}
