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

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.dao.PaycheckDao;

/**
 * @author dayanlazare
 *
 */
@Repository("paycheckDao")
public class PaycheckDaoImpl implements PaycheckDao {

	@PersistenceContext(name = "salaryEntityManager", unitName = "salaryPersistenceUnit")
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void savePaycheck(Paycheck paycheck) throws Exception {
		em.persist(paycheck);
	}

	@Override
	public List<Paycheck> getPaychecks() throws Exception {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Paycheck> criteria = builder.createQuery(Paycheck.class);
		Root<Paycheck> paycheckRoot = criteria.from(Paycheck.class);

		criteria.select(paycheckRoot);

		List<Order> orderList = new ArrayList<Order>();

		orderList.add(builder.asc(paycheckRoot.get("id")));
		criteria.orderBy(orderList);

		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Paycheck> getPaychecks(List<String> companyCodes) throws Exception {
		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		final CriteriaQuery<Paycheck> criteriaQuery = criteriaBuilder.createQuery(Paycheck.class);

		final Root<Paycheck> paycheckRoot = criteriaQuery.from(Paycheck.class);

		List<Predicate> criteriaList = new ArrayList<Predicate>();

		for(String companyCode : companyCodes) {
			Predicate predicate = criteriaBuilder.equal(paycheckRoot.get("company").get("code"), companyCode);
			criteriaList.add(predicate);
		}
		
		criteriaQuery.select(paycheckRoot);
		
		criteriaQuery.where(criteriaBuilder.or(criteriaList.toArray(new Predicate[0])));

		List<Order> orderList = new ArrayList<Order>();
		orderList.add(criteriaBuilder.asc(paycheckRoot.get("id")));
		criteriaQuery.orderBy(orderList);

		return em.createQuery(criteriaQuery).getResultList();
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

	@Override
	public List<Paycheck> findAll() {
		return null;
	}

	@Override
	public List<Paycheck> findAll(Sort sort) {
		return null;
	}

	@Override
	public List<Paycheck> findAll(Iterable<Integer> ids) {
		return null;
	}

	@Override
	public <S extends Paycheck> List<S> save(Iterable<S> entities) {
		return null;
	}

	@Override
	public void flush() {
	}

	@Override
	public <S extends Paycheck> S saveAndFlush(S entity) {
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Paycheck> entities) {
	}

	@Override
	public void deleteAllInBatch() {
	}

	@Override
	public Paycheck getOne(Integer id) {
		return null;
	}

	@Override
	public <S extends Paycheck> List<S> findAll(Example<S> example) {
		return null;
	}

	@Override
	public <S extends Paycheck> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	@Override
	public Page<Paycheck> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public <S extends Paycheck> S save(S entity) {
		return null;
	}

	@Override
	public Paycheck findOne(Integer id) {
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
	public void delete(Paycheck entity) {
	}

	@Override
	public void delete(Iterable<? extends Paycheck> entities) {
	}

	@Override
	public void deleteAll() {
	}

	@Override
	public <S extends Paycheck> S findOne(Example<S> example) {
		return null;
	}

	@Override
	public <S extends Paycheck> Page<S> findAll(Example<S> example, Pageable pageable) {
		return null;
	}

	@Override
	public <S extends Paycheck> long count(Example<S> example) {
		return 0;
	}

	@Override
	public <S extends Paycheck> boolean exists(Example<S> example) {
		return false;
	}

}
