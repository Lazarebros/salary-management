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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author dayanlazare
 *
 */
@Entity
@Table(name = "paychecks")
public class Paycheck {

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

	private Company company;

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

	@Column(name = "year")
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(name = "month")
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Column(name = "bi_week")
	public int getBiWeek() {
		return biWeek;
	}

	public void setBiWeek(int biWeek) {
		this.biWeek = biWeek;
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

	@Column(name = "gross_amount")
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
		if (this.reimbursement == null) {
			this.reimbursement = reimbursement;
		} else {
			this.reimbursement = this.reimbursement.add(reimbursement);
		}
	}

	@ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
