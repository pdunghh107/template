package com.backend.pdunghh.hr.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.backend.pdunghh.hr.dto.response.StaffResponse;
import com.backend.pdunghh.hr.entity.StaffEntity;
import com.backend.pdunghh.hr.mapper.StaffMapper;
import com.backend.pdunghh.hr.repository.StaffRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    public StaffResponse getMyProfile(UUID userId) {
        StaffEntity entity = staffRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Staff profile not found for user ID: " + userId));
        
        return staffMapper.toResponse(entity);
    }
}
