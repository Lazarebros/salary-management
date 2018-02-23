/**
 * 
 */
package com.d2l2c.salary.management.service.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dayanlazare
 *
 */
public class CompanyVO {

	protected Long id;
	protected String code;
	protected String name;

	private Set<PaycheckVO> paycheckVOs = new HashSet<PaycheckVO>();
	private Set<RateVO> rateVOs = new HashSet<RateVO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PaycheckVO> getPaychecks() {
		return paycheckVOs;
	}

	public void setPaychecks(Set<PaycheckVO> paycheckVOs) {
		this.paycheckVOs = paycheckVOs;
	}

	public Set<RateVO> getRates() {
		return rateVOs;
	}

	public void setRates(Set<RateVO> rateVOs) {
		this.rateVOs = rateVOs;
	}

}
