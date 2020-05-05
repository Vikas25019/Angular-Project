package com.managementsystem.controller;

import com.managementsystem.service.MappingDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MappingController {
    @Autowired
    @Qualifier("mapping")
    private MappingDatabase mappingDatabase;

    @RequestMapping(value = "/viewMapping", method = RequestMethod.GET)
    public String createMapping(Model model, HttpServletRequest request) {
        mappingDatabase.viewMapping(model, request);
        return "mapping/showmapping";
    }
}
