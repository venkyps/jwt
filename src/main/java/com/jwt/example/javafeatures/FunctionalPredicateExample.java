package com.jwt.example.javafeatures;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalPredicateExample {
    public static void main(String[] args) {
        Predicate<String> value = (stringValue) -> "String".equals(stringValue);
        System.out.println("value of the string :: "+value.test("String"));

        System.out.println("Predicate Chaining:::");

        Predicate<String> hasLengthGreaterThan4 = name -> name.length() > 4;
        Predicate<String> startsWithC = name -> name.startsWith("C");

        Predicate<String> complexCheck = hasLengthGreaterThan4.and(startsWithC);

        System.out.println(complexCheck.test("Chitra")); // Output: true

        System.out.println("Predicate with Stream Filtering :::");

        List<String> names = Arrays.asList("Arun", "Bala", "Chitra", "Anbu");
        Predicate<String> startsWithA = name -> name.startsWith("A");

        List<String> filtered = names.stream()
                .filter(startsWithA)
                .collect(Collectors.toList());

        System.out.println(filtered); // Output: [Arun, Anbu]
    }
}
