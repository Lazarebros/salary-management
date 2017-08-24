/**
 * 
 */
package com.d2l2c.salary.management.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.doa.PaycheckDao;
import com.d2l2c.salary.management.data.service.PaycheckService;

/**
 * @author dayanlazare
 *
 */
@Service("personService")
public class PaycheckServiceImpl implements PaycheckService {

	@Autowired
	private PaycheckDao paycheckDao;

	@Transactional
	@Override
	public void save(Paycheck paycheck) throws Exception {
		paycheckDao.save(paycheck);
	}

	@Transactional
	@Override
	public List<Paycheck> getPaychecks() throws Exception {
		return paycheckDao.getPaychecks();
	}

	@Transactional
	@Override
	public Paycheck getPaycheck(Long id) throws Exception {
		return paycheckDao.getPaycheck(id);
	}

}
