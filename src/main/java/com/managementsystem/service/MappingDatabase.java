package com.managementsystem.service;

import com.managementsystem.dao.IDaoInterface;
import com.managementsystem.dao.MysqlDatabaseOperation;
import com.managementsystem.pojo.Client;
import com.managementsystem.pojo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

@Component("mapping")
public class MappingDatabase {
    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<MappingDatabase, MysqlDatabaseOperation> daoInterface;

    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Client, MysqlDatabaseOperation> daoInterface2;

    MysqlDatabaseOperation<MappingDatabase> mysqlDatabaseOperation = MysqlDatabaseOperation.getInstance();
	Employee employee = new Employee();
	
    public void createMapping(Employee employee, Model model) {
        final String CLIENT_ID = "clientId";
        final String EMPLOYEE_ID = "employeeId";
        String clientId = employee.getClientId();
        String employeeId = employee.getId();
        MappingDatabase mappingDatabase = new MappingDatabase();
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put(CLIENT_ID, clientId);
        data.put(EMPLOYEE_ID, employeeId);
        try {
            daoInterface.create(mappingDatabase, mysqlDatabaseOperation, data);
        } catch (Exception e) {
            model.addAttribute("excep", e);
        }
    }

    public LinkedHashMap<String, String> viewMapping(Employee employee,Model model) {
        String employeeIdColumn = "employeeId";
        String clientId = "clientId";

        MappingDatabase mappingDatabase = new MappingDatabase();
        Client client = new Client();

        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        LinkedHashMap<String, String> viewData = new LinkedHashMap<>();

        String employeeIdValue = employee.getId();
        data.put(employeeIdColumn, employeeIdValue);

        try {
            String getId = daoInterface.viewMapping(mappingDatabase, mysqlDatabaseOperation, data, clientId);
            if (getId.length() != 0) {
                data.clear();
                data.put(clientId, getId);
                model.addAttribute("data", data);
                viewData = daoInterface2.retrieve(client, mysqlDatabaseOperation, data);
                model.addAttribute("viewData", viewData);
            }
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return viewData;
    }

    public void removeMapping(LinkedHashMap<String, String> checkData, Model model) {
        MappingDatabase mappingDatabase = new MappingDatabase();
        try {
            daoInterface.delete(mappingDatabase, mysqlDatabaseOperation, checkData);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
    }

}
