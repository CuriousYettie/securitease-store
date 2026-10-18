package com.example.store.service;

import com.example.store.dto.CustomerDTO;
import com.example.store.entity.Customer;
import com.example.store.exceptions.InvalidCustomerException;
import com.example.store.mapper.CustomerMapper;
import com.example.store.repository.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerDTO> findAllCustomers(String name) {
        if (name == null || name.isEmpty()) {
            return customerMapper.customersToCustomerDTOs(customerRepository.findAll());
        }
        return customerMapper.customersToCustomerDTOs(customerRepository.findByNameContainingIgnoreCase(name));
    }

    public CustomerDTO createCustomer(Customer customer) {
        try {
            return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
        } catch (Exception ex) {
            log.error("Failed to create customer: {}", ex.getMessage(), ex);
            throw new InvalidCustomerException("Failed to create customer: " + ex.getMessage());
        }
    }
}
