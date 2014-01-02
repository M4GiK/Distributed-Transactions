/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Jan 2, 2014.
 */
package com.m4gik.business.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.m4gik.business.model.Finance;

/**
 * 
 * This class represent implementation for Data Access Object.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
@Service
public class FinanceDAOImpl implements FinanceDAO {

    @PersistenceContext(unitName = "PersistenceUnitFinance")
    private EntityManager entityManager;

    /**
     * The persistence context needed to persist entity identity as a unique
     * entity instance.
     */
    public void persistFinance(Finance finance) {
        entityManager.persist(finance);
    }

}
