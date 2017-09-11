/**
 * 
 */
package com.d2l2c.salary.management.web.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.joda.time.DateTime;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.web.util.SalaryWebUtil;

/**
 * @author dayanlazare
 *
 */
@SessionScoped
@ManagedBean(name = "paycheckBean", eager = true)
public class PaycheckBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, PaycheckView> paycheckViewMap = new HashMap<String, PaycheckView>();

	public void addPaycheck(Paycheck paycheck) {
		String key = this.getKey(paycheck);
		PaycheckView currentPaycheckView = null;
		if(paycheckViewMap.containsKey(key)) {
			currentPaycheckView = paycheckViewMap.get(key);
		} else {
			currentPaycheckView = new PaycheckView();
			paycheckViewMap.put(key, currentPaycheckView);
		}
		currentPaycheckView.addPaycheck(paycheck);
	}
	
	public List<PaycheckView> getPaycheckViews() {
		List<PaycheckView> paycheckViews = new ArrayList<PaycheckView>(paycheckViewMap.values());
		SalaryWebUtil.sortPaycheckViews(paycheckViews);
		return paycheckViews;
	}
	
	public Map<String, PaycheckView> getPaycheckViewMap() {
		return paycheckViewMap;
	}
	
	private String getKey(Paycheck paycheck) {
		DateTime dateTime = new DateTime(paycheck.getStartDate());
		return dateTime.getYear() + "" + dateTime.getMonthOfYear();
	}

}
