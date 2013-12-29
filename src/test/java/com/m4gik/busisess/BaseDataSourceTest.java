/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 29, 2013.
 */
package com.m4gik.busisess;

import java.io.File;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This class sets base data sources for project.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/data-source-context.xml")
public class BaseDataSourceTest {

    /**
     * Method for logging.
     */
    @BeforeClass
    @AfterClass
    public static void clearLog() {

        // Ensure that Atomikos logging directory exists
        File dir = new File("atomikos");
        if (!dir.exists()) {
            dir.mkdir();
        }

        // ...and delete any stale locks (this would be a sign of a crash)
        File tmlog = new File("atomikos/tmlog.lck");
        if (tmlog.exists()) {
            tmlog.delete();
        }
    }

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
