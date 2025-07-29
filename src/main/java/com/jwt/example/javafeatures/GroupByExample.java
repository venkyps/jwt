package com.jwt.example.javafeatures;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", "New York"),
                new Person("Bob", "Los Angeles"),
                new Person("Charlie", "New York"),
                new Person("David", "Chicago"),
                new Person("Eve", "Los Angeles")
        );

        // Group by city
        Map<String, List<Person>> groupedByCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity));

        // Print the grouped map
        groupedByCity.forEach((city, persons) -> {
            System.out.println("City: " + city);
            persons.forEach(System.out::println);
            System.out.println();
        });
    }
}

@AllArgsConstructor
@Data
class Person {

    String name;
    String city;


}
