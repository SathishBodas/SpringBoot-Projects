package com.example.EmployeeProject.Repository;

import com.example.EmployeeProject.Model.Employee;
import com.example.EmployeeProject.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    List<Ticket> findByTicketEmployee(Employee employee);
    List<Ticket> findByDateBetween(LocalDate fromDate, LocalDate toDate);

}
