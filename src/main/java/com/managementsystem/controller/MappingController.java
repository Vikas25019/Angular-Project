package com.managementsystem.controller;

import com.managementsystem.pojo.Employee;
import com.managementsystem.service.MappingDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MappingController {
    @Autowired
    @Qualifier("mapping")
    private MappingDatabase mappingDatabase;
    
    @GetMapping("/viewMapping/{id}")
    public @ResponseBody
    List<LinkedHashMap<String,String>> read(Employee employee, @PathVariable("id") String id, Model model, HttpServletResponse response) {
		employee.setId(id);
        response.setContentType("application/json");
        LinkedHashMap<String, String> data = mappingDatabase.viewMapping(employee,model);
        List<LinkedHashMap<String,String>> list  = new ArrayList<>();
        list.add(data);
        return list;
    }
    
}
