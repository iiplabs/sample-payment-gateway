package com.iiplabs.spg.web.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiplabs.spg.web.model.dto.CardDto;
import com.iiplabs.spg.web.model.dto.CardHolderDto;
import com.iiplabs.spg.web.model.dto.PaymentDto;
import com.iiplabs.spg.web.services.IPaymentService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@ContextConfiguration(initializers = TestApplicationContextInitializer.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  @Mock
  private IPaymentService paymentService;
  
  @Autowired
  private PaymentController paymentController;

  @Autowired
  private ObjectMapper objectMapper;

  final private String testJsonPaymentDto = "{\"invoice\":\"1234567\",\"amount\":\"1299\",\"currency\":\"EUR\",\"card\":{\"pan\":\"4024007197526238\",\"expiry\":\"0624\",\"cvv\":\"789\"},\"cardholder\":{\"name\":\"First Last\",\"email\":\"test@domain.com\"}}";
  final private String testJsonPaymentDtoBadRequest = "{\"invoice\":\"1234567}";
  final private String testJsonPaymentDtoEmptyInvoice = "{\"invoice\":\"\",\"amount\":\"1299\",\"currency\":\"EUR\",\"card\":{\"pan\":\"4024007197526238\",\"expiry\":\"0624\",\"cvv\":\"789\"},\"cardholder\":{\"name\":\"First Last\",\"email\":\"test@domain.com\"}}";

  @BeforeAll
  public static void setup() {
    //
  }

  @Test
  public void contextLoads() {
    //
  }

  @Test
  public void whenPaymentControllerInjected_thenNotNull() throws Exception {
    assertThat(paymentController).isNotNull();
  }

  @Test
  public void testGetPayment() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments")
    .contentType(MediaType.APPLICATION_JSON)
    .content(testJsonPaymentDto))
    .andExpect(MockMvcResultMatchers.status().isOk());

    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/payments/1234567"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andReturn();
  }
  
  @Test
  public void testGetPaymentNotFound() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/payments/1234567"))
      .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
    
  @Test
  public void testAddPayment() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments")
      .contentType(MediaType.APPLICATION_JSON)
      .content(testJsonPaymentDto))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testAddPaymentMalformed() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments")
      .contentType(MediaType.APPLICATION_JSON)
      .content(testJsonPaymentDtoBadRequest))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void testAddPaymentWrongInput() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments")
      .contentType(MediaType.APPLICATION_JSON)
      .content(testJsonPaymentDtoEmptyInvoice))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
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
    PaymentDto paymentDto = getMockPaymentDto();
    assertEquals(deserializedPaymentDto, paymentDto);
  }

  private static PaymentDto getMockPaymentDto() {
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
    return paymentDto;
  }

}
