/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Jan 2, 2014.
 */
package com.m4gik.business.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.m4gik.business.model.WarehouseFinland;

/**
 * 
 * This class represent implementation for Data Access Object.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
@Service
public class WarehouseFinlandDAOImpl implements WarehouseFinlandDAO {

    @PersistenceContext(unitName = "PersistenceUnitWarehouseFinland")
    private EntityManager entityManager;

    /**
     * The persistence context needed to persist entity identity as a unique
     * entity instance. This method overrides an existing method.
     * 
     * @see com.m4gik.business.dao.WarehouseFinlandDAO#persistEmployee(com.m4gik.business.model.WarehouseFinland)
     */
    @Override
    public void persistEmployee(WarehouseFinland warehouseFinland)
            throws Exception {
        entityManager.persist(warehouseFinland);
    }

}
