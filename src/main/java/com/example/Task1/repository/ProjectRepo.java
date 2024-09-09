package com.example.Task1.repository;

import com.example.Task1.model.Customers;
import com.example.Task1.model.Employees;
import com.example.Task1.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Projects,Integer>
{
    List<Projects> findAllByEmployees(Employees employees);
    List<Projects> findAllByCustomers(Customers customers);
}
