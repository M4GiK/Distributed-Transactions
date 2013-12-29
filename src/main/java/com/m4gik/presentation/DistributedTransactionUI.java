/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 28, 2013.
 */
package com.m4gik.presentation;

import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;

import com.m4gik.presentation.views.FinanceScreen;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * This class represents user interface for distributed transaction as proof of
 * concept.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
@Theme("runo")
@SuppressWarnings("serial")
public class DistributedTransactionUI extends UI {

    /**
     * 
     * Inner static class represents Servlet. This class can be extended.
     * 
     * @author m4gik <michal.szczygiel@wp.pl>
     * 
     */
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DistributedTransactionUI.class, widgetset = "com.vaadin.DefaultWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    /**
     * Logger for reporting in runtime.
     */
    private final static Logger logger = Logger
            .getLogger(DistributedTransactionUI.class.getName());

    /**
     * Creating object button for tabs in sheet.
     */
    private TabSheet tabSheet = null;

    /**
     * This method build view for finance operations for distributed
     * transactions.
     * 
     * @return
     */
    private Component buildFinance() {
        return new FinanceScreen().build();
    }

    /**
     * 
     * @return
     */
    private Component buildSupply() {
        // TODO Auto-generated method stub
        return new VerticalLayout();
    }

    /**
     * This method build tab sheet for UI.
     * 
     * @param layout
     */
    private TabSheet buildTabSheet(VerticalLayout layout) {
        this.tabSheet = new TabSheet();
        this.tabSheet.setSizeFull();

        this.tabSheet.addTab(buildFinance(), "Finance");
        this.tabSheet.addTab(buildSupply(), "Supply");

        return this.tabSheet;
    }

    /**
     * This method initialize a base components for distributed transaction as
     * UI for application. This method overrides an existing method.
     * 
     * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
     */
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.addComponent(buildTabSheet(layout));
        setContent(layout);
    }

}
