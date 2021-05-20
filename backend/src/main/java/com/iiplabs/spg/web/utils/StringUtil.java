package com.iiplabs.spg.web.utils;

import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil {

	public static String fromBase64(String base64) throws IllegalArgumentException {
		return fromByteArray(Base64.getDecoder().decode(base64));
	}

	public static String fromByteArray(byte[] a) throws IllegalArgumentException {
		String r = "";
		try {
			CharsetDecoder decoder = StandardCharsets.US_ASCII.newDecoder();		 
		    decoder.onMalformedInput(CodingErrorAction.REPLACE)
		      .onUnmappableCharacter(CodingErrorAction.REPLACE)
		      .replaceWith("?");
		 
		    r = decoder.decode(ByteBuffer.wrap(a)).toString();
		} catch(Exception e) {
			throw new IllegalArgumentException(e);
		}
	    return r;
	}

	public static String toBase64(String v) {
		return Base64.getEncoder().withoutPadding().encodeToString(v.getBytes());
	}

	public static String maskString(String source, int start, int end, char maskChar) {
		if (source == null || source.equals("")) {
			return "";
		}

		if (start < 0) {
			start = 0;
		}

		if (end > source.length()) {
			end = source.length();
		}
		
		if (start > end) {
			throw new IllegalArgumentException("End index greater than start index");
		}

		int maskLength = end - start;

		if (maskLength == 0) {
			return source;
		}

		String strMaskString = StringUtils.repeat(maskChar, maskLength);

		return StringUtils.overlay(source, strMaskString, start, end);
	}

	private StringUtil() {
		throw new AssertionError();
	}

}
