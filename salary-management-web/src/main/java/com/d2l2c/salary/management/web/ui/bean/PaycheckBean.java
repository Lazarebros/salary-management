/**
 * 
 */
package com.d2l2c.salary.management.web.ui.bean;

import java.math.BigDecimal;
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

	private static final BigDecimal expectedGross = new BigDecimal("8000.00");
	private static final BigDecimal expectedNetPay = new BigDecimal("5600.00");

	private List<Paycheck> paychecks = new ArrayList<Paycheck>();

	private DateTime startDate;
	private DateTime endDate;

	private BigDecimal grossAmount = new BigDecimal(0);
	private BigDecimal netPay = new BigDecimal(0);
	private BigDecimal reimbursement = new BigDecimal(0);

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
		return startDate.getYear();
	}

	public int getMonth() {
		return startDate.getMonthOfYear();
	}

	public String getMonthName() {
		return DateUtil.getMonth(startDate.getMonthOfYear());
	}

	public BigDecimal getGrossRemain() {
		return grossAmount.subtract(expectedGross);
	}

	public BigDecimal getRealNetPay() {
		return netPay.subtract(reimbursement);
	}

	public BigDecimal getNetPayRemain() {
		return getRealNetPay().subtract(expectedNetPay);
	}

	private void setStartDate(DateTime startDate) {
		if(this.startDate == null || this.startDate.isAfter(startDate)) {
			this.startDate = startDate;
		}
	}

	private void setEndDate(DateTime endDate) {
		if(this.endDate == null || this.endDate.isBefore(endDate)) {
			this.startDate = endDate;
		}
	}

	public void addPaycheck(Paycheck paycheck) {
		this.paychecks.add(paycheck);

		this.setStartDate(new DateTime(paycheck.getStartDate()));
		this.setEndDate(new DateTime(paycheck.getEndDate()));

		this.setGrossAmount(paycheck.getGrossAmount());
		this.setNetPay(paycheck.getNetPay());
		this.setReimbursement(paycheck.getReimbursement());
	}

}
