package com.example.Task1.seviceimpl;

import com.example.Task1.exceptions.ResourceNotFoundException;
import com.example.Task1.model.Customers;
import com.example.Task1.payload.CustomerDto;
import com.example.Task1.repository.CustomerRepo;
import com.example.Task1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto)
    {
        Customers customer = this.modelMapper.map(customerDto,Customers.class);
        Customers createCustomer = this.customerRepo.save(customer);
        return this.modelMapper.map(createCustomer,CustomerDto.class);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, int customerId)
    {
        Customers customer = this.customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer","Customer Id",customerId));
        customer.setCustomerName(customerDto.getCustomerName());

        Customers updated = this.customerRepo.save(customer);
        return this.modelMapper.map(updated,CustomerDto.class);
    }

    @Override
    public CustomerDto getCustomerById(int customerId)
    {
        Customers customer = this.customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer","Customer Id",customerId));

        return this.modelMapper.map(customer,CustomerDto.class);
    }

    @Override
    public List<CustomerDto> getAllCustomers()
    {
        List<Customers> customers = this.customerRepo.findAll();

        List<CustomerDto> customerDto = customers.stream().map(customer -> this.modelMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
        return customerDto;
    }

    @Override
    public void deleteCustomerById(int customerId)
    {
        Customers customer = this.customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer","Customer Id",customerId));
        this.customerRepo.delete(customer);
    }
}
