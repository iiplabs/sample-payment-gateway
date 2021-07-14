package com.iiplabs.spg.web.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiplabs.spg.web.model.dto.CardDto;
import com.iiplabs.spg.web.model.dto.CardHolderDto;
import com.iiplabs.spg.web.model.dto.PaymentDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@ContextConfiguration(initializers = TestApplicationContextInitializer.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {

  @Autowired
	private MockMvc mockMvc;
  
  @Autowired
  PaymentController paymentController;

  @Autowired
  private ObjectMapper objectMapper;

  final private String testJsonPaymentDto = "{\"invoice\":\"1234567\",\"amount\":\"1299\",\"currency\":\"EUR\",\"card\":{\"pan\":\"4024007197526238\",\"expiry\":\"0624\",\"cvv\":\"789\"},\"cardholder\":{\"name\":\"First Last\",\"email\":\"test@domain.com\"}}";

  @Test
	public void contextLoads() {
    //
	}

  @Test
  public void whenPaymentControllerInjected_thenNotNull() throws Exception {
    assertThat(paymentController).isNotNull();
  }

  @Test
  public void serializationTest() throws IOException {
    PaymentDto paymentDto = objectMapper.readValue(testJsonPaymentDto, PaymentDto.class);
    final String serializedPaymentDtoAsJson = objectMapper.writeValueAsString(paymentDto);
    assertEquals(serializedPaymentDtoAsJson, testJsonPaymentDto);
  }

  @Test
  public void deserializationTest() throws IOException {
    PaymentDto deserializedPaymentDto = objectMapper.readValue(testJsonPaymentDto, PaymentDto.class);
    PaymentDto paymentDto = new PaymentDto();
    paymentDto.setAmount("1299");
    CardDto card = new CardDto();
    card.setCvv("789");
    card.setExpiry("0624");
    card.setPan("4024007197526238");
    paymentDto.setCard(card);
    CardHolderDto cardHolder = new CardHolderDto();
    cardHolder.setEmail("test@domain.com");
    cardHolder.setName("First Last");
    paymentDto.setCardHolder(cardHolder);
    paymentDto.setCurrency("EUR");
    paymentDto.setInvoice("1234567");
    assertEquals(deserializedPaymentDto, paymentDto);
  }

}
