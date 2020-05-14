package com.managementsystem.controller;

import com.managementsystem.pojo.Client;
import com.managementsystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

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

    @GetMapping("/retrieveClient/{id}")
    public ResponseEntity<?> read(Client client, @PathVariable("id") String id, Model model) {
        client.setId(id);
        LinkedHashMap<String, String> data = clientService.retrieve(client, model);
        String message = model.getAttribute("message").toString();
        if (!message.isEmpty()) {
            return ResponseEntity.ok().body(message);
        }
        List<LinkedHashMap<String, String>> list = new ArrayList<>();
        list.add(data);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    public @ResponseBody List<LinkedHashMap<String, String>> readAll(Client client, Model model) {
        List<LinkedHashMap<String, String>> data = clientService.retrieveAll(client, model);
        return data;
    }

    @PostMapping("/updateClient")
    public ResponseEntity<?> update(@RequestBody Client client, Model model) {
        System.out.println("id" + client.getId());
        String message = clientService.update(client, model);
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<?> deleteClient(Client client, @PathVariable("id") String id, Model model, HttpServletRequest request) {
        client.setId(id);
        System.out.println(id);
        clientService.delete(client, model, request);
        return ResponseEntity.ok().body("client deleted..");
    }
}
