/**
 * 
 */
package com.d2l2c.salary.management.web.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.d2l2c.salary.management.web.ui.bean.PaycheckBean;
import com.d2l2c.salary.management.web.util.SalaryWebUtil;

/**
 * @author dayanlazare
 *
 */
public class PaycheckView implements Serializable {

	private static final long serialVersionUID = 1L;

	private TreeMap<Integer, PaycheckBean> paycheckBeanMap = new TreeMap<Integer, PaycheckBean>();

	private int year;

	public List<PaycheckBean> getPaychecks() {
		List<PaycheckBean> paycheckBeanList = new ArrayList<PaycheckBean>(paycheckBeanMap.values());
		SalaryWebUtil.sortPaycheckViews(paycheckBeanList, false);
		return paycheckBeanList;
	}

	public TreeMap<Integer, PaycheckBean> getPaycheckBeanMap() {
		return paycheckBeanMap;
	}

	public void setPaycheckBeanMap(TreeMap<Integer, PaycheckBean> paycheckBeanMap) {
		this.paycheckBeanMap = paycheckBeanMap;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
