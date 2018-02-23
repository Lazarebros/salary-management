/**
 * 
 */
package com.d2l2c.salary.management.service.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dayanlazare
 *
 */
public class RateVO {

	private Long id;
	private BigDecimal hourlyRate;
	private Date startDate;
	private Date endDate;
	private BigDecimal expectedGross;
	private BigDecimal expectedNetPay;

	private CompanyVO companyVO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(BigDecimal hourly) {
		this.hourlyRate = hourly;
	}

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

	public BigDecimal getExpectedGross() {
		return expectedGross;
	}

	public void setExpectedGross(BigDecimal expectedGross) {
		this.expectedGross = expectedGross;
	}

	public BigDecimal getExpectedNetPay() {
		return expectedNetPay;
	}

	public void setExpectedNetPay(BigDecimal expectedNetPay) {
		this.expectedNetPay = expectedNetPay;
	}

	public CompanyVO getCompany() {
		return companyVO;
	}

	public void setCompany(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}

}
