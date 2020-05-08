package com.managementsystem.service;

import com.managementsystem.dao.IDaoInterface;
import com.managementsystem.dao.MysqlDatabaseOperation;
import com.managementsystem.inputvalidation.InputValidation;
import com.managementsystem.pojo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

@Component("clientService")
public class ClientService {
    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Client, MysqlDatabaseOperation> daoInterface;
    MysqlDatabaseOperation<Client> mysqlDatabaseOperation = MysqlDatabaseOperation.getInstance();
	List<String> messageList = new ArrayList();
	
    public String create(Client client) {
        String ID = "clientId";
        String message = "";
        ClientService clientService = new ClientService();

        try {
            LinkedHashMap<String, String> data = client.clientData();
            String id = client.getId();
            
            boolean valid = clientService.inputValidation(client,messageList);
            if (valid) {
                LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
                checkData.put(ID, id);
                boolean checkId = daoInterface.isIdPresent(client, mysqlDatabaseOperation, checkData);
                if (!checkId) {
                    daoInterface.create(client, mysqlDatabaseOperation, data);
                    message = "Record saved successfully!";
                } else {
                    message = "Id already present";
                }
            }
            else{
				message=messageList.get(0);
			}
        } catch (Exception e) {
            System.out.println("Exception"+e);
        }
        System.out.println("message"+message);
        return message;
    }

    public LinkedHashMap<String, String> retrieve(Client client, Model model) {
        
        String column = "clientId";
        String message = "";
        LinkedHashMap<String, String> viewData  = new LinkedHashMap<>();
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        ClientService clientService = new ClientService();
        String id = client.getId();
        checkData.put(column, id);
        try {
            boolean valid = clientService.userIdValidation(client,messageList);
            if (valid) {
                boolean checkId = daoInterface.isIdPresent(client, mysqlDatabaseOperation, checkData);
                if (checkId) {
                    viewData = daoInterface.retrieve(client, mysqlDatabaseOperation, checkData);
                } else {
                    message = "Id is not present";
                }
            }
            else{
				message=messageList.get(0);
			}
        } catch (Exception e) {
            System.out.println(e);
        }
		model.addAttribute("message",message);
        return viewData;
    }

    public List<LinkedHashMap<String, String>> retrieveAll(Client client, Model model) {
         List<LinkedHashMap<String, String>> data = new ArrayList<>();
        try {
             data = daoInterface.retrieveAll(client, mysqlDatabaseOperation);
            model.addAttribute("data", data);
        } catch (Exception e) {
            model.addAttribute("e", e);
        }
        return data;
    }

    public String update(Client client, Model model) {
        String idName = "clientId";
        boolean success = false;
        String message = "";
        ClientService clientService = new ClientService();
        try {
            boolean valid = clientService.inputValidation(client,messageList);
            if (valid) {
                LinkedHashMap<String, String> data = client.clientData();
                String id = data.get(idName);
                LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
                checkData.put(idName, id);

                boolean checkId = daoInterface.isIdPresent(client, mysqlDatabaseOperation, checkData);
                if (checkId) {
                    daoInterface.update(client, mysqlDatabaseOperation, data, idName);
                    message = "Record update successfully!";
                    success = true;

                } else {
                    message = "Id is not present";
                }
            }
        } catch (Exception e) {
            //String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
            model.addAttribute("e", e);
        }
        model.addAttribute("success", success);
        return message;
    }

    public void delete(Client client, Model model, HttpServletRequest request) {
        String idName = "clientId";
        String id = client.getId();
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        checkData.put(idName, id);
        System.out.println(checkData);
        try {
            daoInterface.delete(client, mysqlDatabaseOperation, checkData);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
    }

    private boolean inputValidation(Client client,List<String> list){
        InputValidation inputValidation = new InputValidation();
        try {
            inputValidation.userIdValidator(client.getId());
            inputValidation.userNameValidator(client.getName());
            inputValidation.userAddressValidator(client.getAddress());
            return true;
        } catch (Exception e) {
            String message = e.toString();
            list.clear();
            list.add(0,message);
            return false;
        }
    }

    private boolean userIdValidation(Client client,List<String> list) {
        InputValidation inputValidation = new InputValidation();
        try {
            inputValidation.userIdValidator(client.getId());
            return true;
        } catch (Exception e) {
            System.out.println("Exception"+e);
            String message = e.toString();
            list.clear();
            list.add(0,message);
            return false;
        }
    }
}
