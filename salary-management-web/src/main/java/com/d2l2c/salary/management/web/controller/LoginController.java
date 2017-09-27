package com.d2l2c.salary.management.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.d2l2c.salary.management.web.ui.bean.LoginBean;
import com.d2l2c.salary.management.web.util.SessionUtils;
import com.d2l2c.user.management.bean.User;
import com.d2l2c.user.management.service.UserService;

/**
 * @author dayanlazare
 *
 */
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private LoginBean loginBean;

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String login() {
		String page = null;
		String message = null;
		try {
			User user = userService.validateUser(loginBean.getUsername(), loginBean.getPassword());
			if (user != null) {
				loginBean.setValid(true);
				loginBean.setFirstname(user.getFirstname());
				loginBean.setLastname(user.getLastname());
				page = "home";
			} else {
				message = "Username or password is incorrect!";
			}
		} catch (Exception e) {
			message = "Something went wrong...";
		}
		if(message != null) {
			LOGGER.error(message);
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		}
		return page;
	}

	public String logout() {
		loginBean.setValid(false);
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

}
