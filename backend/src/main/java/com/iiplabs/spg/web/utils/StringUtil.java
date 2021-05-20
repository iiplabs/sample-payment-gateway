package com.iiplabs.spg.web.utils;

import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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

	private StringUtil() {
		throw new AssertionError();
	}

}
