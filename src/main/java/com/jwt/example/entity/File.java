package com.jwt.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "File")
@Data
public class File extends Auditable implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Lob
    private byte[] fileData;

    private String secretKey;

}
