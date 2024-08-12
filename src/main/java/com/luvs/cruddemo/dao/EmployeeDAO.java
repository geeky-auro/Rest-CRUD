package com.luvs.cruddemo.dao;

import com.luvs.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {


    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee employee);

    void deleteById(int id);

}
