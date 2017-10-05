/**
 * 
 */
package com.d2l2c.salary.management.data.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.dao.PaycheckDao;

/**
 * @author dayanlazare
 *
 */
@Repository("paycheckDao")
public class PaycheckDaoImpl extends AbstractDao<Long, Paycheck> implements PaycheckDao {

	@Override
	public List<Paycheck> findByCompanyCodes(String...companyCodes) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		final CriteriaQuery<Paycheck> criteriaQuery = criteriaBuilder.createQuery(Paycheck.class);

		final Root<Paycheck> paycheckRoot = criteriaQuery.from(Paycheck.class);

		List<Predicate> criteriaList = new ArrayList<Predicate>();

		for (String companyCode : companyCodes) {
			Predicate predicate = criteriaBuilder.equal(paycheckRoot.get("company").get("code"), companyCode);
			criteriaList.add(predicate);
		}

		criteriaQuery.select(paycheckRoot);

		criteriaQuery.where(criteriaBuilder.or(criteriaList.toArray(new Predicate[0])));

		List<Order> orderList = new ArrayList<Order>();
		orderList.add(criteriaBuilder.asc(paycheckRoot.get("id")));
		criteriaQuery.orderBy(orderList);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Paycheck> findPaychecksByYears(Integer...years) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		final CriteriaQuery<Paycheck> criteriaQuery = criteriaBuilder.createQuery(Paycheck.class);

		final Root<Paycheck> paycheckRoot = criteriaQuery.from(Paycheck.class);

		List<Predicate> criteriaList = new ArrayList<Predicate>();

		for (int year : years) {
			Predicate predicate = criteriaBuilder.equal(paycheckRoot.get("year"), year);
			criteriaList.add(predicate);
		}

		criteriaQuery.select(paycheckRoot);

		criteriaQuery.where(criteriaBuilder.or(criteriaList.toArray(new Predicate[0])));

		List<Order> orderList = new ArrayList<Order>();
		orderList.add(criteriaBuilder.asc(paycheckRoot.get("id")));
		criteriaQuery.orderBy(orderList);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Paycheck findById(Long id) {
		return findByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getPaycheckYears() {
		final Query query = entityManager.createQuery("SELECT DISTINCT paycheck.year From Paycheck paycheck ORDER BY paycheck.year DESC");
		return query.getResultList();
	}

}
