package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


//    private EmployeeDAO employeeDAO;
    // Spring Data Jpa 이용 -> 기본적인 CRUD 메소드 그냥 다 제공함.
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(/*@Qualifier("employeeDAOJpaImpl")*/ EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }

    @Override
//    @Transactional -> JpaRepository가 이 기능을 제공함.
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
//    @Transactional
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee theEmployee = null;
        if (result.isPresent()) theEmployee = result.get();
        else throw new RuntimeException("Did not find employee id - " + theId);
        return theEmployee;
    }

    @Override
//    @Transactional
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
//    @Transactional
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
