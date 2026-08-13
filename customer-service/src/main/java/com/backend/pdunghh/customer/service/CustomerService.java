package com.backend.pdunghh.customer.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.backend.pdunghh.customer.dto.response.CustomerResponse;
import com.backend.pdunghh.customer.entity.CustomerEntity;
import com.backend.pdunghh.customer.mapper.CustomerMapper;
import com.backend.pdunghh.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse getMyProfile(UUID userId) {
        CustomerEntity entity = customerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Customer profile not found for user ID: " + userId));
        
        return customerMapper.toResponse(entity);
    }
}
