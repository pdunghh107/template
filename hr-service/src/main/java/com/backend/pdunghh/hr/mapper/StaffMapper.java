package com.backend.pdunghh.hr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.backend.pdunghh.hr.dto.response.StaffResponse;
import com.backend.pdunghh.hr.entity.StaffEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StaffMapper {
    StaffResponse toResponse(StaffEntity entity);
}
