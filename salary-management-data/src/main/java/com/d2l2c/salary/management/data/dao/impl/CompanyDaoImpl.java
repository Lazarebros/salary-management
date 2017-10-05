/**
 * 
 */
package com.d2l2c.salary.management.data.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.dao.CompanyDao;

/**
 * @author dayanlazare
 *
 */
@Repository("CompanyDao")
public class CompanyDaoImpl extends AbstractDao<Long, Company> implements CompanyDao {

	@Override
	public Company findByCompanyCode(String companyCode) {
		Company company = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		Root<Company> companyRoot = criteria.from(Company.class);
		criteria.select(companyRoot);
		criteria.where(builder.equal(companyRoot.get("code"), companyCode));
		List<Company> companyList = entityManager.createQuery(criteria).getResultList();
		if (!companyList.isEmpty()) {
			company = companyList.get(0);
		}
		return company;
	}

	@Override
	public Company findById(Long id) {
		return findByKey(id);
	}

}
