package com.backend.pdunghh.customer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.backend.pdunghh.customer.dto.response.CustomerResponse;
import com.backend.pdunghh.customer.entity.CustomerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerResponse toResponse(CustomerEntity entity);
}
