/**
 * 
 */
package com.d2l2c.salary.management.data.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dayanlazare
 *
 */
@Entity
@Table(name = "paycheck")
public class Paycheck {

	private Long id;
	private String companyName;
	private Date startDate;
	private Date endDate;
	private Long numberOfHours;
	private BigDecimal hourlyRate;
	private BigDecimal grossAmount;
	private BigDecimal netPay;
	private BigDecimal reimbursement;

	public Paycheck() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paycheck_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "number_of_hours")
	public Long getNumberOfHours() {
		return numberOfHours;
	}

	public void setNumberOfHours(Long numberOfHours) {
		this.numberOfHours = numberOfHours;
	}
	
	@Column(name = "hourly_rate")
	public BigDecimal getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(BigDecimal rate) {
		this.hourlyRate = rate;
	}

	@Column(name = "gross_amout")
	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(BigDecimal gross) {
		this.grossAmount = gross;
	}

	@Column(name = "net_pay")
	public BigDecimal getNetPay() {
		return netPay;
	}

	public void setNetPay(BigDecimal netPay) {
		this.netPay = netPay;
	}

	@Column(name = "reimbursement")
	public BigDecimal getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(BigDecimal reimbursement) {
		if(this.reimbursement == null) {
			this.reimbursement = reimbursement;
		} else {
			this.reimbursement = this.reimbursement.add(reimbursement);
		}
	}

	@Override
	public String toString() {
		return "Paycheck [id=" + id + ", companyName=" + companyName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", numberOfHours=" + numberOfHours + ", hourlyRate=" + hourlyRate + ", grossAmount="
				+ grossAmount + ", netPay=" + netPay + ", reimbursement=" + reimbursement + "]";
	}
	
}
