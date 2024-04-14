package com.example.EmployeeProject.Controller;

import com.example.EmployeeProject.Service.TicketServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.EmployeeProject.Dao.TicketObject;
import com.example.EmployeeProject.Model.Ticket;


import java.util.List;

@RestController
public class TicketController {
    @Autowired
    public TicketServiceImple ticketService;

    @GetMapping("/employees/{id}/tickets")
    public List<TicketObject> getEmployeeTickets(@PathVariable("id") int employeeId){
        return ticketService.getEmployeeTickets(employeeId);
    }

    @GetMapping("/tickets/{id}")
    public TicketObject getTicketById(@PathVariable("id") int id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping("employees/{id}/addTicket")
    public TicketObject addTicket(@PathVariable("id") int employeeId, @RequestBody Ticket ticket) {
        return ticketService.addTicket(employeeId, ticket);
    }
}
