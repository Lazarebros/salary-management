package com.d2l2c.salary.management.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.d2l2c.salary.management.data.bean.user.User;
import com.d2l2c.salary.management.data.service.UserService;
import com.d2l2c.salary.management.web.ui.view.UserView;

/**
 * @author dayanlazare
 *
 */
@Controller
public class UserController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static final String USERS_VIEW_ATTR_NAME = "usersView";

	@Autowired
	UserService userService;

	private UserView userView = new UserView();


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = {"/users"}, method = RequestMethod.GET)
	public String usersPage(ModelMap model) {
		super.addLoggedInUser(model);
		try {
			List<User> users = userService.findAll();
			userView.setUsers(users);
			model.addAttribute(USERS_VIEW_ATTR_NAME, userView);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return "users";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		super.addLoggedInUser(model);
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}


	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String username, ModelMap model) {
		super.addLoggedInUser(model);
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}

}
