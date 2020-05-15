package com.managementsystem.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan("com.managementsystem")
public class MvcConfig{
	MvcConfig(){
		System.out.println("mvc config started...................");
	}
}
