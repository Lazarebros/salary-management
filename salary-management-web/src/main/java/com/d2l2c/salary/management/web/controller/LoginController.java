package com.d2l2c.salary.management.web.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.d2l2c.salary.management.web.ui.bean.LoginBean;
import com.d2l2c.salary.management.web.util.SessionUtils;
import com.d2l2c.user.management.bean.User;
import com.d2l2c.user.management.service.UserService;

/**
 * @author dayanlazare
 *
 */
@ManagedBean
@RequestScoped
public class LoginController {

	@ManagedProperty(value = "#{userService}")
	UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@ManagedProperty(value = "#{loginBean}")
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
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
