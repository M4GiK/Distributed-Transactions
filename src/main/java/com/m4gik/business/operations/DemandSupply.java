/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 31, 2013.
 */
package com.m4gik.business.operations;

import org.springframework.transaction.annotation.Transactional;

import com.m4gik.business.BaseDataSource;

/**
 * 
 * This class implements the supply-demand.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class DemandSupply extends BaseDataSource {

    /**
     * 
     */
    @Transactional
    public void makeDemand() {

    }

}
