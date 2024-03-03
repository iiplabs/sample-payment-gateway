package com.iiplabs.spg.web.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.iiplabs.spg.web.utils.StringUtil;

import org.junit.jupiter.api.Test;

class StringUtilTest {

    @Test
    void testBase64CodeDecode() {
        String email = "test@internet.org";
        String encodedEmail = StringUtil.toBase64(email);

        String decodedEmail = StringUtil.fromBase64(encodedEmail);

        assertEquals(email, decodedEmail);
    }

    @Test
    void testGoodLastField() {
        String source = "card.expiry";
        assertEquals("expiry", StringUtil.getLastField(source));
    }

    @Test
    void testBadLastField() {
        String source = "expiry";
        assertEquals("expiry", StringUtil.getLastField(source));
    }

    @Test
    void testMaskedPan() {
        String source = "4024007197526238";
        // leave last 4 characters un-masked
        assertEquals("************6238", StringUtil.maskString(source, 0, 12, '*'));
    }

    @Test
    void testSplitString() {
        String source = "AAA/BBB";
        assertEquals(2, source.split("/").length);
    }

}
