package com.iiplabs.spg.web.utils;

import java.util.Map;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Path.Node;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import com.iiplabs.spg.web.model.dto.PaymentDto;
import com.iiplabs.spg.web.model.dto.TransactionResponseDto;

public final class TransactionUtil {

    private static Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static TransactionResponseDto validateTransaction(PaymentDto paymentDto) {
        Map<String, String> errors = VALIDATOR.validate(paymentDto).stream()
                .collect(Collectors.toMap(o -> TransactionUtil.getFieldName(o.getPropertyPath()), ConstraintViolation::getMessage));

        return new TransactionResponseDto(errors);
    }

    /**
     * Method takes the field key from the full path
     * for example, "email" from "cardholder.email"
     *
     * @param elements
     * @return
     */
    private static String getFieldName(Path elements) {
        String field = null;
        for (Node node : elements) {
            field = node.getName();
        }
        return field;
    }

    private TransactionUtil() {
        throw new AssertionError();
    }

}
