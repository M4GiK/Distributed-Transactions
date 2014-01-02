/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Jan 2, 2014.
 */
package com.m4gik.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m4gik.business.dao.FinanceDAO;
import com.m4gik.business.dao.WarehouseFinlandDAO;
import com.m4gik.business.dao.WarehousePolandDAO;
import com.m4gik.business.model.Finance;
import com.m4gik.business.model.WarehouseFinland;
import com.m4gik.business.model.WarehousePoland;

/**
 * 
 * This class represent implementation for Distributed Service.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
@Service("distributedService")
public class DistributedServiceImpl implements DistributedService {

    @Autowired
    FinanceDAO financeDAO;

    @Autowired
    WarehouseFinlandDAO warehouseFinlandDAO;

    @Autowired
    WarehousePolandDAO warehousePolandDAO;

    /**
     * This method invoked to perform the basic interaction with the database.
     * This method overrides an existing method.
     * 
     * @see com.m4gik.business.service.DistributedService#persist(com.m4gik.business.model.Finance,
     *      com.m4gik.business.model.WarehousePoland,
     *      com.m4gik.business.model.WarehouseFinland)
     */
    @Transactional(rollbackFor = Exception.class)
    public void persist(Finance finance, WarehousePoland warehousePoland,
            WarehouseFinland warehouseFinland) throws Exception {
        financeDAO.persistFinance(finance);
        warehousePolandDAO.persistWarehousePoland(warehousePoland);
        warehouseFinlandDAO.persistWarehouseFinalnd(warehouseFinland);
        System.out.println("PERSIST ALL");
    }

}
