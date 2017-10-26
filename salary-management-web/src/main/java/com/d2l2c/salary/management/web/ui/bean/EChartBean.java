/**
 * 
 */
package com.d2l2c.salary.management.web.ui.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import com.d2l2c.common.util.chart.Axis;
import com.d2l2c.common.util.chart.Chart;
import com.d2l2c.common.util.chart.ChartConstants;
import com.d2l2c.common.util.chart.Grid;
import com.d2l2c.common.util.chart.Legend;
import com.d2l2c.common.util.chart.Serie;
import com.d2l2c.common.util.chart.TextStyle;
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
	private Chart grossAmountCumulativeLineChart;
	
	private Chart realNetPayLineChart;
	private Chart realNetPayCumulativeLineChart;

	private TextStyle defaultTextStyle = new TextStyle("rgba(0,0,0, 0.8)", 14);

	private String serieType;
	private Integer barMaxWidth;

	public EChartBean() {
		this.serieType = ChartConstants.Serie.LINE_TYPE;
	}

	public EChartBean(String serieType) {
		this.serieType  = serieType;
		if(ChartConstants.Serie.BAR_TYPE.equals(serieType)) {
			barMaxWidth = 12;
		}
	}

	public void setYearlyPaycheckMap(TreeMap<Integer, PaycheckBean> paycheckViewMap) {
		this.yearlyPaycheckMap = paycheckViewMap;
		if (!paycheckViewMap.isEmpty()) {
			this.createCharts();
		} else {
			this.resetCharts();
		}
	}

	private void resetCharts() {
		grossAmountLineChart = null;
		grossAmountCumulativeLineChart = null;
		
		realNetPayLineChart = null;
		realNetPayCumulativeLineChart = null;
	}

	private void createCharts() {
		Tooltip tooltip = new Tooltip(Tooltip.AXIS);

		List<String> xAxisData = Arrays.asList("Jan", "Fev", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

		Axis xAxis = new Axis(false, xAxisData);
		Axis yAxis = new Axis();
		
		Grid grid = new Grid("4%", "4%", "4%");

		grossAmountLineChart = new Chart(grid);
		grossAmountLineChart.setBackgroundColor("rgba(52,60,74, 0.3)");
		grossAmountLineChart.setTitle(new Title("Gross", defaultTextStyle));
		grossAmountLineChart.setTooltip(tooltip);

		grossAmountLineChart.setxAxis(xAxis);
		grossAmountLineChart.setyAxis(yAxis);

		grossAmountCumulativeLineChart = new Chart(grid);
		grossAmountCumulativeLineChart.setBackgroundColor("rgba(52,60,74, 0.3)");
		grossAmountCumulativeLineChart.setTitle(new Title("Cumulative Gross", defaultTextStyle));
		grossAmountCumulativeLineChart.setTooltip(tooltip);

		grossAmountCumulativeLineChart.setxAxis(xAxis);
		grossAmountCumulativeLineChart.setyAxis(yAxis);

		realNetPayLineChart = new Chart(grid);
		realNetPayLineChart.setBackgroundColor("rgba(52,60,74, 0.3)");
		realNetPayLineChart.setTitle(new Title("Real Net", defaultTextStyle));
		realNetPayLineChart.setTooltip(tooltip);

		realNetPayLineChart.setxAxis(xAxis);
		realNetPayLineChart.setyAxis(yAxis);

		realNetPayCumulativeLineChart = new Chart(grid);
		realNetPayCumulativeLineChart.setBackgroundColor("rgba(52,60,74, 0.3)");
		realNetPayCumulativeLineChart.setTitle(new Title("Cumulative Real Net", defaultTextStyle));
		realNetPayCumulativeLineChart.setTooltip(tooltip);

		realNetPayCumulativeLineChart.setxAxis(xAxis);
		realNetPayCumulativeLineChart.setyAxis(yAxis);

		this.intitData();
	}

	public String getGrossAmountChart() {
		return this.getJson(grossAmountLineChart);
	}

	public String getGrossAmountCumulativeChart() {
		return this.getJson(grossAmountCumulativeLineChart);
	}

	public String getRealNetPayChart() {
		return this.getJson(realNetPayLineChart);
	}

	public String getRealNetPayCumulativeChart() {
		return this.getJson(realNetPayCumulativeLineChart);
	}

	private void intitData() {
		List<String> lengendList = new ArrayList<String>();
		yearlyPaycheckMap.keySet().forEach(key -> {
			lengendList.add(key.toString());
			this.initSerie(key);
		});
		grossAmountLineChart.setLegend(new Legend(lengendList, ChartConstants.Position.RIGHT, defaultTextStyle));
		grossAmountCumulativeLineChart.setLegend(new Legend(lengendList, ChartConstants.Position.RIGHT, defaultTextStyle));
		
		realNetPayLineChart.setLegend(new Legend(lengendList, ChartConstants.Position.RIGHT, defaultTextStyle));
		realNetPayCumulativeLineChart.setLegend(new Legend(lengendList, ChartConstants.Position.RIGHT, defaultTextStyle));
	}

	private void initSerie(Integer key) {
		List<Paycheck> paychecks = yearlyPaycheckMap.get(key).getPaychecks();
		TreeMap<Integer, PaycheckBean> monthlyPaycheckMap = SalaryWebUtil.groupPaychecksByMonth(paychecks);
		List<Object> grossAmountData = new ArrayList<Object>(12);
		List<Object> grossAmountCumulativeData = new ArrayList<Object>(12);
		BigDecimal grossAmountCumulative = new BigDecimal("0.00");
		
		List<Object> realNetPayData = new ArrayList<Object>(12);
		List<Object> realNetPayCumulativeData = new ArrayList<Object>(12);
		BigDecimal realNetPayCumulative = new BigDecimal("0.00");
		for (int index = 1; index <= 12; index++) {
			if (monthlyPaycheckMap.containsKey(index)) {
				grossAmountData.add(monthlyPaycheckMap.get(index).getGrossAmount());
				grossAmountCumulative = grossAmountCumulative.add(monthlyPaycheckMap.get(index).getGrossAmount());
				grossAmountCumulativeData.add(grossAmountCumulative);
				
				realNetPayData.add(monthlyPaycheckMap.get(index).getRealNetPay());
				realNetPayCumulative = realNetPayCumulative.add(monthlyPaycheckMap.get(index).getRealNetPay());
				realNetPayCumulativeData.add(realNetPayCumulative);
			} else {
				grossAmountData.add("");
				grossAmountCumulativeData.add("");
				realNetPayData.add("");
				realNetPayCumulativeData.add("");
			}
		}
		Serie grossAmountSerie = new Serie(key.toString(), serieType, barMaxWidth, grossAmountData);
		grossAmountLineChart.addSerie(grossAmountSerie);
		Serie grossAmountCumulativeSerie = new Serie(key.toString(), serieType, barMaxWidth, grossAmountCumulativeData);
		grossAmountCumulativeLineChart.addSerie(grossAmountCumulativeSerie);

		Serie realNetPaySerie = new Serie(key.toString(), serieType, barMaxWidth, realNetPayData);
		realNetPayLineChart.addSerie(realNetPaySerie);
		Serie realNetPayCumulativeSerie = new Serie(key.toString(), serieType, barMaxWidth, realNetPayCumulativeData);
		realNetPayCumulativeLineChart.addSerie(realNetPayCumulativeSerie);

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