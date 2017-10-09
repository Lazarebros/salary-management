/**
 * 
 */
package com.d2l2c.salary.management.web.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.web.ui.bean.PaycheckBean;
import com.d2l2c.salary.management.web.util.SalaryWebUtil;

/**
 * @author dayanlazare
 *
 */
public class PaycheckView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, PaycheckBean> paycheckBeanMap = new HashMap<String, PaycheckBean>();

	public void addPaycheck(Paycheck paycheck) {
		String key = this.getKey(paycheck);
		PaycheckBean currentPaycheckBean = null;
		if(paycheckBeanMap.containsKey(key)) {
			currentPaycheckBean = paycheckBeanMap.get(key);
		} else {
			currentPaycheckBean = new PaycheckBean();
			paycheckBeanMap.put(key, currentPaycheckBean);
		}
		currentPaycheckBean.addPaycheck(paycheck);
	}
	
	public List<PaycheckBean> getPaycheckBeanList() {
		List<PaycheckBean> paycheckBeanList = new ArrayList<PaycheckBean>(paycheckBeanMap.values());
		SalaryWebUtil.sortPaycheckViews(paycheckBeanList, false);
		return paycheckBeanList;
	}
	
	public Map<String, PaycheckBean> getPaycheckBeanMap() {
		return paycheckBeanMap;
	}
	
	private String getKey(Paycheck paycheck) {
		DateTime dateTime = new DateTime(paycheck.getStartDate());
		return dateTime.getYear() + "" + dateTime.getMonthOfYear();
	}

}
