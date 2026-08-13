package com.backend.pdunghh.hr.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.backend.pdunghh.shared.persistence.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "staffs")
public class StaffEntity extends AuditableEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "employee_code", unique = true, length = 20)
    private String employeeCode;

    @Column(name = "department", length = 50)
    private String department;

    @Column(name = "branch_id")
    private UUID branchId;

    @Column(name = "title", length = 100)
    private String title;
}
