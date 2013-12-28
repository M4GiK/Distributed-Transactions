package com.m4gik.presentation;

import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("runo")
@SuppressWarnings("serial")
public class DistributedTransactionUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DistributedTransactionUI.class, widgetset = "com.vaadin.DefaultWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    private final static Logger logger = Logger
            .getLogger(DistributedTransactionUI.class.getName());

    @Override
    protected void init(VaadinRequest request) {
        // TODO Auto-generated method stub

    }

}
