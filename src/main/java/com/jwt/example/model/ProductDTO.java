package com.jwt.example.model;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String stockName;
    private int quantity;

}
