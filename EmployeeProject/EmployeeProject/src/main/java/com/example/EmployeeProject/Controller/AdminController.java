package com.example.EmployeeProject.Controller;

import com.example.EmployeeProject.Service.AdminServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.EmployeeProject.Model.*;
import com.example.EmployeeProject.Dao.TicketObject;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    public AdminServiceImple adminService;

    @GetMapping("/admins/{id}/getAllTickets")
    public List<TicketObject> getAllAdminTickets(@PathVariable("id") Integer adminId){
        return adminService.getAllAdminTickets(adminId);
    }

    @GetMapping("/admins/{id}/tickets")
    public List<TicketObject> getAdminTicketsByDate(@PathVariable("id") Integer adminId, @RequestParam("date1") String date1, @RequestParam("date2") String date2, @RequestParam("status") String status){
        return adminService.getAdminTicketsByDate(adminId, date1, date2, status);
    }

    @PutMapping("/tickets/{id}")
    public String solveTicket(@PathVariable("id") Integer ticketId) {
        return adminService.solveTicket(ticketId);
    }

    @PostMapping("/admins")
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }
}
