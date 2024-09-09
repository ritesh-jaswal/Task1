package com.example.Task1.service;

import com.example.Task1.payload.CustomerDto;
import com.example.Task1.payload.EmployeeDto;

import java.util.List;

public interface EmployeeService
{
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(EmployeeDto employeeDto,int employeeId);
    EmployeeDto getEmployeeById(int employeeId);
    List<EmployeeDto> getAllEmployees();
    void deleteEmployeeById(int employeeId);
}
