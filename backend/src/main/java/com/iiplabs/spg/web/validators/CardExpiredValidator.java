package com.iiplabs.spg.web.validators;

import java.time.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.iiplabs.spg.web.utils.CreditCardUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CardExpiredValidator implements ConstraintValidator<CardExpired, String> {

  	public static final int LEN = 4;
	
    @Override
    public void initialize(CardExpired constraintAnnotation) {
 
    }
    
	@Override
	public boolean isValid(String cardExpired, ConstraintValidatorContext context) {
        if (cardExpired == null || cardExpired.length() != LEN) {
            return true;
        }

        YearMonth expiredDate = null;
        try {
            expiredDate = CreditCardUtil.getYearMonthFromExpiry(cardExpired);
        } catch (Exception e) {
            log.error(e);
        }

        return expiredDate != null && expiredDate.isAfter(YearMonth.now());
	}

}
