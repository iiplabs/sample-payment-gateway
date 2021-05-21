package com.iiplabs.spg.web.services;

import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiplabs.spg.web.model.Payment;

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
        e.printStackTrace();
      }
      if (serializedOutput != null) {
        log.info(serializedOutput);
      } else {
        log.info("{'error' : 'problem serializing invoice " + t.getInvoice() + "'}");
      }
    });
  }
  
  private static final ObjectMapper MAPPER = new ObjectMapper();
}
