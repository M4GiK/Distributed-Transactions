/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 27, 2013.
 */
package test.jdbc.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Wrapper for a {@link DataSource} that can run scripts on start up and shut
 * down. Us as a bean definition.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class DataSourceInitializer implements InitializingBean, DisposableBean {

    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

    }

    public void destroy() throws Exception {
        // TODO Auto-generated method stub

    }

}
