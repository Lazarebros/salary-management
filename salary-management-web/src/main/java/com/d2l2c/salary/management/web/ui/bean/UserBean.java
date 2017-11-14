/**
 * 
 */
package com.d2l2c.salary.management.web.ui.bean;

import java.util.ArrayList;
import java.util.List;

import com.d2l2c.user.management.bean.User;

/**
 * @author dayanlazare
 *
 */
public class UserBean {

	private List<User> users = new ArrayList<User>();

	public UserBean() {
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> paychecks) {
		this.users = paychecks;
	}

}
