/**
 * 
 */
package com.d2l2c.salary.management.data.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.d2l2c.salary.management.data.bean.Rate;
import com.d2l2c.salary.management.data.dao.RateDao;

/**
 * @author dayanlazare
 *
 */
@Repository("RateDao")
public class RateDaoImpl implements RateDao {

	@PersistenceContext(name = "salaryEntityManager", unitName = "salaryPersistenceUnit")
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void saveRate(Rate rate) throws Exception {
		em.persist(rate);
	}

	@Override
	public List<Rate> getRates() throws Exception {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Rate> criteria = builder.createQuery(Rate.class);
		Root<Rate> rateRoot = criteria.from(Rate.class);

		criteria.select(rateRoot);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Rate> getRates(List<String> companyCodes) throws Exception {
		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

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

		return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Rate getRate(Long id) throws Exception {
		Rate rate = null;

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Rate> criteria = builder.createQuery(Rate.class);
		Root<Rate> rateRoot = criteria.from(Rate.class);
		criteria.select(rateRoot);
		criteria.where(builder.equal(rateRoot.get("id"), id));
		List<Rate> rateList = em.createQuery(criteria).getResultList();
		if (!rateList.isEmpty()) {
			rate = rateList.get(0);
		}
		return rate;
	}

	@Override
	public List<Rate> findAll() {
		return null;
	}

	@Override
	public List<Rate> findAll(Sort sort) {
		return null;
	}

	@Override
	public List<Rate> findAll(Iterable<Integer> ids) {
		return null;
	}

	@Override
	public <S extends Rate> List<S> save(Iterable<S> entities) {
		return null;
	}

	@Override
	public void flush() {
	}

	@Override
	public <S extends Rate> S saveAndFlush(S entity) {
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Rate> entities) {
	}

	@Override
	public void deleteAllInBatch() {
	}

	@Override
	public Rate getOne(Integer id) {
		return null;
	}

	@Override
	public <S extends Rate> List<S> findAll(Example<S> example) {
		return null;
	}

	@Override
	public <S extends Rate> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	@Override
	public Page<Rate> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public <S extends Rate> S save(S entity) {
		return null;
	}

	@Override
	public Rate findOne(Integer id) {
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		return false;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void delete(Integer id) {
	}

	@Override
	public void delete(Rate entity) {
	}

	@Override
	public void delete(Iterable<? extends Rate> entities) {
	}

	@Override
	public void deleteAll() {
	}

	@Override
	public <S extends Rate> S findOne(Example<S> example) {
		return null;
	}

	@Override
	public <S extends Rate> Page<S> findAll(Example<S> example, Pageable pageable) {
		return null;
	}

	@Override
	public <S extends Rate> long count(Example<S> example) {
		return 0;
	}

	@Override
	public <S extends Rate> boolean exists(Example<S> example) {
		return false;
	}

}
