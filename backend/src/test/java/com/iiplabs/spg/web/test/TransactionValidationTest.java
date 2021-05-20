package com.iiplabs.spg.web.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.iiplabs.spg.web.model.dto.CardDto;
import com.iiplabs.spg.web.model.dto.CardHolderDto;
import com.iiplabs.spg.web.model.dto.PaymentDto;
import com.iiplabs.spg.web.model.dto.TransactionResponseDto;
import com.iiplabs.spg.web.utils.TransactionUtil;

import org.junit.jupiter.api.Test;

public class TransactionValidationTest {

    @Test
    public void testApproved() {
        PaymentDto paymentDto = TransactionValidationTest.getGoodTest();
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertTrue(response.isApproved());
    }

    @Test
    public void testDeclinedInvoice() {
        PaymentDto paymentDto = TransactionValidationTest.getGoodTest();

        // invoice isn't set
        paymentDto.setInvoice(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // empty
        paymentDto.setInvoice("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // > 50 symbols in length
        paymentDto.setInvoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
    }

    @Test
    public void testDeclinedAmount() {
        PaymentDto paymentDto = TransactionValidationTest.getGoodTest();

        // not set
        paymentDto.setAmount(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // decimal amount
        paymentDto.setAmount("1299.00");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // empty
        paymentDto.setAmount("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // not valid
        paymentDto.setAmount("a 1299");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // negative
        paymentDto.setAmount("-1299");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
    }

    @Test
    public void testDeclinedCurrency() {
        PaymentDto paymentDto = TransactionValidationTest.getGoodTest();

        // not set
        paymentDto.setCurrency(null);
        TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // empty
        paymentDto.setCurrency("");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());

        // not in the approved list
        paymentDto.setCurrency("EUR123");
        response = TransactionUtil.validateTransaction(paymentDto);
        assertFalse(response.isApproved());
    }

    @Test
    public void testDeclinedCardholderName() {
      PaymentDto paymentDto = TransactionValidationTest.getGoodTest();

      // CardholderName isn't set
      paymentDto.getCardHolder().setName(null);
      TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // empty
      paymentDto.getCardHolder().setName("");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // > 50 symbols in length
      paymentDto.getCardHolder().setName("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());
    }

    @Test
    public void testDeclinedEmail() {
      PaymentDto paymentDto = TransactionValidationTest.getGoodTest();

      // Email isn't set
      paymentDto.getCardHolder().setEmail(null);
      TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // empty
      paymentDto.getCardHolder().setEmail("");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // invalid email
      paymentDto.getCardHolder().setEmail("testdomain.com");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());
      // invalid email
      paymentDto.getCardHolder().setEmail("@testdomain.com");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());
    }

    @Test
    public void testDeclinedPan() {
      PaymentDto paymentDto = TransactionValidationTest.getGoodTest();

      // PAN isn't set
      paymentDto.getCard().setPan(null);
      TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // empty
      paymentDto.getCard().setPan("");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // PAN shorter than 16 digits
      paymentDto.getCard().setPan("402400719752623");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // alpha
      paymentDto.getCard().setPan("a402400719752623");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());
    }

    @Test
    public void testDeclinedCardExpired() {
      PaymentDto paymentDto = TransactionValidationTest.getGoodTest();

      // Expiry isn't set
      paymentDto.getCard().setExpiry(null);
      TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // invalid date
      paymentDto.getCard().setExpiry("");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // invalid date
      paymentDto.getCard().setExpiry("132");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // invalid date
      paymentDto.getCard().setExpiry("1325");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // expired date - April 2021
      paymentDto.getCard().setExpiry("0421");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());
    }

    @Test
    public void testDeclinedCvv() {
      PaymentDto paymentDto = TransactionValidationTest.getGoodTest();
      // CVV isn't set
      paymentDto.getCard().setCvv(null);
      TransactionResponseDto response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // invalid CVV
      paymentDto.getCard().setCvv("");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());

      // invalid CVV
      paymentDto.getCard().setCvv("1234");
      response = TransactionUtil.validateTransaction(paymentDto);
      assertFalse(response.isApproved());
    }

    private static PaymentDto getGoodTest() {
      PaymentDto paymentDto = new PaymentDto();
      paymentDto.setAmount("1299");

      CardDto cardDto = new CardDto();
      cardDto.setCvv("789");
      cardDto.setExpiry("0624");
      cardDto.setPan("4024007197526238");
      paymentDto.setCard(cardDto);

      CardHolderDto cardHolderDto = new CardHolderDto();
      cardHolderDto.setEmail("email@domain.com");
      cardHolderDto.setName("First Last");
      paymentDto.setCardHolder(cardHolderDto);
      
      paymentDto.setCurrency("EUR");
      paymentDto.setInvoice("1234567");

      return paymentDto;
    }

}
