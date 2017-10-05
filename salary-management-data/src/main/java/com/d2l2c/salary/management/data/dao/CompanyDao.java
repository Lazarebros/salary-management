/**
 * 
 */
package com.d2l2c.salary.management.data.dao;

import java.util.List;

import com.d2l2c.salary.management.data.bean.Company;

/**
 * @author dayanlazare
 *
 */
public interface CompanyDao {
	
	public void save(Company company);
	
	public List<Company> findAll();
	
	public Company findById(Long id);
	
	public Company findByCompanyCode(String companyCode);

}
