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
public class PaycheckPeriodBean {

	private Period period;

	private List<Paycheck> paychecks = new ArrayList<Paycheck>();

	private DateTime startDate;
	private DateTime endDate;

	private BigDecimal grossAmount = new BigDecimal(0);
	private BigDecimal netPay = new BigDecimal(0);
	private BigDecimal reimbursement = new BigDecimal(0);

	public PaycheckPeriodBean(Period period) {
		this.period = period;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public List<Paycheck> getPaychecks() {
		return paychecks;
	}

	public void setPaychecks(List<Paycheck> paychecks) {
		this.paychecks = paychecks;
		grossAmount = paychecks.stream().map(Paycheck::getGrossAmount).reduce((x, y) -> x.add(y)).get();
		netPay = paychecks.stream().map(Paycheck::getNetPay).reduce((x, y) -> x.add(y)).get();
		reimbursement = paychecks.stream().map(Paycheck::getReimbursement).reduce((x, y) -> x.add(y)).get();
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

	public BigDecimal getRealNetPay() {
		return netPay.subtract(reimbursement);
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

	public void addPaycheck(Paycheck paycheck) {
		this.paychecks.add(paycheck);

		this.setStartDate(new DateTime(paycheck.getStartDate()));
		this.setEndDate(new DateTime(paycheck.getEndDate()));

		this.setGrossAmount(paycheck.getGrossAmount());
		this.setNetPay(paycheck.getNetPay());
		this.setReimbursement(paycheck.getReimbursement());
	}

}
