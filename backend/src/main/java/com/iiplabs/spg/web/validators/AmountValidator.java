package com.iiplabs.spg.web.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AmountValidator implements ConstraintValidator<Amount, String> {

    @Override
    public void initialize(Amount constraintAnnotation) {
 
    }
    
	@Override
	public boolean isValid(String amount, ConstraintValidatorContext context) {
    boolean valid = false;
		if (amount != null && amount.length() > 0) {
      try {
        int i = Integer.parseInt(amount);
        if (i > 0) {
          valid = true;
        }
      } catch (NumberFormatException e) {
        log.error(e);
      }
    }
    return valid;
	}

}
