/**
 * 
 */
package com.d2l2c.salary.management.web.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.d2l2c.common.util.chart.ChartConstants;
import com.d2l2c.salary.management.web.ui.bean.EChartBean;
import com.d2l2c.salary.management.web.ui.bean.PaycheckBean;
import com.d2l2c.salary.management.web.util.SalaryWebUtil;

/**
 * @author dayanlazare
 *
 */
public class PaycheckView implements Serializable {

	private static final long serialVersionUID = 1L;

	TreeMap<Integer, PaycheckBean> yearlyPaycheckMap;

	private TreeMap<Integer, PaycheckBean> monthlyPaycheckMap;
	
	private int year;

	private EChartBean chartBean = new EChartBean(ChartConstants.Serie.BAR_TYPE);

	public PaycheckView(String chartType) {
		super();
		chartBean = new EChartBean(chartType);
	}

	public List<PaycheckBean> getMonthlyPaychecks() {
		List<PaycheckBean> paycheckBeanList = new ArrayList<PaycheckBean>(monthlyPaycheckMap.values());
		SalaryWebUtil.sortPaycheckViews(paycheckBeanList, false);
		return paycheckBeanList;
	}

	public TreeMap<Integer, PaycheckBean> getMonthlyPaycheckMap() {
		return monthlyPaycheckMap;
	}

	public void setMonthlyPaycheckMap(TreeMap<Integer, PaycheckBean> paycheckBeanMap) {
		this.monthlyPaycheckMap = paycheckBeanMap;
	}

	public void setYearlyPaycheckMap(TreeMap<Integer, PaycheckBean> yearlyPaycheckMap) {
		this.yearlyPaycheckMap = yearlyPaycheckMap;
		chartBean.setYearlyPaycheckMap(yearlyPaycheckMap);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	

	public EChartBean getChartBean() {
		return chartBean;
	}

}
