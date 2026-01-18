package com.example.resilience.java;

import java.util.HashMap;
import java.util.Objects;

public class HashmapTest {


    public static  void main(String[] args){

        Employee employee = new Employee("1","venu");
        Employee employee1 = new Employee("1","venu");

        HashMap<Employee,String> result = new HashMap<>();
        result.put(employee,"first");
        result.put(employee1,"second");
        System.out.println("size :" +result.size());
    }

    private static class Employee{
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        private final String id;
        private final String name;

        Employee(String id , String name){
            this.id =id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return Objects.equals(id, employee.id) && Objects.equals(name, employee.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
