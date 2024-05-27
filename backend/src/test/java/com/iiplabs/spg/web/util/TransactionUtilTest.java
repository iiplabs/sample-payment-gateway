package com.iiplabs.spg.web.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.iiplabs.spg.web.model.dto.CardDto;
import com.iiplabs.spg.web.model.dto.CardHolderDto;
import com.iiplabs.spg.web.model.dto.PaymentDto;
import com.iiplabs.spg.web.model.dto.TransactionResponseDto;
import com.iiplabs.spg.web.utils.TransactionUtil;

import org.junit.jupiter.api.Test;

class TransactionUtilTest {

    @Test
    void testApproved() {
        PaymentDto paymentDto = TransactionUtilTest.getGoodTest();
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertTrue(response.isApproved());
    }

    @Test
    void testDeclinedInvoice() {
        // invoice isn't set
        PaymentDto paymentDto = TransactionUtilTest.getDeclinedInvoice(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // empty
        paymentDto = TransactionUtilTest.getDeclinedInvoice("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // > 50 symbols in length
        paymentDto = TransactionUtilTest.getDeclinedInvoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
    }

    @Test
    void testDeclinedAmount() {
        // not set
        PaymentDto paymentDto = TransactionUtilTest.getDeclinedAmount(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // decimal amount
        paymentDto = TransactionUtilTest.getDeclinedAmount("1299.00");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // empty
        paymentDto = TransactionUtilTest.getDeclinedAmount("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // not valid
        paymentDto = TransactionUtilTest.getDeclinedAmount("a 1299");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // 0
        paymentDto = TransactionUtilTest.getDeclinedAmount("0");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // negative
        paymentDto = TransactionUtilTest.getDeclinedAmount("-1299");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // leading 000
        paymentDto = TransactionUtilTest.getDeclinedAmount("01299");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertTrue(response.isApproved());
    }

    @Test
    void testDeclinedCurrency() {
        // not set
        PaymentDto paymentDto = TransactionUtilTest.getDeclinedCurrency(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // empty
        paymentDto = TransactionUtilTest.getDeclinedCurrency("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // not in the approved list
        paymentDto = TransactionUtilTest.getDeclinedCurrency("EUR123");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
    }

    @Test
    void testDeclinedCardholderName() {
        // CardholderName isn't set        
        PaymentDto paymentDto = TransactionUtilTest.getDeclinedCardholderName(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // empty
        paymentDto = TransactionUtilTest.getDeclinedCardholderName("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // > 50 symbols in length
        paymentDto = TransactionUtilTest.getDeclinedCardholderName("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
    }

    @Test
    void testDeclinedEmail() {
        // Email isn't set
        PaymentDto paymentDto = TransactionUtilTest.getDeclinedEmail(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // empty
        paymentDto = TransactionUtilTest.getDeclinedEmail("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // invalid email
        paymentDto = TransactionUtilTest.getDeclinedEmail("testdomain.com");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
        // invalid email
        paymentDto = TransactionUtilTest.getDeclinedEmail("@testdomain.com");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
    }

    @Test
    void testDeclinedPan() {
        // PAN isn't set
        PaymentDto paymentDto = TransactionUtilTest.getDeclinedPan(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // empty
        paymentDto = TransactionUtilTest.getDeclinedPan("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // PAN shorter than 16 digits
        paymentDto = TransactionUtilTest.getDeclinedPan("402400719752623");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // alpha
        paymentDto = TransactionUtilTest.getDeclinedPan("a402400719752623");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
    }

    @Test
    void testDeclinedCardExpired() {
        // Expiry isn't set
        PaymentDto paymentDto = TransactionUtilTest.getDeclinedCardExpired(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // invalid date
        paymentDto = TransactionUtilTest.getDeclinedCardExpired("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // invalid date
        paymentDto = TransactionUtilTest.getDeclinedCardExpired("132");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // invalid date
        paymentDto = TransactionUtilTest.getDeclinedCardExpired("1325");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // expired date - April 2021
        paymentDto = TransactionUtilTest.getDeclinedCardExpired("0421");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
    }

    @Test
    void testDeclinedCvv() {
        // CVV isn't set
        PaymentDto paymentDto = TransactionUtilTest.getDeclinedCvv(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // invalid CVV
        paymentDto = TransactionUtilTest.getDeclinedCvv("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // invalid CVV
        paymentDto = TransactionUtilTest.getDeclinedCvv("1234");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
    }

    private static PaymentDto getGoodTest() {
        CardDto card = new CardDto("4024007197526238", "0624", "789");
        CardHolderDto cardHolder = new CardHolderDto("First Last", "test@domain.com");
        PaymentDto paymentDto = new PaymentDto("1234567", "1299", "EUR", cardHolder, card);
        return paymentDto;
    }

    private static PaymentDto getDeclinedAmount(String amount) {
        CardDto card = new CardDto("4024007197526238", "0624", "789");
        CardHolderDto cardHolder = new CardHolderDto("First Last", "test@domain.com");
        PaymentDto paymentDto = new PaymentDto("1234567", amount, "EUR", cardHolder, card);
        return paymentDto;
    }

    private static PaymentDto getDeclinedInvoice(String invoice) {
        CardDto card = new CardDto("4024007197526238", "0624", "789");
        CardHolderDto cardHolder = new CardHolderDto("First Last", "test@domain.com");
        PaymentDto paymentDto = new PaymentDto(invoice, "1299", "EUR", cardHolder, card);
        return paymentDto;
    }

    private static PaymentDto getDeclinedCurrency(String currency) {
        CardDto card = new CardDto("4024007197526238", "0624", "789");
        CardHolderDto cardHolder = new CardHolderDto("First Last", "test@domain.com");
        PaymentDto paymentDto = new PaymentDto("1234567", "1299", currency, cardHolder, card);
        return paymentDto;
    }

    private static PaymentDto getDeclinedCardholderName(String cardholderName) {
        CardDto card = new CardDto("4024007197526238", "0624", "789");
        CardHolderDto cardHolder = new CardHolderDto(cardholderName, "test@domain.com");
        PaymentDto paymentDto = new PaymentDto("1234567", "1299", "EUR", cardHolder, card);
        return paymentDto;
    }

    private static PaymentDto getDeclinedCvv(String cvv) {
        CardDto card = new CardDto("4024007197526238", "0624", cvv);
        CardHolderDto cardHolder = new CardHolderDto("First Last", "test@domain.com");
        PaymentDto paymentDto = new PaymentDto("1234567", "1299", "EUR", cardHolder, card);
        return paymentDto;
    }

    private static PaymentDto getDeclinedCardExpired(String expiry) {
        CardDto card = new CardDto("4024007197526238", expiry, "789");
        CardHolderDto cardHolder = new CardHolderDto("First Last", "test@domain.com");
        PaymentDto paymentDto = new PaymentDto("1234567", "1299", "EUR", cardHolder, card);
        return paymentDto;
    }

    private static PaymentDto getDeclinedEmail(String email) {
        CardDto card = new CardDto("4024007197526238", "0624", "789");
        CardHolderDto cardHolder = new CardHolderDto("First Last", email);
        PaymentDto paymentDto = new PaymentDto("1234567", "1299", "EUR", cardHolder, card);
        return paymentDto;
    }

    private static PaymentDto getDeclinedPan(String pan) {
        CardDto card = new CardDto(pan, "0624", "789");
        CardHolderDto cardHolder = new CardHolderDto("First Last", "test@domain.com");
        PaymentDto paymentDto = new PaymentDto("1234567", "1299", "EUR", cardHolder, card);
        return paymentDto;
    }

}
