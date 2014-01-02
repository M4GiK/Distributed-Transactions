/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 28, 2013.
 */
package com.m4gik.business;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class sets base data sources for project.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
@ContextConfiguration(locations = "/META-INF/spring/data-source-context.xml")
public class BaseDataSource {

    /**
     * JDBC object to the Finance.
     */
    private JdbcTemplate jdbcFinance;

    /**
     * JDBC object to the warehouse in Finland.
     */
    private JdbcTemplate jdbcWarehouseFinland;

    /**
     * JDBC object to the warehouse in Poland.
     */
    private JdbcTemplate jdbcWarehousePoland;

    /**
     * @return the jdbcFinance
     */
    public JdbcTemplate getJdbcFinance() {
        return jdbcFinance;
    }

    /**
     * @return the jdbcWarehouseFinland
     */
    public JdbcTemplate getJdbcWarehouseFinland() {
        return jdbcWarehouseFinland;
    }

    /**
     * @return the jdbcWarehousePoland
     */
    public JdbcTemplate getJdbcWarehousePoland() {
        return jdbcWarehousePoland;
    }

    /**
     * This method set data source for proper source from
     * data-source-context.xml.
     * 
     * @param dataSourceWarehousePoland
     * @param dataSourceWarehouseFinland
     * @param dataSourceFinance
     */
    @Autowired
    public void setDataSources(
            @Qualifier("dataSourceWarehousePoland") DataSource dataSourceWarehousePoland,
            @Qualifier("dataSourceWarehouseFinland") DataSource dataSourceWarehouseFinland,
            @Qualifier("dataSourceFinance") DataSource dataSourceFinance) {
        this.jdbcWarehousePoland = new JdbcTemplate(dataSourceWarehousePoland);
        this.jdbcWarehouseFinland = new JdbcTemplate(dataSourceWarehouseFinland);
        this.jdbcFinance = new JdbcTemplate(dataSourceFinance);
        System.out.println("LOL");
    }
}
