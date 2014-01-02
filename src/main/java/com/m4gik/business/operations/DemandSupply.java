/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 31, 2013.
 */
package com.m4gik.business.operations;

import java.util.HashMap;

import org.springframework.jdbc.core.JdbcTemplate;
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

    public final static String AMOUNT = "AMOUNT";
    public final static String DATE = "DAPOT_DATETIME";
    public final static String NAME = "NAME_FOR_ARTICLE";
    public final static String TABLE_NAME = "TABLE_NAME";

    public DemandSupply() {
        super();
    }

    /**
     * 
     * @param demand
     * @param simpleJdbcTemplate
     */
    private void checkWarehouse(HashMap<String, String> demand,
            JdbcTemplate simpleJdbcTemplate) {

        if (simpleJdbcTemplate.queryForInt(createSelectQuery(demand)) < 1) {
            // simpleJdbcTemplate.update(createUpdateQuery(demand,
            // simpleJdbcTemplate));
            createUpdateQuery(demand, simpleJdbcTemplate);
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
     * @return String with select query with question about size elements with
     *         amount more then 100 .
     */
    private String createSelectQuery(HashMap<String, String> demand) {
        String createdQuery = "SELECT COUNT(amount) FROM  ";
        createdQuery += demand.get(TABLE_NAME);
        createdQuery += " WHERE name =  " + demand.get(NAME);
        createdQuery += " AND amount > 100";

        return createdQuery;
    }

    /**
     * 
     * @param demand
     * @param simpleJdbcTemplate
     * @return
     */
    private String createUpdateQuery(HashMap<String, String> demand,
            JdbcTemplate simpleJdbcTemplate) {
        String createdQuery = "SELECT amount FROM " + demand.get(TABLE_NAME);
        createdQuery += " WHERE name =  " + demand.get(NAME);

        Integer amount = simpleJdbcTemplate.queryForInt(createdQuery);
        System.out.println("TROLOLO " + amount);

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
    public void makeDemand(HashMap<String, String> demand, String country)
            throws Exception {
        if (country.equalsIgnoreCase("Poland")) {
            checkWarehouse(demand, getJdbcWarehousePoland());
        }

        if (country.equalsIgnoreCase("Finland")) {
            checkWarehouse(demand, getJdbcWarehouseFinland());
        }
    }

}
