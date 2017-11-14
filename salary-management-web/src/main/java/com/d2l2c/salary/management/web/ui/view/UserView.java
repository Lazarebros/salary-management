/**
 * 
 */
package com.d2l2c.salary.management.web.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.d2l2c.salary.management.web.util.SalaryWebUtil;
import com.d2l2c.user.management.bean.User;

/**
 * @author dayanlazare
 *
 */
public class UserView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<User> users = new ArrayList<User>();

	public UserView() {
		super();
	}

	public List<User> getUsers() {
		SalaryWebUtil.sortUsers(users, false);
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
