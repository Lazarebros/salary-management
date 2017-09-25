/**
 * 
 */
package com.d2l2c.salary.management.web.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.d2l2c.common.util.chart.Axis;
import com.d2l2c.common.util.chart.Chart;
import com.d2l2c.common.util.chart.Legend;
import com.d2l2c.common.util.chart.Serie;
import com.d2l2c.common.util.chart.Title;
import com.d2l2c.common.util.chart.Tooltip;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dayanlazare
 */
@SessionScoped
@ManagedBean
public class EChartBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private Map<String, PaycheckBean> paycheckViewMap = new HashMap<String, PaycheckBean>();
	
	private Chart lineChart;
	
	public EChartBean() {
		this.createCharts();
	}
	
	private void createCharts() {
		
		Title title = new Title("Real Net Pay");
		Tooltip tooltip = new Tooltip(Tooltip.AXIS);
		
		
//		Legend legend = this.getLegend();
		
		List<String> legendData = Arrays.asList("2015", "2016", "2017");
		Legend legend = new Legend(legendData);
		
		List<String> xAxisData = Arrays.asList("Jan", "Fev", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		Axis xAxis = new Axis(false, xAxisData);
		
		Axis yAxis = new Axis();
		
		List<String> data1 = Arrays.asList("1233", "2345", "3452", "1243", "1465", "5437", "1233", "2345", "3452", "1243", "1465", "5437");
		List<String> data2 = Arrays.asList("4675", "7654", "4353", "3097", "1465", "5437", "1233", "2345", "3452", "1243", "1465", "5437");
		List<String> data3 = Arrays.asList("5467", "3456", "1465", "3245", "6547", "2345", "1324", "7657", "2314", "6574", "3456", "2345");

		Serie serie1 = new Serie("2015", Serie.LINE_TYPE, data1);
		Serie serie2 = new Serie("2016", Serie.LINE_TYPE, data2);
		Serie serie3 = new Serie("2017", Serie.LINE_TYPE, data3);
		
		lineChart = new Chart(title, legend, xAxis, yAxis);
		lineChart.setTooltip(tooltip);
		lineChart.addSerie(serie1);
		lineChart.addSerie(serie2);
		lineChart.addSerie(serie3);
		
	}

	public void setPaycheckViewMap(Map<String, PaycheckBean> paycheckViewMap) {
		if(!paycheckViewMap.isEmpty()) {
			this.paycheckViewMap = paycheckViewMap;
			this.createCharts();
		}
	}
	

	public String getChart() {
		this.createCharts();
		return this.getJson(lineChart);
	}
	
	private Legend getLegend() {
		List<String> legendData = new ArrayList<String>(paycheckViewMap.keySet());
		Legend legend = new Legend(legendData);
		return legend;
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