package com.iiplabs.spg.web.utils;

import java.time.DateTimeException;
import java.time.YearMonth;

public final class CreditCardUtil {
  
  public static final int BASE_YEAR = 2000;

  public static YearMonth getYearMonthFromExpiry(String expiry) throws IndexOutOfBoundsException, NumberFormatException, DateTimeException {
    int month = Integer.parseInt(expiry.substring(0, 2));
    int year = Integer.parseInt(expiry.substring(2));
    return YearMonth.of(BASE_YEAR + year, month);
  }

  private CreditCardUtil() {
		throw new AssertionError();
	}

}
