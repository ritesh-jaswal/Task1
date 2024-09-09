package com.example.Task1.service;

import com.example.Task1.payload.CustomerDto;

import java.util.List;

public interface CustomerService
{
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(CustomerDto customerDto,int customerId);
    CustomerDto getCustomerById(int customerId);
    List<CustomerDto> getAllCustomers();
    void deleteCustomerById(int customerId);
}
