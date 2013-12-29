/**
 * Project Distributed Transactions.
 * Copyright Michał Szczygieł, 2013.
 * Created at Dec 28, 2013.
 */
package com.m4gik.presentation.views;

import com.vaadin.ui.Layout;

/**
 * Interface for building the proper view.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public interface ViewScreen {

    /**
     * Method to build proper view.
     * 
     * @return The layout to build the view.
     */
    public Layout build();

}
