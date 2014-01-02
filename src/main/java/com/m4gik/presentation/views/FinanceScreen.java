/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 29, 2013.
 */
package com.m4gik.presentation.views;

import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * This class represent basic operations for finance database.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class FinanceScreen implements ViewScreen {

    /**
     * This method build view for tab finance. This method overrides an existing
     * method.
     * 
     * @see com.m4gik.presentation.views.ViewScreen#build()
     */
    @Override
    public Layout build() {

        return new VerticalLayout();
    }

}
