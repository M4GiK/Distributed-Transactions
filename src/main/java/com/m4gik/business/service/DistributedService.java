/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Jan 2, 2014.
 */
package com.m4gik.business.service;

import com.m4gik.business.model.Finance;
import com.m4gik.business.model.WarehouseFinland;
import com.m4gik.business.model.WarehousePoland;

/**
 * Interface for Distributed Service.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public interface DistributedService {

    /**
     * This method invoked to perform the basic interaction with the database.
     * 
     * @param finance
     * @param warehousePoland
     * @param warehouseFinland
     * @throws Exception
     */
    void persist(Finance finance, WarehousePoland warehousePoland,
            WarehouseFinland warehouseFinland) throws Exception;
}
