package com.iiplabs.spg.web.services;

import java.util.Collection;

import com.iiplabs.spg.web.model.Payment;

public interface IAuditService {

    void writeToAudit(Collection<Payment> transactions);
     
}
