/**
 * 
 */
package com.d2l2c.salary.management.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d2l2c.salary.management.data.bean.Company;

/**
 * @author dayanlazare
 *
 */
public interface CompanyDao extends JpaRepository<Company, Integer> {
	
	public void saveCompany(Company company) throws Exception;
	
	public List<Company> getCompanies() throws Exception;
	
	public Company getCompany(String companyCode) throws Exception;

}
