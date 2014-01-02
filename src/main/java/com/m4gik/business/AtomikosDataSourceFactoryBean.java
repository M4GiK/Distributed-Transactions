/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 27, 2013.
 */
package com.m4gik.business;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;

/**
 * A {@link FactoryBean} for an Atomikos pooled {@link DataSource} taking care
 * of lifecycle callbacks through Spring, and allowing an {@link XADataSource}
 * to be injected directly, instead of relying on Atomikos to create one.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
@SuppressWarnings("rawtypes")
public class AtomikosDataSourceFactoryBean extends AbstractFactoryBean {

    /**
     * Local variable holding a size for pool connectivity.
     */
    private int connectionPoolSize;

    /**
     * Local variable holding a mode for exclusive connection.
     */
    private boolean exclusiveConnectionMode;

    /**
     * Local variable holding a name for unique resource.
     */
    private String uniqueResourceName;

    /**
     * Local variable holding a XA data source.
     */
    private XADataSource xaDataSource;

    /**
     * This method set resources for Atomikos transactions.
     * 
     * Template method that subclasses must override to construct the object
     * returned by this factory.
     * 
     * Invoked on initialization of this FactoryBean in case of a singleton;
     * else, on each getObject() call.
     * 
     * Overrides: createInstance() in AbstractFactoryBean Returns:
     * 
     * @return The object returned by this factory
     * 
     * @throws Exception
     *             - if an exception occurred during object creation
     * 
     */
    @Override
    protected Object createInstance() throws Exception {
        AtomikosDataSourceBean result = new AtomikosDataSourceBean();
        result.setXaDataSource(xaDataSource);
        result.setUniqueResourceName(uniqueResourceName);
        // result.setConnectionPoolSize(connectionPoolSize);
        result.setPoolSize(connectionPoolSize);
        // result.setExclusiveConnectionMode(exclusiveConnectionMode);
        result.init();

        return result;
    }

    /**
     * Ensure that the data source is cleaned up when the application context is
     * closed.
     * 
     * @see AbstractFactoryBean#destroyInstance(Object)
     */
    @Override
    protected void destroyInstance(Object instance) throws Exception {
        ((AtomikosDataSourceBean) instance).close();
    }

    /**
     * 
     * This method overrides an existing method.
     * 
     * @see org.springframework.beans.factory.config.AbstractFactoryBean#getObjectType()
     */
    @Override
    public Class<AtomikosDataSourceBean> getObjectType() {
        return AtomikosDataSourceBean.class;
    }

    /**
     * Forcing to creates singleton.
     * 
     * This method overrides an existing method.
     * 
     * @see org.springframework.beans.factory.config.AbstractFactoryBean#isSingleton()
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * This method set connection pool size.
     * 
     * @param connectionPoolSize
     */
    public void setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    /**
     * This method set exclusive connection mode.
     * 
     * @param exclusiveConnectionMode
     */
    public void setExclusiveConnectionMode(boolean exclusiveConnectionMode) {
        this.exclusiveConnectionMode = exclusiveConnectionMode;
    }

    /**
     * This method set unique resource name.
     * 
     * @param uniqueResourceName
     */
    public void setUniqueResourceName(String uniqueResourceName) {
        this.uniqueResourceName = uniqueResourceName;
    }

    /**
     * The {@link XADataSource} to inject into the pool.
     * 
     * @param xaDataSource
     */
    public void setXaDataSource(XADataSource xaDataSource) {
        this.xaDataSource = xaDataSource;
    }

}
