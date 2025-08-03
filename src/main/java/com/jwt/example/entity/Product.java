package com.jwt.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name="product")
@Data
public class Product extends Auditable implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column
    private String stockName;

    @Column
    private int quantity;

    @Column
    @Version
    private Integer version;

}
