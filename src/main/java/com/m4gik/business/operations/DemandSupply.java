/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 31, 2013.
 */
package com.m4gik.business.operations;

import java.util.HashMap;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
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

    private final static String AMOUNT = "AMOUNT";
    private final static String DATE = "DAPOT_DATETIME";
    private final static String NAME = "NAME_FOR_ARTICLE";
    private final static String TABLE_NAME = "TABLE_NAME";

    private void checkWarehouse(HashMap<String, String> demand,
            SimpleJdbcTemplate simpleJdbcTemplate) {
        String createdQuery = createSelectQuery(demand);

        if (simpleJdbcTemplate.getJdbcOperations().queryForInt(createdQuery) < 1) {

        }
    }

    /**
     * This method creates select query for given parameter.
     * 
     * @param demand
     *            The map order for supply. HashMap structure: first element -
     *            TABLE_NAME second element - NAME_FOR_ARTICLE third element -
     *            DAPOT_DATETIME fourth element - AMOUNT.
     * 
     * @return String with select query.
     */
    private String createSelectQuery(HashMap<String, String> demand) {
        String createdQuery = "SELECT COUNT(amount) FROM  ";
        createdQuery += demand.get(TABLE_NAME);
        createdQuery += " WHERE ";
        // createdQuery += demand.get(NAME);
        // createdQuery += demand.get(DATE);
        // createdQuery += demand.get(AMOUNT);

        return createdQuery;
    }

    /**
     * This method make demand for supply.
     * 
     * @param demand
     *            The map order for supply. HashMap structure: first element -
     *            TABLE_NAME second element - NAME_FOR_ARTICLE third element -
     *            DAPOT_DATETIME fourth element - AMOUNT.
     * 
     */
    @Transactional
    public void makeDemand(HashMap<String, String> demand, String country) {
        if (country.equalsIgnoreCase("Poland")) {
            checkWarehouse(demand, getJdbcWarehousePoland());
        }

        if (country.equalsIgnoreCase("Finland")) {
            checkWarehouse(demand, getJdbcWarehouseFinland());
        }
    }

}
