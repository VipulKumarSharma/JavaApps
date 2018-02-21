package com.mind.suneasy.controller;

import java.util.Locale;

import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mind.suneasy.constants.ModelAndViewURLConstant;
import com.mind.suneasy.dto.LoginBeanDTO;

public interface EasySunController {

	@RequestMapping(value=ModelAndViewURLConstant.ROOT_URL, method=RequestMethod.GET)
	public ModelAndView getLoginScreen(ModelMap model, Locale locale);
	
	@RequestMapping(value=ModelAndViewURLConstant.HOME_URL, method=RequestMethod.POST)
	public ModelAndView getHomeScreen(ModelMap model, @Validated LoginBeanDTO user);

	@RequestMapping(value=ModelAndViewURLConstant.HOME_URL, method=RequestMethod.GET)
	public ModelAndView showHome(ModelMap model);
	
	@RequestMapping(value=ModelAndViewURLConstant.UI_BUTTONS_URL, method=RequestMethod.GET)
	public ModelAndView showComponentUiButtons(ModelMap model);
	
	@RequestMapping(value=ModelAndViewURLConstant.WIDGETS_URL, method=RequestMethod.GET)
	public ModelAndView showWidgets(ModelMap model);
}
