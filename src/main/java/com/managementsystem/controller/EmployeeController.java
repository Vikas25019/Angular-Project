package com.managementsystem.controller;

import com.managementsystem.pojo.Employee;
import com.managementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {
    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        System.out.println(employee.getDateOfBirth());
        String message = employeeService.create(employee);
        System.out.println(message);
        return ResponseEntity.ok().body(message);
    }

    /*@GetMapping("/retrieveEmployee/{id}")
    public @ResponseBody List<LinkedHashMap<String,String>> read(Employee employee,@PathVariable("id") String id, Model model,HttpServletResponse response) {
        employee.setId(id);
        response.setContentType("application/json");
        LinkedHashMap<String, String> data = employeeService.retrieve(employee, model);
        List<LinkedHashMap<String,String>> list  = new ArrayList<>();
        list.add(data);
        return list;
    }*/

    @GetMapping("/retrieveEmployee/{id}")
    public ResponseEntity<?> read(Employee employee, @PathVariable("id") String id, Model model) {
        employee.setId(id);
        LinkedHashMap<String, String> data = employeeService.retrieve(employee, model);
        String message = model.getAttribute("message").toString();
        if (!message.isEmpty()) {
            return ResponseEntity.ok().body(message);
        }
        List<LinkedHashMap<String, String>> list = new ArrayList<>();
        list.add(data);
        return ResponseEntity.ok().body(list);
    }


    @RequestMapping(value = "/retrieveAllEmployee", method = RequestMethod.GET)
    public @ResponseBody List<LinkedHashMap<String, String>> readAll(Employee employee, Model model) {
        List<LinkedHashMap<String, String>> data = employeeService.retrieveAll(employee, model);
        return data;
    }


    @PostMapping("/updateEmployee")
    public ResponseEntity<?> update(@RequestBody Employee employee, Model model) {
        System.out.println("dob" + employee.getDateOfBirth());
        String message = employeeService.update(employee, model);
        return ResponseEntity.ok().body(message);
    }


    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> deleteClient(Employee employee, @PathVariable("id") String id, Model model, HttpServletRequest request) {
        employee.setId(id);
        System.out.println(id);
        employeeService.delete(employee, model, request);
        return ResponseEntity.ok().body("employee deleted..");
    }


}
