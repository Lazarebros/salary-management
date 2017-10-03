package com.d2l2c.salary.management.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/**
 * @author dayanlazare
 *
 */
@Controller
public class BaseController {

	private static final String LOGGED_IN_USER_ATTR_NAME = "loggedinuser";

	protected void addLoggedInUser(ModelMap model) {
		model.addAttribute(LOGGED_IN_USER_ATTR_NAME, getPrincipal());
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	protected String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}
