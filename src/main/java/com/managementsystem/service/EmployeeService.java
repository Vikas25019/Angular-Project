package com.managementsystem.service;

import com.managementsystem.dao.IDaoInterface;
import com.managementsystem.dao.MysqlDatabaseOperation;
import com.managementsystem.inputvalidation.InputValidation;
import com.managementsystem.pojo.Client;
import com.managementsystem.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

@Component("employeeService")
public class EmployeeService {
    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Employee, MysqlDatabaseOperation> daoInterface;

    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Client, MysqlDatabaseOperation> daoInterfaceClient;

    @Autowired
    @Qualifier("mysqlDatabaseOperation")
    MysqlDatabaseOperation<Employee> mysqlDatabaseOperation;

    List<String> messageList = new ArrayList();

    @Autowired
    @Qualifier("mapping")
    MappingDatabase mappingDatabase;

    public String create(Employee employee) {
        String message = "";
        final String CLIENT_ID = "clientId";
        final String EMPLOYEE_ID = "employeeId";
        EmployeeService employeeService = new EmployeeService();
        Client client = new Client();
        boolean valid = employeeService.inputValidation(employee, messageList);
        if (valid) {
            LinkedHashMap<String, String> data = employee.employeeData();
            LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
            checkData.put(EMPLOYEE_ID, data.get(EMPLOYEE_ID));
            String clientId = data.get(CLIENT_ID);
            try {
                boolean checkId = daoInterface.isIdPresent(employee, mysqlDatabaseOperation, checkData);
                if (!checkId) {
                    checkData.clear();
                    checkData.put(CLIENT_ID, clientId);
                    boolean checkClientId = daoInterfaceClient.isIdPresent(client, mysqlDatabaseOperation, checkData);
                    if (checkClientId) {
                        daoInterface.create(employee, mysqlDatabaseOperation, data);
                        mappingDatabase.createMapping(employee);
                        message = "Record saved successfully!";
                    } else {
                        message = "Client Id is not present";
                    }
                } else {
                    message = "Id already present";
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            message = messageList.get(0);
        }

        return message;
    }

    public LinkedHashMap<String, String> retrieve(Employee employee, Model model) {

        String column = "employeeId";
        String message = "";
        LinkedHashMap<String, String> viewData = new LinkedHashMap<>();
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        EmployeeService employeeService = new EmployeeService();
        String id = employee.getId();
        checkData.put(column, id);
        try {
            boolean valid = employeeService.userIdValidation(employee, messageList);
            if (valid) {
                boolean checkId = daoInterface.isIdPresent(employee, mysqlDatabaseOperation, checkData);
                if (checkId) {
                    viewData = daoInterface.retrieve(employee, mysqlDatabaseOperation, checkData);
                    model.addAttribute("view", viewData);

                } else {
                    message = "Id is not present";
                }
            } else {
                message = messageList.get(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        model.addAttribute("message", message);
        return viewData;
    }

    public List<LinkedHashMap<String, String>> retrieveAll(Employee employee, Model model) {
        List<LinkedHashMap<String, String>> data = new ArrayList<>();
        try {
            data = daoInterface.retrieveAll(employee, mysqlDatabaseOperation);
            model.addAttribute("data", data);
        } catch (Exception e) {
            model.addAttribute("e", e);
        }
        return data;
    }

    public String update(Employee employee, Model model) {

        String idName = "employeeId";

        String message = "";
        EmployeeService employeeService = new EmployeeService();
        try {
            boolean valid = employeeService.inputValidation(employee, messageList);
            if (valid) {
                LinkedHashMap<String, String> data = employee.employeeData();
                String id = data.get(idName);
                LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
                checkData.put(idName, id);
                System.out.println(data);
                boolean checkId = daoInterface.isIdPresent(employee, mysqlDatabaseOperation, checkData);
                if (checkId) {
                    daoInterface.update(employee, mysqlDatabaseOperation, data, idName);
                    message = "Record update successfully!";
                } else {
                    message = "Id is not present";
                }
            } else {
                message = messageList.get(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return message;

    }

    public void delete(Employee employee, Model model, HttpServletRequest request) {
        String idName = "employeeId";
        String id = employee.getId();
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        checkData.put(idName, id);
        try {
            daoInterface.delete(employee, mysqlDatabaseOperation, checkData);
            mappingDatabase.removeMapping(checkData, model);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
    }

    private boolean inputValidation(Employee employee, List<String> list) {
        InputValidation inputValidation = new InputValidation();

        try {
            inputValidation.userIdValidator(employee.getId());
            inputValidation.userIdValidator(employee.getClientId());
            inputValidation.userNameValidator(employee.getName());
            inputValidation.userDepartmentValidator(employee.getDepartment());
            inputValidation.userEmailValidator(employee.getEmail());
            inputValidation.userDateOfBirthValidator(employee.getDateOfBirth());
            return true;
        } catch (Exception e) {
            System.out.println("exp" + e);
            String message = e.toString();
            list.clear();
            list.add(0, message);
            return false;
        }
    }

    private boolean userIdValidation(Employee employee, List<String> list) {
        InputValidation inputValidation = new InputValidation();
        try {
            inputValidation.userIdValidator(employee.getId());
            return true;
        } catch (Exception e) {
            System.out.println("exp" + e);
            String message = e.toString();
            list.clear();
            list.add(0, message);
            return false;
        }
    }
}
