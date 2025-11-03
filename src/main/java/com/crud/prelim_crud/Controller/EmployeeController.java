package com.crud.prelim_crud.Controller;

import com.crud.prelim_crud.DTO.EmployeeDTO;
import com.crud.prelim_crud.Model.Employee;
import com.crud.prelim_crud.Repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping("/")
public class EmployeeController {


    private final EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee"; // employee.html
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "add"; // new.html
    }


    @PostMapping("/save")
    public String saveEmployee(
            @ModelAttribute("employee") @Valid EmployeeDTO employeeDTO,
            BindingResult result,
            Model model) {


        if (result.hasErrors()) {
            model.addAttribute("employee", employeeDTO);
            return "add";
        }


        if(employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()){
            result.rejectValue("email", "error.employee", "Email already exists.");
            model.addAttribute("employee", employeeDTO);
            return "add";
        }


        Employee newEmployee = new Employee();
        newEmployee.setName(employeeDTO.getName());
        newEmployee.setEmail(employeeDTO.getEmail());


        employeeRepository.save(newEmployee);
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);


        if (employee == null) {
            model.addAttribute("message", "Employee not found!");
            return "error/error";
        }


        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());


        model.addAttribute("employee", employeeDTO);
        model.addAttribute("id", id);
        return "edit";
    }


    @PostMapping("/update/{id}")
    public String updateEmployee(
            @PathVariable int id,
            @Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
            BindingResult result,
            Model model) {


        employeeRepository.findByEmail(employeeDTO.getEmail()).ifPresent(existing -> {
            if (existing.getId() != id) {
                result.rejectValue("email", "error.employee", "Email already exists.");
            }
        });


        if (result.hasErrors()) {
            model.addAttribute("employee", employeeDTO);
            model.addAttribute("id", id);
            return "edit";
        }


        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));


        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setEmail(employeeDTO.getEmail());


        employeeRepository.save(existingEmployee);
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id, Model model) {
        if (!employeeRepository.existsById(id)) {
            model.addAttribute("message", "Employee not found!");
            return "error/error";
        }
        employeeRepository.deleteById(id);
        return "redirect:/";
    }
}

