/**
 * 
 */
package com.d2l2c.salary.management.web.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.web.ui.bean.PaycheckBean;
import com.d2l2c.user.management.bean.User;

/**
 * @author dayanlazare
 *
 */
public class SalaryWebUtil {

	public static void sortPaycheckViews(List<PaycheckBean> paycheckViews, boolean reverse) {
		Comparator<PaycheckBean> comparator = new Comparator<PaycheckBean>() {
			@Override
			public int compare(PaycheckBean p1, PaycheckBean p2) {
				int result = compareInt(p1.getYear(), p2.getYear());
				if(result == 0) {
					result = compareInt(p1.getMonthOfPay(), p2.getMonthOfPay());
				}
				return result;
			}
		};
		if (reverse) {
			Collections.sort(paycheckViews, comparator.reversed());
		} else {
			Collections.sort(paycheckViews, comparator);
		}
	}

	public static void sortUsers(List<User> users, boolean reverse) {
		Comparator<User> comparator = new Comparator<User>() {
			@Override
			public int compare(User p1, User p2) {
				return compareLong(p1.getId(), p2.getId());
			}
		};
		if (reverse) {
			Collections.sort(users, comparator.reversed());
		} else {
			Collections.sort(users, comparator);
		}
	}

	public static int compareInt(int value1, int value2) {
		int result = 0;
		if(value1 > value2) {
			result = 1;
		} else if(value1 < value2) {
			result = -1;
		} else {
			result = 0;
		}
		return result;
	}

	public static int compareLong(Long value1, Long value2) {
		int result = 0;
		if(value1 > value2) {
			result = 1;
		} else if(value1 < value2) {
			result = -1;
		} else {
			result = 0;
		}
		return result;
	}

	public static TreeMap<Integer, PaycheckBean> groupPaychecksByYear(List<Paycheck> paychecks) {
		TreeMap<Integer, PaycheckBean> paycheckMap = new TreeMap<Integer, PaycheckBean>(Collections.reverseOrder());
		paychecks.forEach(paycheck -> {
	        if (paycheckMap.containsKey(paycheck.getYear())) {
	        	PaycheckBean matchedPaycheckBean = paycheckMap.get(paycheck.getYear());
	        	matchedPaycheckBean.addPaycheck(paycheck);
	        } else {
	        	PaycheckBean paycheckBean = new PaycheckBean();
	        	paycheckBean.addPaycheck(paycheck);
	            paycheckMap.put(paycheck.getYear(), paycheckBean);
	        }
	    });
		return paycheckMap;
	}

	public static TreeMap<Integer, PaycheckBean> groupPaychecksByMonth(List<Paycheck> paychecks) {
		TreeMap<Integer, PaycheckBean> paycheckMap = new TreeMap<Integer, PaycheckBean>();
		paychecks.forEach(paycheck -> {
	        if (paycheckMap.containsKey(paycheck.getMonth())) {
	        	PaycheckBean matchedPaycheckBean = paycheckMap.get(paycheck.getMonth());
	        	matchedPaycheckBean.addPaycheck(paycheck);
	        } else {
	        	PaycheckBean paycheckBean = new PaycheckBean();
	        	paycheckBean.addPaycheck(paycheck);
	            paycheckMap.put(paycheck.getMonth(), paycheckBean);
	        }
	    });
		return paycheckMap;
	}

}
