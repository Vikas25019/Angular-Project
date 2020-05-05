package com.managementsystem.controller;

import com.managementsystem.pojo.Client;
import com.managementsystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClientController {
    @Autowired
    @Qualifier("clientService")
    private ClientService clientService;
  
    @PostMapping("/saveClient")
    public ResponseEntity<?> save(@RequestBody Client client) {
        String message = clientService.create(client);
        return ResponseEntity.ok().body(message);
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewClient(@ModelAttribute("client") Client client) {
        return "client/showclient";
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
    public String read(@ModelAttribute("client") Client client, Model model) {
        String path = "client/showclient";
        String message = clientService.retrieve(client, model);
        model.addAttribute("message", message);
        boolean success = (boolean) model.getAttribute("success");
        if (success) {
            path = "client/viewclient";
        }
        return path;
    }

    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    public @ResponseBody List<LinkedHashMap<String, String>> readAll(Client client, Model model ,HttpServletResponse response) {
		response.setContentType("application/json");  
        List<LinkedHashMap<String, String>> data = clientService.retrieveAll(client, model);
        return data;
    }

    @RequestMapping(value = "/updateClientPage", method = RequestMethod.GET)
    public String updatePage(@ModelAttribute("client") Client client) {
        return "client/update";
    }

    
    @GetMapping(value = "/updateClient")
    public String update(@ModelAttribute("client") Client client, Model model) {
        String path = "client/update";
        String message = clientService.update(client, model);
        model.addAttribute("message", message);
        boolean success = (boolean) model.getAttribute("success");
        if (success) {
            path = "redirect:retrieveAll";
        }
        return path;
    }

    @RequestMapping(value = "/deleteClient", method = RequestMethod.GET)
    public String delete(@ModelAttribute("client") Client client, Model model, HttpServletRequest request) {
        clientService.delete(client, model, request);
        return "redirect:retrieveAll";
    }

}
