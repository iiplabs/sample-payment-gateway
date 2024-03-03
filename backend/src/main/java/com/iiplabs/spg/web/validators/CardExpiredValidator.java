package com.iiplabs.spg.web.validators;

import java.time.YearMonth;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.iiplabs.spg.web.utils.CreditCardUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CardExpiredValidator implements ConstraintValidator<CardExpired, String> {

    public static final int LEN = 4;

    @Override
    public boolean isValid(String cardExpired, ConstraintValidatorContext context) {
        // check if passed 'ddmm' is after today
        // return valid for all other issues, as they are checked 
        // in another validator
        if (cardExpired == null || cardExpired.length() != LEN) {
            return true;
        }

        YearMonth expiredDate;
        try {
            expiredDate = CreditCardUtil.getYearMonthFromExpiry(cardExpired);
        } catch (Exception e) {
            log.error(e);
            return true;
        }

        if (expiredDate != null) {
            return expiredDate.isAfter(YearMonth.now());
        }

        return false;
    }

}
