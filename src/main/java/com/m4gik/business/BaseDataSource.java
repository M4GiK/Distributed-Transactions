/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 28, 2013.
 */
package com.m4gik.business;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
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
    private SimpleJdbcTemplate jdbcFinance;

    /**
     * JDBC object to the warehouse in Finland.
     */
    private SimpleJdbcTemplate jdbcWarehouseFinland;

    /**
     * JDBC object to the warehouse in Poland.
     */
    private SimpleJdbcTemplate jdbcWarehousePoland;

    /**
     * @return the jdbcFinance
     */
    public SimpleJdbcTemplate getJdbcFinance() {
        return jdbcFinance;
    }

    /**
     * @return the jdbcWarehouseFinland
     */
    public SimpleJdbcTemplate getJdbcWarehouseFinland() {
        return jdbcWarehouseFinland;
    }

    /**
     * @return the jdbcWarehousePoland
     */
    public SimpleJdbcTemplate getJdbcWarehousePoland() {
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
        this.jdbcWarehousePoland = new SimpleJdbcTemplate(
                dataSourceWarehousePoland);
        this.jdbcWarehouseFinland = new SimpleJdbcTemplate(
                dataSourceWarehouseFinland);
        this.jdbcFinance = new SimpleJdbcTemplate(dataSourceFinance);
    }
}
