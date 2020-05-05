package com.managementsystem.controller;

import com.managementsystem.dao.IDaoInterface;
import com.managementsystem.dao.MysqlDatabaseOperation;
import com.managementsystem.pojo.Client;
import com.managementsystem.pojo.Employee;
import com.managementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class EmployeeController {
    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Client, MysqlDatabaseOperation> daoInterface;
    MysqlDatabaseOperation<Client> mysqlDatabaseOperation = MysqlDatabaseOperation.getInstance();

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @RequestMapping(value = "/createEmployee", method = RequestMethod.GET)
    public String create(@ModelAttribute("employee") Employee employee) {
        return "employee/createemployee";
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.GET)
    public String save(@ModelAttribute("employee") Employee employee, Model model) {
        String message = employeeService.create(employee, model);
        model.addAttribute("message", message);
        return "employee/createemployee";
    }

    @RequestMapping(value = "/viewEmployee", method = RequestMethod.GET)
    public String viewClient(@ModelAttribute("employee") Employee employee) {
        return "employee/showemployee";
    }

    @RequestMapping(value = "/retrieveEmployee", method = RequestMethod.GET)
    public String read(@Valid @ModelAttribute("employee") Employee employee, Model model) {
        String path = "employee/showemployee";
        String message = employeeService.retrieve(employee, model);
        model.addAttribute("message", message);
        boolean success = (boolean) model.getAttribute("success");
        if (success) {
            path = "employee/viewemployee";
        }
        return path;
    }

    @RequestMapping(value = "/retrieveAllEmployee", method = RequestMethod.GET)
    public String readAll(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        employeeService.retrieveAll(employee, model);
        return "employee/showAllEmployee";
    }

    @RequestMapping(value = "/updateEmployeePage", method = RequestMethod.GET)
    public String updatePage(@ModelAttribute("employee") Employee employee) {
        return "employee/updateemployee";
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.GET)
    public String update(@Valid @ModelAttribute("employee") Employee employee, Model model) {
        String path = "employee/updateemployee";
        String message = employeeService.update(employee, model);
        model.addAttribute("message", message);
        boolean success = (boolean) model.getAttribute("success");
        if (success) {
            path = "redirect:retrieveAllEmployee";
        }
        return path;
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
    public String delete(@ModelAttribute("employee") Employee employee, Model model, HttpServletRequest request) {
        employeeService.delete(employee, model, request);
        return "redirect:retrieveAllEmployee";
    }
}
