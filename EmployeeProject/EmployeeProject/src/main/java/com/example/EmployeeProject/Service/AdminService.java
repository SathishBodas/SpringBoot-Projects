package com.example.EmployeeProject.Service;

import com.example.EmployeeProject.Dao.TicketObject;
import com.example.EmployeeProject.Model.Admin;

import java.util.List;

public interface AdminService {

    List<TicketObject> getAllAdminTickets(Integer adminId);
    List<TicketObject> getAdminTicketsByDate(int adminId, String date1, String date2, String status);
    String solveTicket(Integer ticketId);
    Admin addAdmin(Admin admin);

}
