package com.jwt.example.javafeatures;

import java.util.function.Supplier;

public class FunctionalSupplierExample {
    public static void main(String[] args) {
        Supplier<String> supplier = ()->"Hello world";
        System.out.println("value of the supplier ::"+supplier.get());
    }
}
