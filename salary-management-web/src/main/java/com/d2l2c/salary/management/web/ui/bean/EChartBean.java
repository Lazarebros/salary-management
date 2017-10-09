/**
 * 
 */
package com.d2l2c.salary.management.web.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import com.d2l2c.common.util.chart.Axis;
import com.d2l2c.common.util.chart.Chart;
import com.d2l2c.common.util.chart.Legend;
import com.d2l2c.common.util.chart.Serie;
import com.d2l2c.common.util.chart.Title;
import com.d2l2c.common.util.chart.Tooltip;
import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.web.util.SalaryWebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dayanlazare
 */
public class EChartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ObjectMapper mapper = new ObjectMapper();

	private TreeMap<Integer, PaycheckBean> yearlyPaycheckMap = new TreeMap<Integer, PaycheckBean>();

	private Chart grossAmountLineChart;

	private Chart realNetPayLineChart;

	public EChartBean() {
	}

	private void createCharts() {
		Tooltip tooltip = new Tooltip(Tooltip.AXIS);

		List<String> xAxisData = Arrays.asList("Jan", "Fev", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

		Axis xAxis = new Axis(false, xAxisData);
		Axis yAxis = new Axis();

		grossAmountLineChart = new Chart();
		grossAmountLineChart.setTitle(new Title("Gross"));
		grossAmountLineChart.setTooltip(tooltip);

		grossAmountLineChart.setxAxis(xAxis);
		grossAmountLineChart.setyAxis(yAxis);

		realNetPayLineChart = new Chart();
		realNetPayLineChart.setTitle(new Title("Real Net"));
		realNetPayLineChart.setTooltip(tooltip);

		realNetPayLineChart.setxAxis(xAxis);
		realNetPayLineChart.setyAxis(yAxis);

		this.intitData();
	}

	public void setYearlyPaycheckMap(TreeMap<Integer, PaycheckBean> paycheckViewMap) {
		if (!paycheckViewMap.isEmpty()) {
			this.yearlyPaycheckMap = paycheckViewMap;
			this.createCharts();
		}
	}

	public String getGrossAmountChart() {
		return this.getJson(grossAmountLineChart);
	}

	public String getRealNetPayChart() {
		return this.getJson(realNetPayLineChart);
	}

	private void intitData() {
		List<String> lengendList = new ArrayList<String>();
		yearlyPaycheckMap.keySet().forEach(key -> {
			lengendList.add(key.toString());
			this.initSerie(key);
		});
		grossAmountLineChart.setLegend(new Legend(lengendList));
		realNetPayLineChart.setLegend(new Legend(lengendList));
	}

	private void initSerie(Integer key) {
		List<Paycheck> paychecks = yearlyPaycheckMap.get(key).getPaychecks();
		TreeMap<Integer, PaycheckBean> monthlyPaycheckMap = SalaryWebUtil.groupPaychecksByMonth(paychecks);
		List<Object> grossAmountData = new ArrayList<Object>(12);
		List<Object> realNetPayData = new ArrayList<Object>(12);
		for (int index = 1; index <= 12; index++) {
			if (monthlyPaycheckMap.containsKey(index)) {
				grossAmountData.add(monthlyPaycheckMap.get(index).getGrossAmount());
				realNetPayData.add(monthlyPaycheckMap.get(index).getRealNetPay());
			} else {
				grossAmountData.add("");
			}
		}
		Serie grossAmountSerie = new Serie(key.toString(), Serie.LINE_TYPE, grossAmountData);
		grossAmountLineChart.addSerie(grossAmountSerie);

		Serie realNetPaySerie = new Serie(key.toString(), Serie.LINE_TYPE, realNetPayData);
		realNetPayLineChart.addSerie(realNetPaySerie);
	}

	private String getJson(Object obj) {
		String json = "";
		try {
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}