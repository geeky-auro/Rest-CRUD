package com.luvs.cruddemo.dao;

import com.luvs.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImple implements EmployeeDAO{
    // define fields for entityManager
    private EntityManager entityManager;

    // setup constructor injection
    @Autowired
    public EmployeeDAOImple(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }
    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> theQUery=entityManager.createQuery("from Employee", Employee.class);
        // execute and get result list
        List<Employee> employees=theQUery.getResultList();
        // return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // get employee
        Employee theEmployee=entityManager.find(Employee.class,theId);
        // return employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee dbEMployee = entityManager.merge(employee);
        // if ID==0 -> insert/save else update

        return dbEMployee;
    }

    @Override
    public void deleteById(int id) {
        // find EMployeeBy Id
        Employee theEmployee=entityManager.find(Employee.class,id);
        entityManager.remove(theEmployee);
    }
}
