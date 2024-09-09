package com.example.Task1.Controller;

import com.example.Task1.payload.ApiResponse;
import com.example.Task1.payload.CustomerDto;
import com.example.Task1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task1/")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @PostMapping("customer")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto)
    {
        CustomerDto customer = this.customerService.createCustomer(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("customer/{customerId}")
    public void updateCustomer(@RequestBody CustomerDto customerDto,@PathVariable int customerId)
    {
       this.customerService.updateCustomer(customerDto,customerId);
    }

    @DeleteMapping("customer/{customerId}")
    public void deleteCustomer(@PathVariable int customerId)
    {
        this.customerService.deleteCustomerById(customerId);
    }

    @GetMapping("customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers()
    {
        return new ResponseEntity<>(this.customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("customer/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int customerId)
    {
        CustomerDto customerDto = this.customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerDto,HttpStatus.OK);
    }
}
