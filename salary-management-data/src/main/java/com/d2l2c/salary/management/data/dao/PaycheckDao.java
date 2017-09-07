/**
 * 
 */
package com.d2l2c.salary.management.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d2l2c.salary.management.data.bean.Paycheck;

/**
 * @author dayanlazare
 *
 */
public interface PaycheckDao extends JpaRepository<Paycheck, Integer> {
	
	public void savePaycheck(Paycheck paycheck) throws Exception;
	
	public List<Paycheck> getPaychecks() throws Exception;
	
	public Paycheck getPaycheck(Long id) throws Exception;

}
