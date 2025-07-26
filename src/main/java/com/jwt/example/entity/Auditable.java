package com.jwt.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // This annotation indicates that the fields of this class will be mapped to the database columns of the child entities.
@EntityListeners(AuditingEntityListener.class) // This is crucial for Spring Data JPA to automatically populate the audit fields.
public abstract class Auditable {
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdtime;
    @LastModifiedDate
    @Column(name = "updated_time")
    private LocalDateTime updatedtime;
}