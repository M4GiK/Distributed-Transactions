/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 29, 2013.
 */
package com.m4gik.busisess;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This class test behavior during testing distributed transaction.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class DistrbutedTransactionDataSourceTest extends BaseDataSourceTest {

    @AfterTransaction
    public void checkPostConditions() {

        int count = getJdbcWarehousePoland().queryForInt(
                "SELECT COUNT(*) FROM DEPOT");
        // This change was rolled back by the test framework
        assertEquals(0, count);

        count = getJdbcWarehouseFinland().queryForInt(
                "SELECT COUNT(*) FROM DEPOT");
        // This rolled back as well because of the XA
        assertEquals(0, count);

    }

    @BeforeTransaction
    public void clearData() {
        getJdbcFinance().update("DELETE FROM REGISTER");
        getJdbcWarehouseFinland().update("DELETE FROM DEPOT");
        getJdbcWarehousePoland().update("DELETE FROM DEPOT");
    }

    @Transactional
    @Test
    public void testInsertIntoTwoDataSources() throws Exception {

        assertEquals(
                1,
                getJdbcFinance()
                        .update("INSERT INTO REGISTER (id, name, register_date, price) VALUES (?,?,?,?)",
                                0, "stuff", (new Timestamp(0)), 16.0));

        assertEquals(
                1,
                getJdbcWarehouseFinland()
                        .update("INSERT INTO DEPOT (id, name, depot_date, amount) VALUES (?,?,?,?)",
                                0, "stuff", (new Timestamp(0)), 16.0));

        assertEquals(
                1,
                getJdbcWarehousePoland()
                        .update("INSERT INTO DEPOT (id, name, depot_date, amount) VALUES (?,?,?,?)",
                                0, "stuff", (new Timestamp(0)), 16.0));

    }

}
