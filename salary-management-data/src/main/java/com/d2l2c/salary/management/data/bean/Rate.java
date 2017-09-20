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
@Table(name = "rates")
public class Rate {

	private Long id;
	private BigDecimal hourly;
	private Date startDate;
	private Date endDate;
	private BigDecimal expectedGross;
	private BigDecimal expectedNetPay;

	private Company company;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rate_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "hourly")
	public BigDecimal getHourly() {
		return hourly;
	}

	public void setHourly(BigDecimal hourly) {
		this.hourly = hourly;
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

	@Column(name = "expected_gross")
	public BigDecimal getExpectedGross() {
		return expectedGross;
	}

	public void setExpectedGross(BigDecimal expectedGross) {
		this.expectedGross = expectedGross;
	}

	@Column(name = "expected_net_pay")
	public BigDecimal getExpectedNetPay() {
		return expectedNetPay;
	}

	public void setExpectedNetPay(BigDecimal expectedNetPay) {
		this.expectedNetPay = expectedNetPay;
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
