/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 29, 2013.
 */
package com.m4gik.busisess;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Class testing the data source during transaction.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class DataSourceTests extends BaseDataSourceTest {

    @Transactional
    @Test
    public void testFinanceDataSource() throws Exception {
        getJdbcFinance()
                .update("INSERT INTO REGISTER (id, name, register_date, price) VALUES (?,?,?,?)",
                        0, "stuff", (new Timestamp(0)), 16.0);
        assertEquals(1,
                getJdbcFinance().queryForInt("SELECT COUNT(*) FROM REGISTER"));

        getJdbcFinance().execute("DELETE FROM REGISTER");
        assertEquals(0,
                getJdbcFinance().queryForInt("SELECT COUNT(*) FROM REGISTER"));
    }

    @Transactional
    @Test
    public void testWarehouseFinland() throws Exception {
        getJdbcWarehouseFinland()
                .update("INSERT INTO DEPOT (id, name, depot_date, amount) VALUES (?,?,?,?)",
                        0, "stuff", (new Timestamp(0)), 16.0);
        assertEquals(
                1,
                getJdbcWarehouseFinland().queryForInt(
                        "SELECT COUNT(*) FROM DEPOT"));

        getJdbcWarehouseFinland().execute("DELETE FROM DEPOT");
        assertEquals(
                0,
                getJdbcWarehouseFinland().queryForInt(
                        "SELECT COUNT(*) FROM DEPOT"));
    }

    @Transactional
    @Test
    public void testWarehousePoland() throws Exception {

        getJdbcWarehousePoland()
                .update("INSERT INTO DEPOT (id, name, depot_date, amount) VALUES (?,?,?,?)",
                        0, "stuff", (new Timestamp(0)), 16.0);
        assertEquals(
                1,
                getJdbcWarehousePoland().queryForInt(
                        "SELECT COUNT(*) FROM DEPOT"));

        getJdbcWarehousePoland().execute("DELETE FROM DEPOT");
        assertEquals(
                0,
                getJdbcWarehousePoland().queryForInt(
                        "SELECT COUNT(*) FROM DEPOT"));
    }
}
