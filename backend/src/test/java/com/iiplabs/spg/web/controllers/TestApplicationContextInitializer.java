package com.iiplabs.spg.web.controllers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class TestApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
  
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.setProperty("AUDIT_JSON", "/tmp/audit.json");
    }

}
