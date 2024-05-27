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
    final private String testJsonPaymentDtoCardExpired = "{\"invoice\":\"1234567\",\"amount\":\"1299\",\"currency\":\"EUR\",\"card\":{\"pan\":\"4024007197526238\",\"expiry\":\"0110\",\"cvv\":\"789\"},\"cardholder\":{\"name\":\"First Last\",\"email\":\"test@domain.com\"}}";
    final private String testJsonPaymentDtoCardInvalidExpiry = "{\"invoice\":\"1234567\",\"amount\":\"1299\",\"currency\":\"EUR\",\"card\":{\"pan\":\"4024007197526238\",\"expiry\":\"0024\",\"cvv\":\"789\"},\"cardholder\":{\"name\":\"First Last\",\"email\":\"test@domain.com\"}}";

    @BeforeAll
    public static void setup() {
        //
    }

    @Test
    void contextLoads() {
        //
    }

    @Test
    void whenPaymentControllerInjected_thenNotNull() throws Exception {
        assertThat(paymentController).isNotNull();
    }

    @Test
    void testGetPayment() throws Exception {
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
    void testGetPaymentNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/payments/1234567"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testAddPayment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testJsonPaymentDto))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddPaymentMalformed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testJsonPaymentDtoBadRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testAddPaymentWrongInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testJsonPaymentDtoEmptyInvoice))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testAddPaymentCardExpired() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testJsonPaymentDtoCardExpired))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.approved").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.expiry").value("Payment card is expired."));
    }

    @Test
    void testAddPaymentCardInvalidExpiry() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testJsonPaymentDtoCardInvalidExpiry))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.approved").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.expiry").value("Invalid expiry date."));

    }

    @Test
    void serializationTest() throws IOException {
        PaymentDto paymentDto = objectMapper.readValue(testJsonPaymentDto, PaymentDto.class);
        final String serializedPaymentDtoAsJson = objectMapper.writeValueAsString(paymentDto);
        assertEquals(serializedPaymentDtoAsJson, testJsonPaymentDto);
    }

    @Test
    void deserializationTest() throws IOException {
        PaymentDto deserializedPaymentDto = objectMapper.readValue(testJsonPaymentDto, PaymentDto.class);
        PaymentDto paymentDto = getMockPaymentDto();
        assertEquals(deserializedPaymentDto, paymentDto);
    }

    private static PaymentDto getMockPaymentDto() {
        CardDto card = new CardDto("4024007197526238", "0624", "789");
        CardHolderDto cardHolder = new CardHolderDto("First Last", "test@domain.com");
        PaymentDto paymentDto = new PaymentDto("1234567", "1299", "EUR", cardHolder, card);
        return paymentDto;
    }

}
