package com.iiplabs.spg.web.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.iiplabs.spg.web.utils.StringUtil;

@Converter
public class EncryptedContentConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return StringUtil.toBase64(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return StringUtil.fromBase64(dbData);
    }

}
