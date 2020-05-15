package com.managementsystem.service;

import com.managementsystem.dao.IDaoInterface;
import com.managementsystem.pojo.Client;
import com.managementsystem.pojo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.LinkedHashMap;

@Service("mapping")
public class MappingDatabase {
    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<MappingDatabase> daoInterface;

    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Client> daoInterface2;

    public void createMapping(Employee employee) {
        final String CLIENT_ID = "clientId";
        final String EMPLOYEE_ID = "employeeId";
        String clientId = employee.getClientId();
        String employeeId = employee.getId();
        MappingDatabase mappingDatabase = new MappingDatabase();
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put(CLIENT_ID, clientId);
        data.put(EMPLOYEE_ID, employeeId);
        try {
            daoInterface.create(mappingDatabase, data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public LinkedHashMap<String, String> viewMapping(Employee employee) {
        String employeeIdColumn = "employeeId";
        String clientId = "clientId";

        MappingDatabase mappingDatabase = new MappingDatabase();
        Client client = new Client();

        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        LinkedHashMap<String, String> viewData = new LinkedHashMap<>();

        String employeeIdValue = employee.getId();
        data.put(employeeIdColumn, employeeIdValue);

        try {
            String getId = daoInterface.viewMapping(mappingDatabase, data, clientId);
            if (getId.length() != 0) {
                data.clear();
                data.put(clientId, getId);
                viewData = daoInterface2.retrieve(client, data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return viewData;
    }

    public void removeMapping(LinkedHashMap<String, String> checkData, Model model) {
        MappingDatabase mappingDatabase = new MappingDatabase();
        try {
            daoInterface.delete(mappingDatabase, checkData);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
    }

}
