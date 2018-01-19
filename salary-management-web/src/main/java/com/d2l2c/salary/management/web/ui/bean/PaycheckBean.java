/**
 * 
 */
package com.d2l2c.salary.management.web.ui.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.d2l2c.common.util.date.DateUtil;
import com.d2l2c.salary.management.data.bean.Paycheck;

/**
 * @author dayanlazare
 *
 */
public class PaycheckBean {

	private List<Paycheck> paychecks = new ArrayList<Paycheck>();

	private DateTime startDate;
	private DateTime endDate;
	private int year;
	
	private int monthOfPay;

	private BigDecimal grossAmount = new BigDecimal("0.00");
	private BigDecimal netPay = new BigDecimal("0.00");
	private BigDecimal reimbursement = new BigDecimal("0.00");

	private BigDecimal expectedGross = new BigDecimal("0.00");
	private BigDecimal expectedNetPay = new BigDecimal("0.00");

	public PaycheckBean() {
	}

	public List<Paycheck> getPaychecks() {
		return paychecks;
	}

	public String getCompanyCode() {
		return paychecks.get(0).getCompany().getCode();
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = this.grossAmount.add(grossAmount);
	}

	public BigDecimal getNetPay() {
		return netPay;
	}

	public void setNetPay(BigDecimal netPay) {
		this.netPay = this.netPay.add(netPay);
	}

	public BigDecimal getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(BigDecimal reimbursement) {
		this.reimbursement = this.reimbursement.add(reimbursement);
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMonthName() {
		return DateUtil.getMonth(monthOfPay);
	}

	public BigDecimal getRealNetPay() {
		return netPay.subtract(reimbursement);
	}

	public BigDecimal getRealNetPayMean() {
		return getRealNetPay().divide(new BigDecimal(getMonthOfPay()), RoundingMode.HALF_UP);
	}

	public BigDecimal getNetPayRemain() {
		return getRealNetPay().subtract(expectedNetPay.multiply(new BigDecimal("2")));
	}

	public BigDecimal getGrossRemain() {
		return grossAmount.subtract(expectedGross.multiply(new BigDecimal("2")));
	}

	private void setStartDate(DateTime startDate) {
		if (this.startDate == null || this.startDate.isAfter(startDate)) {
			this.startDate = startDate;
		}
	}

	private void setEndDate(DateTime endDate) {
		if (this.endDate == null || this.endDate.isBefore(endDate)) {
			this.startDate = endDate;
		}
	}

	public int getMonthOfPay() {
		return monthOfPay;
	}

	public void setMonthOfPay(int monthOfPay) {
		this.monthOfPay = monthOfPay;
	}

	public void addPaycheck(Paycheck paycheck) {
		this.paychecks.add(paycheck);

		this.setStartDate(new DateTime(paycheck.getStartDate()));
		this.setEndDate(new DateTime(paycheck.getEndDate()));
		this.setYear(paycheck.getYear());
		
		this.setMonthOfPay(paycheck.getMonth());

		this.setGrossAmount(paycheck.getGrossAmount());
		this.setNetPay(paycheck.getNetPay());
		this.setReimbursement(paycheck.getReimbursement());

		this.expectedGross = paycheck.getCompany().getRates().iterator().next().getExpectedGross();
		this.expectedNetPay = paycheck.getCompany().getRates().iterator().next().getExpectedNetPay();
	}

	public int getYearProgress() {
		return (getMonthOfPay() * 100) / 12;
	}

	public String getMonthPayState() {
		Double monthlyExpctedGross = this.expectedGross.multiply(new BigDecimal("2")).doubleValue();
		String state = "";
		if (this.getGrossRemain().doubleValue() >= monthlyExpctedGross) {
			state = "double";
		} else if (this.getGrossRemain().doubleValue() == 0.0) {
			state = "same";
		} else if (this.getGrossRemain().doubleValue() < 0) {
			state = "less";
		}
		return state;
	}

}
