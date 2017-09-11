/**
 * 
 */
package com.d2l2c.salary.management.data.bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author dayanlazare
 *
 */
@Entity
@Table(name = "companies")
public class Company {

	protected Long id;
	protected String code;
	protected String name;
	private BigDecimal expectedNetPay;

	private Set<Paycheck> paychecks = new HashSet<Paycheck>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "expected_net_pay")
	public BigDecimal getExpectedNetPay() {
		return expectedNetPay;
	}

	public void setExpectedNetPay(BigDecimal expectedNetPay) {
		this.expectedNetPay = expectedNetPay;
	}

	@Transient
	public Set<Paycheck> getPaychecks() {
		return paychecks;
	}

	public void setPaychecks(Set<Paycheck> paychecks) {
		this.paychecks = paychecks;
	}

}
