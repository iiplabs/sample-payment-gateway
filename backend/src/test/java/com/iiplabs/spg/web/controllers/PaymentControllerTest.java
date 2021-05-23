package com.iiplabs.spg.web.controllers;

import static org.assertj.core.api.Assertions.assertThat;

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

  @Test
	public void contextLoads() {
    //
	}

  @Test
  public void whenPaymentControllerInjected_thenNotNull() throws Exception {
    assertThat(paymentController).isNotNull();
  }

}
