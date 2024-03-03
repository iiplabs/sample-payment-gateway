package com.iiplabs.spg.web.config;

import java.util.EnumSet;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SessionTrackingModeSetter implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext()
                .setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
    }

}
