/**
 * 
 */
package com.d2l2c.salary.management.data.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.d2l2c.salary.management.data.bean.Paycheck;

/**
 * @author dayanlazare
 *
 */
public class PaycheckUtil {

	public static Map<Integer, Paycheck> groupPaychecksByYear(List<Paycheck> paychecks) {
		Map<Integer, Paycheck> paycheckMap = new HashMap<Integer, Paycheck>();
		paychecks.forEach(paycheck -> {
	        if (paycheckMap.containsKey(paycheck.getYear())) {
	        	Paycheck matchedItem = paycheckMap.get(paycheck.getYear());
	            matchedItem.setGrossAmount(matchedItem.getGrossAmount().add(paycheck.getGrossAmount()));
	            matchedItem.setNetPay(matchedItem.getNetPay().add(paycheck.getNetPay()));
	            matchedItem.setReimbursement(matchedItem.getReimbursement().add(paycheck.getReimbursement()));
	        } else {
	        	paycheck.setMonth(0);
	        	paycheck.setBiWeek(0);
	            paycheckMap.put(paycheck.getYear(), paycheck);
	        }
	    });
		return paycheckMap;
	}

}
