package com.iiplabs.spg.web.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.iiplabs.spg.web.utils.StringUtil;

import org.junit.jupiter.api.Test;

public class UtilTest {

	@Test
	public void testBase64CodeDecode() {
		String email = "test@internet.org";
		String encodedEmail = StringUtil.toBase64(email);
		
		String decodedEmail = StringUtil.fromBase64(encodedEmail);
		
		assertEquals(email, decodedEmail);
	}
	
	@Test
	public void testMaskedPan() {
		 String source = "4024007197526238";
		 // leave last 4 characters un-masked
		 assertEquals("************6238", StringUtil.maskString(source, 0, 12, '*'));
	}

}
