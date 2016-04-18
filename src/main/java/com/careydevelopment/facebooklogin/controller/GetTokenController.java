package com.careydevelopment.facebooklogin.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.careydevelopment.propertiessupport.PropertiesFactory;
import com.careydevelopment.propertiessupport.PropertiesFactoryException;
import com.careydevelopment.propertiessupport.PropertiesFile;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;

@Controller
public class GetTokenController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GetTokenController.class);
	
    @RequestMapping("/getToken")
    public RedirectView getToken(HttpServletRequest request,Model model) {
    	//this will be the URL that we take the user to
    	String facebookUrl = "";
    	
		try {
	        Facebook facebook = new FacebookFactory().getInstance();
	        request.getSession().setAttribute("facebook", facebook);
	        StringBuffer callbackURL = request.getRequestURL();
	        int index = callbackURL.lastIndexOf("/");
	        callbackURL.replace(index, callbackURL.length(), "").append("/callback");
	        
	        facebookUrl = callbackURL.toString();
	        
			LOGGER.info("Authorization url is " + facebookUrl);
		} catch (Exception e) {
			LOGGER.error("Problem getting authorization URL!",e);
		}
    	
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl(facebookUrl);
	    return redirectView;
    }
}
