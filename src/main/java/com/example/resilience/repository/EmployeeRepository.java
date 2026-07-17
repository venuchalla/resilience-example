package com.example.resilience.repository;

import com.example.resilience.entities.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findByFirstName(String userName);

  //  List<Employee> saveAll(List<Employee> employeeList);

  // Employee save(Employee e);

}
