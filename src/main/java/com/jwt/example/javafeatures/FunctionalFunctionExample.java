package com.jwt.example.javafeatures;



import java.util.function.Function;

public class FunctionalFunctionExample {
    public static void main(String[] args) {
        Function<String,Integer> function = name -> name.length();
        System.out.println("length of the value :: "+function.apply("Venkatesh"));

        System.out.println("value of the identity :::");
        Function<String, String> identity = Function.identity();
        System.out.println(identity.apply("Same"));
    }
}
