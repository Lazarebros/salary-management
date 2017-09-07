/**
 * 
 */
package com.d2l2c.salary.management.web.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.d2l2c.salary.management.web.ui.bean.PaycheckView;

/**
 * @author dayanlazare
 *
 */
public class SalaryWebUtil {

	public static void sortPaycheckViews(List<PaycheckView> paycheckViews) {
		Comparator<PaycheckView> comparator = new Comparator<PaycheckView>() {
			@Override
			public int compare(PaycheckView p1, PaycheckView p2) {
				int result = compareInt(p1.getYear(), p2.getYear());
				if(result == 0) {
					result = compareInt(p1.getMonth(), p2.getMonth());
				}
				return result;
			}
		};
		Collections.sort(paycheckViews, comparator);
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

}
