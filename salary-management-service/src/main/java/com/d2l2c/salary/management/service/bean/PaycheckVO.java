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
public class PaycheckVO {

	private Long id;
	private int year;
	private int month;
	private int biWeek;
	private Date startDate;
	private Date endDate;
	private Long numberOfHours;
	private BigDecimal grossAmount;
	private BigDecimal netPay;
	private BigDecimal reimbursement;

	private CompanyVO companyVO;

	public PaycheckVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getBiWeek() {
		return biWeek;
	}

	public void setBiWeek(int biWeek) {
		this.biWeek = biWeek;
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

	public Long getNumberOfHours() {
		return numberOfHours;
	}

	public void setNumberOfHours(Long numberOfHours) {
		this.numberOfHours = numberOfHours;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(BigDecimal gross) {
		this.grossAmount = gross;
	}

	public BigDecimal getNetPay() {
		return netPay;
	}

	public void setNetPay(BigDecimal netPay) {
		this.netPay = netPay;
	}

	public BigDecimal getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(BigDecimal reimbursement) {
		this.reimbursement = reimbursement;
	}

	public CompanyVO getCompany() {
		return companyVO;
	}

	public void setCompany(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}

	public String getMonthName() {
		return "";
	}

}
