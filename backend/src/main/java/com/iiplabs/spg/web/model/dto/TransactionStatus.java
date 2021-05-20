package com.iiplabs.spg.web.model.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionStatus {

  APPROVED(true),
  DECLINED(false);

  private static final Map<Boolean, TransactionStatus> codeMap = new HashMap<>();
  static {
    codeMap.put(APPROVED.code, APPROVED);
    codeMap.put(DECLINED.code, DECLINED);
  }

  private final boolean code;
  
  TransactionStatus(boolean code) {
    this.code = code;
  }

  @JsonValue
  public boolean code() {
    return code;
  }

  @JsonCreator
  public static TransactionStatus findByCode(boolean code) {
    return codeMap.get(code);
  }
  
}