package com.iiplabs.spg.web.validators;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

	protected static final Collection<String> SYSTEM_CURRENCIES = Arrays.asList("CAD", "EUR", "USD", "JPY");
	
    @Override
    public void initialize(Currency constraintAnnotation) {
 
    }
    
	@Override
	public boolean isValid(String currency, ConstraintValidatorContext context) {
		boolean valid = false;
		if (currency != null) {
			valid = SYSTEM_CURRENCIES.contains(currency);
		}
		return valid;
	}

}
