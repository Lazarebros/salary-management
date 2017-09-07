/**
 * 
 */
package com.d2l2c.salary.management.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.dao.PaycheckDao;
import com.d2l2c.salary.management.data.service.PaycheckService;

/**
 * @author dayanlazare
 *
 */
@Service("paycheckService")
public class PaycheckServiceImpl implements PaycheckService {

	@Autowired
	private PaycheckDao paycheckDao;

	@Transactional("salaryTransactionManager")
	@Override
	public void save(Paycheck paycheck) throws Exception {
		paycheckDao.save(paycheck);
	}

	@Transactional("salaryTransactionManager")
	@Override
	public List<Paycheck> getPaychecks() throws Exception {
		return paycheckDao.getPaychecks();
	}

	@Transactional("salaryTransactionManager")
	@Override
	public Paycheck getPaycheck(Long id) throws Exception {
		return paycheckDao.getPaycheck(id);
	}

}
