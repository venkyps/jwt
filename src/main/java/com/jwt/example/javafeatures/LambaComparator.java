package com.jwt.example.javafeatures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambaComparator {

    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>();
        nameList.add("arun");
        nameList.add("rani");
        nameList.add("dilip");

        nameList.forEach(name ->{
            System.out.println("before sorting name :: "+name);
        });

        Collections.sort(nameList,(s1,s2)-> s1.compareTo(s2));

        nameList.forEach(name ->{
            System.out.println("after sorting name :: "+name);
        });



    }
}
