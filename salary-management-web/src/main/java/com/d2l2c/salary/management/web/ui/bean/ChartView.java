/**
 * 
 */
package com.d2l2c.salary.management.web.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.d2l2c.salary.management.web.util.SalaryWebUtil;

/**
 * @author dayanlazare
 */
@SessionScoped
@ManagedBean
public class ChartView implements Serializable {

	private static final long serialVersionUID = 1L;

	private LineChartModel animatedModel1;
	private BarChartModel animatedModel2;
	
	private Map<String, PaycheckView> paycheckViewMap = new HashMap<String, PaycheckView>();

	public LineChartModel getAnimatedModel1() {
		return animatedModel1;
	}

	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}

	private void createAnimatedModels() {
		int max = this.getMaxRealNet();
		
		animatedModel1 = initLinearModel();
		animatedModel1.setTitle("Real Net Pay");
		animatedModel1.setAnimate(true);
		animatedModel1.setLegendPosition("se");
		Axis yAxis = animatedModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(max);

		animatedModel2 = initBarModel();
		animatedModel2.setTitle("Real Net Pay");
		animatedModel2.setAnimate(true);
		animatedModel2.setLegendPosition("ne");
		yAxis = animatedModel2.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(max);
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();
		Map<Integer, LineChartSeries> chartSeriesMap = new HashMap<Integer, LineChartSeries>();
		
		List<PaycheckView> paycheckViews = new ArrayList<PaycheckView>(paycheckViewMap.values());
		SalaryWebUtil.sortPaycheckViews(paycheckViews);
		
		for(PaycheckView paycheckView : paycheckViews) {
			int year = paycheckView.getYear();
			LineChartSeries lineChartSeries = null;
			if(chartSeriesMap.containsKey(year)) {
				lineChartSeries = chartSeriesMap.get(year);
			} else {
				lineChartSeries = new LineChartSeries();
				lineChartSeries.setLabel(year + "");
				chartSeriesMap.put(year, lineChartSeries);
			}
			lineChartSeries.set(paycheckView.getMonth(), paycheckView.getRealNetPay());
		}
		for(LineChartSeries lineChartSeries : chartSeriesMap.values()) {
			model.addSeries(lineChartSeries);
		}
		return model;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		Map<Integer, ChartSeries> chartSeriesMap = new HashMap<Integer, ChartSeries>();
		
		List<PaycheckView> paycheckViews = new ArrayList<PaycheckView>(paycheckViewMap.values());
		SalaryWebUtil.sortPaycheckViews(paycheckViews);
		
		for(PaycheckView paycheckView : paycheckViews) {
			int year = paycheckView.getYear();
			ChartSeries chartSeries = null;
			if(chartSeriesMap.containsKey(year)) {
				chartSeries = chartSeriesMap.get(year);
			} else {
				chartSeries = new ChartSeries();
				chartSeries.setLabel(year + "");
				chartSeriesMap.put(year, chartSeries);
			}
			chartSeries.set(paycheckView.getMonth(), paycheckView.getRealNetPay());
		}
		for(ChartSeries chartSeries: chartSeriesMap.values()) {
			model.addSeries(chartSeries);
		}
		return model;
	}

	public void setPaycheckViewMap(Map<String, PaycheckView> paycheckViewMap) {
		this.paycheckViewMap = paycheckViewMap;
		if(!paycheckViewMap.isEmpty()) {
			this.createAnimatedModels();
		}
	}
	
	private int getMaxRealNet() {
		Optional<PaycheckView> max1 = paycheckViewMap.values().stream().max(Comparator.comparingInt(i -> i.getRealNetPay().intValue()));
		return max1.get().getRealNetPay().intValue() + 1000;
	}

}