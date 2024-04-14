package com.example.EmployeeProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="ADMIN")
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ADMIN_ID")
    private Integer adminId;
    @Column(name="TYPE_OF_TICKET")
    private String typeOfTicket;
    @OneToMany(mappedBy="admin")
    @JsonIgnoreProperties("admin")
    private List<Ticket> tickets=new ArrayList<>();

    public Admin(){}

    public Admin(Integer adminId, String typeOfTicket, List<Ticket> tickets) {
        this.adminId = adminId;
        this.tickets = tickets;
        this.typeOfTicket=typeOfTicket;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getTypeOfTicket() {
        return typeOfTicket;
    }

    public void setTypeOfTicket(String typeOfTicket) {
        this.typeOfTicket = typeOfTicket;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
