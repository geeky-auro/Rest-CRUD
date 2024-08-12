package com.luvs.cruddemo.rest;

import com.luvs.cruddemo.dao.EmployeeDAO;
import com.luvs.cruddemo.entity.Employee;
import com.luvs.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // quick and dirty : inject employee DAo (Use COnstructor Injection)

    private EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }

    // expose "/employee" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee=employeeService.findById(employeeId);

        if(theEmployee==null){
            throw new RuntimeException("Employee ID not found - "+employeeId);
        }

        return theEmployee;
    }
    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        // also just in case they pass an ID in JSOn .. set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);

        Employee dbEmployee=employeeService.save(theEmployee);

        return dbEmployee;
    }


    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEMployee){
        return employeeService.save(theEMployee);
    }

    // add mapping for DELETE /employees/{employeeID} -delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee=employeeService.findById(employeeId);

        // throw exception if null

        if(tempEmployee==null){
            throw new RuntimeException("Employee Id Not found - "+ employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted EMployee ID - " +employeeId;
    }

}
