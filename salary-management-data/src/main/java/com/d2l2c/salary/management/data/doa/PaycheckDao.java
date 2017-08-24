/**
 * 
 */
package com.d2l2c.salary.management.data.doa;

import java.util.List;

import com.d2l2c.salary.management.data.bean.Paycheck;

/**
 * @author dayanlazare
 *
 */
public interface PaycheckDao {
	
	public void save(Paycheck paycheck) throws Exception;
	
	public List<Paycheck> getPaychecks() throws Exception;
	
	public Paycheck getPaycheck(Long id) throws Exception;

}
