package com.iiplabs.spg.web.utils;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.iiplabs.spg.web.model.dto.PaymentDto;
import com.iiplabs.spg.web.model.dto.TransactionResponseDto;
import com.iiplabs.spg.web.model.dto.TransactionStatus;

public final class TransactionUtil {

    private static Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static TransactionResponseDto validateTransaction(PaymentDto paymentDto) {
		Map<String, String> errors = VALIDATOR.validate(paymentDto).stream()
            .collect(Collectors.toMap(o -> o.getPropertyPath().toString(), ConstraintViolation::getMessage));
        
        TransactionStatus status = TransactionStatus.APPROVED;
        if (errors.size() > 0) {
            status = TransactionStatus.DECLINED;
        }

        return TransactionResponseDto.builder().status(status).errors(errors).build();
    }

    private TransactionUtil() {
		    throw new AssertionError();
	  }

}
