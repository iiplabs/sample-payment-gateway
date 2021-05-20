package com.iiplabs.spg.web.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.iiplabs.spg.web.utils.StringUtil;

@Converter
public class EncryptedAmountConverter implements AttributeConverter<Integer, String> {

  @Override
  public String convertToDatabaseColumn(Integer attribute) {
    return StringUtil.toBase64(Integer.toString(attribute.intValue()));
  }

  @Override
  public Integer convertToEntityAttribute(String dbData) {
    return Integer.valueOf(StringUtil.fromBase64(dbData));
  }
  
}
