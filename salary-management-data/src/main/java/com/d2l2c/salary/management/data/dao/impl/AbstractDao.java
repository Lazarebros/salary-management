/**
 * 
 */
package com.d2l2c.salary.management.data.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author dayanlazare
 *
 */
public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> clazz;

	@PersistenceContext(name = "salaryEntityManager", unitName = "salaryPersistenceUnit")
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.clazz =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	public T findByKey(PK key) {
		return entityManager.find(clazz, key);
	}

	public List<T> findAll() {
		CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(clazz);
		@SuppressWarnings("unused")
		Root<T> root = criteriaQuery.from(clazz);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public void deleteByKey(PK key) {
		T entity = findByKey(key);
		delete(entity);
	}

	public CriteriaQuery<T> createEntityCriteria() {
		return entityManager.getCriteriaBuilder().createQuery(clazz);
	}

}
