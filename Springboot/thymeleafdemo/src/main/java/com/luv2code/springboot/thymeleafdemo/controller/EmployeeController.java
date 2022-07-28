package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired // optional
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel){

        // get the employees from the database
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        // create model attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) { //링크로 받은 id를 받는 역할을 함.
        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);
        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "employees/employee-form";
    }
    @GetMapping("/delete")
        public String deleteEmployee(@RequestParam("employeeId") int theId){
            // delete the employee
            employeeService.deleteById(theId);
            // redirect to /employees/list
            return "redirect:/employees/list";
        }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        // post / redirect / get pattern
        return "redirect:/employees/list";
    }
}
