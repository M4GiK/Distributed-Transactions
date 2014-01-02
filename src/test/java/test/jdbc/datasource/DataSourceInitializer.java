/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 27, 2013.
 */
package test.jdbc.datasource;

import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Wrapper for a {@link DataSource} that can run scripts on start up and shut
 * down. Us as a bean definition.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class DataSourceInitializer implements InitializingBean, DisposableBean {

    private static Map<DataSource, Boolean> initialized = new IdentityHashMap<DataSource, Boolean>();

    /**
     * Logger for reporting in runtime.
     */
    private static final Log logger = LogFactory
            .getLog(DataSourceInitializer.class);

    /**
     * Main method as convenient entry point.
     * 
     * @param args
     */
    @SuppressWarnings("resource")
    public static void main(String... args) {
        new ClassPathXmlApplicationContext(
                "META-INF/spring/data-source-context.xml");
    }

    private DataSource dataSource;

    private Resource[] destroyScripts;

    private boolean ignoreFailedDrop = true;

    private Resource[] initScripts;

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * 
     * This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an exception
     * in the event of misconfiguration.
     * 
     * This method overrides an existing method.
     * 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(dataSource);
        if (!initialized.containsKey(dataSource)) {
            initialized.put(dataSource, false);
        }
        initialize();
    }

    /**
     * Invoked by a BeanFactory on destruction of a singleton.
     * 
     * This method overrides an existing method.
     * 
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    public void destroy() {

        if (destroyScripts == null) {
            return;
        }

        for (int i = 0; i < destroyScripts.length; i++) {
            Resource destroyScript = initScripts[i];

            try {
                doExecuteScript(destroyScript);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    logger.warn("Could not execute destroy script ["
                            + destroyScript + "]", e);
                } else {
                    logger.warn("Could not execute destroy script ["
                            + destroyScript + "]");
                }
            }
        }
    }

    /**
     * This method, read all data source from context file, and initialize beans
     * for test.
     * 
     * @param scriptResource
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void doExecuteScript(final Resource scriptResource) {
        if (scriptResource == null || !scriptResource.exists()) {
            return;
        }

        TransactionTemplate transactionTemplate = new TransactionTemplate(
                new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
                String[] scripts;

                try {
                    scripts = StringUtils.delimitedListToStringArray(
                            stripComments(IOUtils.readLines(scriptResource
                                    .getInputStream())), ";");
                } catch (IOException e) {
                    throw new BeanInitializationException(
                            "Cannot load script from [" + scriptResource + "]",
                            e);
                }

                for (int i = 0; i < scripts.length; i++) {
                    String script = scripts[i].trim();

                    if (StringUtils.hasText(script)) {
                        try {
                            jdbcTemplate.execute(script);
                        } catch (DataAccessException e) {
                            if (ignoreFailedDrop
                                    && script.toLowerCase().startsWith("drop")) {
                                logger.debug("DROP script failed (ignoring): "
                                        + script);
                            } else {
                                throw e;
                            }
                        }
                    }
                }
                return null;
            }
        });
    }

    /**
     * @throws Throwable
     * @see java.lang.Object#finalize()
     */
    protected void finalize() throws Throwable {
        super.finalize();
        initialized.clear();
        logger.debug("finalize called");
    }

    /**
     * This method initialize data source to execute.
     */
    private void initialize() {
        if (!initialized.get(dataSource)) {
            destroy();
            if (initScripts != null) {
                for (int i = 0; i < initScripts.length; i++) {
                    Resource initScript = initScripts[i];
                    doExecuteScript(initScript);
                }
            }
            initialized.put(dataSource, true);
        }
    }

    /**
     * 
     * @param dataSource
     *            The dataSource to set.
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 
     * @param destroyScripts
     *            The destroyScripts to set.
     */
    public void setDestroyScripts(Resource[] destroyScripts) {
        this.destroyScripts = destroyScripts;
    }

    /**
     * 
     * @param ignoreFailedDrop
     *            The ignoreFailedDrop to set.
     */
    public void setIgnoreFailedDrop(boolean ignoreFailedDrop) {
        this.ignoreFailedDrop = ignoreFailedDrop;
    }

    /**
     * 
     * @param initScripts
     *            The initScripts to set.
     */
    public void setInitScripts(Resource[] initScripts) {
        this.initScripts = initScripts;
    }

    /**
     * This method deletes comments from list.
     * 
     * @param list
     *            to perform.
     * @return List without comments.
     */
    private String stripComments(List<String> list) {
        StringBuffer buffer = new StringBuffer();
        for (String line : list) {
            if (!line.startsWith("//") && !line.startsWith("--")) {
                buffer.append(line + "\n");
            }
        }
        return buffer.toString();
    }

}
