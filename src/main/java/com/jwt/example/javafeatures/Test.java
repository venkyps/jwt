package com.jwt.example.javafeatures;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

        Employee employeeOne = new Employee("arun",100);
        Employee employeeTwo = new Employee("basha",300);
        Employee employeeThree = new Employee("chandy",350);
        Employee employeeFour = new Employee("durai",400);

        List<Employee> employeeList = Arrays.asList(employeeOne,employeeTwo,employeeThree,employeeFour);
        Comparator<Employee> comparator = new EmployeeComparator();
        Employee highest = employeeList.get(0);

       /* for (Employee e : employeeList) {
            if (comparator.compare(e, highest) > 0) {
                highest = e;
            }
        }

        System.out.println("highest salary"+highest);
        */

       /* Collections.sort(employeeList,new EmployeeComparator());

        employeeList.forEach( empl ->{
            System.out.println("employee sort based on salary "+empl.getName());
        });*/





        Predicate<Employee> employeePredicate = (employee-> employee.getSalary()>100);


       List<Employee> filtered =  employeeList.stream().filter(employeePredicate).collect(Collectors.toList());

        Optional<Employee> employeeOption = filtered.stream().sorted((e1, e2)->Double.compare(e2.getSalary(),e1.getSalary()))
                .distinct().skip(1).findFirst();

        if(employeeOption.isPresent()){
            System.out.println("second highest salary "+employeeOption.get().getName());
        }

    }
}

@Data
@AllArgsConstructor
class Employee{
    String name;
    Integer salary;



    public Employee() {

    }

    @Override
    public int hashCode(){
         return Objects.hash(name,salary);
    }
}

@Data
@AllArgsConstructor
class EmployeeComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o2.getSalary(),o1.getSalary());
        //return o1.getSalary().compareTo(o2.getSalary());
    }
}
