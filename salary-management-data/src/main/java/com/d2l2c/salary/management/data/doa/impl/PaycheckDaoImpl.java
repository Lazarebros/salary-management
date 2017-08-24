/**
 * 
 */
package com.d2l2c.salary.management.data.doa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.doa.PaycheckDao;

/**
 * @author dayanlazare
 *
 */
@Repository("PersonService")
public class PaycheckDaoImpl implements PaycheckDao {

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Paycheck paycheck) throws Exception {
		em.persist(paycheck);
	}

	@Override
	public List<Paycheck> getPaychecks() throws Exception {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Paycheck> criteria = builder.createQuery(Paycheck.class);
		Root<Paycheck> paycheckRoot = criteria.from(Paycheck.class);

		criteria.select(paycheckRoot);

		List<Order> orderList = new ArrayList<Order>();

//		orderList.add(builder.desc(paycheckRoot.get("startDate")));
		orderList.add(builder.desc(paycheckRoot.get("id")));
		criteria.orderBy(orderList);

		return em.createQuery(criteria).getResultList();
	}

	@Override
	public Paycheck getPaycheck(Long id) throws Exception {
		Paycheck paycheck = null;

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Paycheck> criteria = builder.createQuery(Paycheck.class);
		Root<Paycheck> paycheckRoot = criteria.from(Paycheck.class);
		criteria.select(paycheckRoot);
		criteria.where(builder.equal(paycheckRoot.get("id"), id));
		List<Paycheck> paycheckList = em.createQuery(criteria).getResultList();
		if (!paycheckList.isEmpty()) {
			paycheck = paycheckList.get(0);
		}
		return paycheck;
	}

}
