package com.backend.pdunghh.customer.entity;

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
@Table(name = "customers")
public class CustomerEntity extends AuditableEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "cif_no", unique = true, length = 50)
    private String cifNo;

    @Column(name = "kyc_status", length = 50)
    private String kycStatus;

    @Column(name = "address")
    private String address;

    @Column(name = "tier", length = 20)
    private String tier;
}
