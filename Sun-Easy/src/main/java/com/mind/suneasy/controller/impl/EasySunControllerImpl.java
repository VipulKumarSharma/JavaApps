package com.mind.suneasy.controller.impl;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

import com.mind.suneasy.constants.ViewConstant;
import com.mind.suneasy.controller.EasySunController;
import com.mind.suneasy.dto.LoginBeanDTO;

@Controller
public class EasySunControllerImpl implements EasySunController {

	@Override
	public ModelAndView getLoginScreen(ModelMap model, Locale locale) {
		System.out.println("Home Page Requested with locale=" + locale);
		
		Date date 				= new Date();
		DateFormat dateFormat 	= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate 	= dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return new ModelAndView(ViewConstant.LOGIN_VIEW);
	}

	@Override
	public ModelAndView getHomeScreen(ModelMap model, @Validated LoginBeanDTO user) {
		System.out.println("Login Screen Opened");
		
		model.addAttribute("userName", 	user.getUserName());
		model.addAttribute("password", 	user.getPassword());
		
		return new ModelAndView(ViewConstant.DASHBOARD_HOME_VIEW);
	}
	
	@Override
	public ModelAndView showHome(ModelMap model) {
		System.out.println("Opening Home");
		
		return new ModelAndView(ViewConstant.DASHBOARD_HOME_VIEW);
	}

	@Override
	public ModelAndView showComponentUiButtons(ModelMap model) {
		System.out.println("Opening Component UI Buttons");
		
		return new ModelAndView(ViewConstant.UI_BUTTONS_VIEW);
	}
	
	@Override
	public ModelAndView showWidgets(ModelMap model) {
		System.out.println("Opening Widgets");
		
		return new ModelAndView(ViewConstant.WIDGETS_VIEW);
	}
	
	
}
