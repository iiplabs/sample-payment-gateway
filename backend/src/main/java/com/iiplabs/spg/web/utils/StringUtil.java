package com.iiplabs.spg.web.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public final class StringUtil {

    public static String getLastField(String path) {
        String[] a = path.split("\\.");
        return a[a.length - 1];
    }

    public static String fromBase64(String base64) throws IllegalArgumentException {
        return new String(Base64.decodeBase64(base64.getBytes()));
    }

    public static String toBase64(String v) {
        return new String(Base64.encodeBase64(v.getBytes()));
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
