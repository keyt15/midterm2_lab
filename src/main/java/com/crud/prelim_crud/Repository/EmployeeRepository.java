package com.crud.prelim_crud.Repository;


import com.crud.prelim_crud.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Optional<Employee> findByEmail(String email);
}
