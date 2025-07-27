package com.jwt.example.javafeatures;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntermediateOperationsExample {
    public static void main(String[] args) {

        System.out.println("map intermediate function ::");
        List<String> names = Arrays.asList("arun", "bala", "chitra");
        List<String> upperNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(upperNames);

        System.out.println("map intermediate function for length ::");

        List<String> namesList = Arrays.asList("Arun", "Bala", "Chitra");

        List<Integer> nameLengths = namesList.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(nameLengths);

        System.out.println("map Flattening Nested Structures ::");

        List<List<String>> nestedNames = Arrays.asList(
                Arrays.asList("Arun", "Bala"),
                Arrays.asList("Chitra", "Deepa")
        );

        List<String> allNames = nestedNames.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(allNames);

    }
}
