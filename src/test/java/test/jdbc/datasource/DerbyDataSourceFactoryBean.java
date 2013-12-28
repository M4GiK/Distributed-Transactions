/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 28, 2013.
 */
package test.jdbc.datasource;

import java.io.File;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.apache.derby.jdbc.EmbeddedXADataSource;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class DerbyDataSourceFactoryBean extends AbstractFactoryBean {

    public interface ExtendedDataSource extends DataSource, XADataSource {

    }

    private String databaseName = "derbydb";;

    private String dataDirectory = "derby-home";

    protected Object createInstance() throws Exception {
        File directory = new File(dataDirectory);
        System.setProperty("derby.system.home", directory.getCanonicalPath());
        System.setProperty("derby.storage.fileSyncTransactionLog", "true");
        System.setProperty("derby.storage.pageCacheSize", "100");

        final EmbeddedXADataSource ds = new EmbeddedXADataSource();
        ds.setDatabaseName(databaseName);
        ds.setCreateDatabase("create");

        return ds;
    }

    @Override
    protected void destroyInstance(Object instance) throws Exception {
        EmbeddedXADataSource dataSource = (EmbeddedXADataSource) instance;
        dataSource.setShutdownDatabase("shutdown");
        dataSource.getConnection().close();
    }

    public Class<ExtendedDataSource> getObjectType() {
        return ExtendedDataSource.class;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setDataDirectory(String dataDirectory) {
        this.dataDirectory = dataDirectory;
    }

}
