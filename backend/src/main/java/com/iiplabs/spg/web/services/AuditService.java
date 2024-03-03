package com.iiplabs.spg.web.services;

import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiplabs.spg.web.model.Payment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AuditService implements IAuditService {

    @Transactional
    @Override
    public void writeToAudit(Collection<Payment> transactions) {
        transactions.stream().forEach(t -> {
            String serializedOutput = null;
            try {
                serializedOutput = MAPPER.writeValueAsString(t);
            } catch (JsonProcessingException e) {
                log.error(e, e);
            }
            if (serializedOutput != null) {
                auditLogger.info(serializedOutput);
            } else {
                auditLogger.info("{'error' : 'problem serializing invoice " + t.getInvoice() + "'}");
            }
        });
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Logger auditLogger = LogManager.getLogger("AuditLogger");

}
