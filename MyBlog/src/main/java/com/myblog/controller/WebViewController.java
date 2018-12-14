package com.myblog.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myblog.service.UserService;
 
@Controller
public class WebViewController 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/appInterface.do")
    public String logout(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    
    	return "common/appInterface";
    }
    
}
