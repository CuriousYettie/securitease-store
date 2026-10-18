package com.example.store.controller;

import com.example.store.dto.CustomerDTO;
import com.example.store.entity.Customer;
import com.example.store.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers(@RequestParam(required = false) String name) {
        return customerService.findAllCustomers(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@RequestBody Customer customer) {
        try {
            return customerService.createCustomer(customer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create customer");
        }
    }
}
