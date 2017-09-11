/**
 * 
 */
package com.d2l2c.salary.management.data.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.dao.CompanyDao;

/**
 * @author dayanlazare
 *
 */
@Repository("CompanyDao")
public class CompanyDaoImpl implements CompanyDao {

	@PersistenceContext(name = "salaryEntityManager", unitName = "salaryPersistenceUnit")
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void saveCompany(Company company) throws Exception {
		em.persist(company);
	}

	@Override
	public List<Company> getCompanies() throws Exception {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		Root<Company> companyRoot = criteria.from(Company.class);

		criteria.select(companyRoot);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public Company getCompany(String companyCode) throws Exception {
		Company company = null;

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		Root<Company> companyRoot = criteria.from(Company.class);
		criteria.select(companyRoot);
		criteria.where(builder.equal(companyRoot.get("companyCode"), companyCode));
		List<Company> companyList = em.createQuery(criteria).getResultList();
		if (!companyList.isEmpty()) {
			company = companyList.get(0);
		}
		return company;
	}

	@Override
	public List<Company> findAll() {
		return null;
	}

	@Override
	public List<Company> findAll(Sort sort) {
		return null;
	}

	@Override
	public List<Company> findAll(Iterable<Integer> ids) {
		return null;
	}

	@Override
	public <S extends Company> List<S> save(Iterable<S> entities) {
		return null;
	}

	@Override
	public void flush() {
	}

	@Override
	public <S extends Company> S saveAndFlush(S entity) {
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Company> entities) {
	}

	@Override
	public void deleteAllInBatch() {
	}

	@Override
	public Company getOne(Integer id) {
		return null;
	}

	@Override
	public <S extends Company> List<S> findAll(Example<S> example) {
		return null;
	}

	@Override
	public <S extends Company> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	@Override
	public Page<Company> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public <S extends Company> S save(S entity) {
		return null;
	}

	@Override
	public Company findOne(Integer id) {
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
	public void delete(Company entity) {
	}

	@Override
	public void delete(Iterable<? extends Company> entities) {
	}

	@Override
	public void deleteAll() {
	}

	@Override
	public <S extends Company> S findOne(Example<S> example) {
		return null;
	}

	@Override
	public <S extends Company> Page<S> findAll(Example<S> example, Pageable pageable) {
		return null;
	}

	@Override
	public <S extends Company> long count(Example<S> example) {
		return 0;
	}

	@Override
	public <S extends Company> boolean exists(Example<S> example) {
		return false;
	}

}
