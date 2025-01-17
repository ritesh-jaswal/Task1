package com.example.Task1.Controller;

import com.example.Task1.payload.ApiResponse;
import com.example.Task1.payload.CustomerDto;
import com.example.Task1.payload.EmployeeDto;
import com.example.Task1.service.CustomerService;
import com.example.Task1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task1/")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("employee")
    @PreAuthorize("hasAuthority('SCOPE_addemployee')")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
    {
        EmployeeDto employee = this.employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("employee/{employeeId}")
    @PreAuthorize("hasAuthority('SCOPE_updateemployee')")
    public void updateEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable int employeeId)
    {
        this.employeeService.updateEmployee(employeeDto,employeeId);
    }

    @DeleteMapping("employee/{employeeId}")
    @PreAuthorize("hasAuthority('SCOPE_deleteemployee')")
    public void deleteEmployee(@PathVariable int employeeId)
    {
        this.employeeService.deleteEmployeeById(employeeId);
    }

    @GetMapping("employees")
    @PreAuthorize("hasAuthority('SCOPE_reademployee')")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees()
    {
        return new ResponseEntity<>(this.employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("employee/{employeeId}")
    @PreAuthorize("hasAuthority('SCOPE_reademployee')")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int employeeId)
    {
        EmployeeDto employeeDto = this.employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
}
