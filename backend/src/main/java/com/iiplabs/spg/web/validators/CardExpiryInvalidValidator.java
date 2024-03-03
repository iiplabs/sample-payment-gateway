package com.iiplabs.spg.web.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.iiplabs.spg.web.utils.CreditCardUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CardExpiryInvalidValidator implements ConstraintValidator<CardExpiryInvalid, String> {

    public static final int LEN = 4;

    @Override
    public boolean isValid(String cardExpired, ConstraintValidatorContext context) {
        if (cardExpired == null || cardExpired.length() != LEN) {
            return false;
        }

        try {
            CreditCardUtil.getYearMonthFromExpiry(cardExpired);
        } catch (Exception e) {
            log.error(e);
            return false;
        }

        return true;
    }

}
