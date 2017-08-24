/**
 * 
 */
package com.d2l2c.salary.management.data.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dayanlazare
 *
 */
public class RateConfig {

	private Date startDate;
	private Date endDate;
	private BigDecimal hourly;
	private Long weeklyExpectedNumberOfHours;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getHourly() {
		return hourly;
	}

	public void setHourly(BigDecimal hourly) {
		this.hourly = hourly;
	}

	public Long getWeeklyExpectedNumberOfHours() {
		return weeklyExpectedNumberOfHours;
	}

	public void setWeeklyExpectedNumberOfHours(Long weeklyExpectedNumberOfHours) {
		this.weeklyExpectedNumberOfHours = weeklyExpectedNumberOfHours;
	}

}
