/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Jan 2, 2014.
 */
package com.m4gik.business.dao;

import com.m4gik.business.model.Finance;

/**
 * Interface for Finance Data Access Object.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public interface FinanceDAO {

    /**
     * The persistence context needed to persist entity identity as a unique
     * entity instance.
     * 
     * @param finance
     */
    void persistFinance(Finance finance);

}
