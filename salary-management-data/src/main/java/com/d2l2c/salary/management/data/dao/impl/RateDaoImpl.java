/**
 * 
 */
package com.d2l2c.salary.management.data.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.d2l2c.salary.management.data.bean.Rate;
import com.d2l2c.salary.management.data.dao.RateDao;

/**
 * @author dayanlazare
 *
 */
@Repository("rateDao")
public class RateDaoImpl extends AbstractDao<Long, Rate> implements RateDao {

	@Override
	public List<Rate> findByCompanyCodes(String...companyCodes) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		final CriteriaQuery<Rate> criteriaQuery = criteriaBuilder.createQuery(Rate.class);

		final Root<Rate> rateRoot = criteriaQuery.from(Rate.class);

		List<Predicate> criteriaList = new ArrayList<Predicate>();

		for(String companyCode : companyCodes) {
			Predicate predicate = criteriaBuilder.equal(rateRoot.get("company").get("code"), companyCode);
			criteriaList.add(predicate);
		}
		
		criteriaQuery.select(rateRoot);
		
		criteriaQuery.where(criteriaBuilder.or(criteriaList.toArray(new Predicate[0])));

		List<Order> orderList = new ArrayList<Order>();
		orderList.add(criteriaBuilder.asc(rateRoot.get("id")));
		criteriaQuery.orderBy(orderList);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Rate findById(Long id) {
		return findByKey(id);
	}

}
