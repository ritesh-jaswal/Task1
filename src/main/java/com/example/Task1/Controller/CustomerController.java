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
@RequestMapping("task1/customer")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto)
    {
        CustomerDto customer = this.customerService.createCustomer(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto,@PathVariable int customerId)
    {
        CustomerDto customer = this.customerService.updateCustomer(customerDto,customerId);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable int customerId)
    {
        this.customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>(new ApiResponse("Customer Deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> getAllCustomers()
    {
        return new ResponseEntity<>(this.customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int customerId)
    {
        CustomerDto customerDto = this.customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerDto,HttpStatus.OK);
    }
}
