package com.iiplabs.spg.web.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

public class DateTimeUtilTest {

  @Test
	public void testDateTimeFormatter() {
			LocalDateTime localDateTime = LocalDateTime.of(2021, 7, 7, 13, 30, 45).plus(861, ChronoUnit.MILLIS);
			String dateString = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(localDateTime);
			System.out.println(dateString);
			assertEquals("2021-07-07T13:30:45.861Z", dateString);
	}

}
