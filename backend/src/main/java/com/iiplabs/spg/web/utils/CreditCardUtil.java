package com.iiplabs.spg.web.utils;

import java.time.DateTimeException;
import java.time.LocalDate;

public final class CreditCardUtil {
  
  public static final int BASE_YEAR = 2000;
  public static final int FIRST_DAY_OF_MONTH = 1;

  public static LocalDate getLocalDateFromExpiry(String expiry) throws IndexOutOfBoundsException, NumberFormatException, DateTimeException {
    int month = Integer.parseInt(expiry.substring(0, 2));
    int year = Integer.parseInt(expiry.substring(2));
    return LocalDate.of(BASE_YEAR + year, month, FIRST_DAY_OF_MONTH);
  }

  private CreditCardUtil() {
		throw new AssertionError();
	}

}
