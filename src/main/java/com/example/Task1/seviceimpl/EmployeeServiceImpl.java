package com.example.Task1.seviceimpl;

import com.example.Task1.exceptions.ResourceNotFoundException;
import com.example.Task1.model.Employees;
import com.example.Task1.payload.EmployeeDto;
import com.example.Task1.repository.EmployeeRepo;
import com.example.Task1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto)
    {
        Employees employee = this.modelMapper.map(employeeDto,Employees.class);

        Employees create = this.employeeRepo.save(employee);
        return this.modelMapper.map(create,EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, int employeeId)
    {
        Employees employee = this.employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee Id", employeeId));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setMiddleName(employeeDto.getMiddleName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setDob(employeeDto.getDob());
        employee.setContactNumber(employeeDto.getContactNumber());
        employee.setEmergencyContactNumber(employeeDto.getEmergencyContactNumber());

        Employees updatedEmployee = this.employeeRepo.save(employee);
        return this.modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(int employeeId)
    {
        Employees employee = this.employeeRepo.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee","Employee Id",employeeId));

        return this.modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployees()
    {
        List<Employees> employees = this.employeeRepo.findAll();
        List<EmployeeDto> employeeDto = employees.stream().map(employee -> this.modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());
        return employeeDto;
    }

    @Override
    public void deleteEmployeeById(int employeeId)
    {
        Employees employee = this.employeeRepo.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee","Employee Id",employeeId));
        this.employeeRepo.delete(employee);
    }
}
