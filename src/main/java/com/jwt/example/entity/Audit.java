package com.jwt.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="audit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Audit extends Auditable implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column
    private String userName;

    @Column
    private String functionName;

    @Version
    @Column(nullable = false)
    private Integer version;
}
