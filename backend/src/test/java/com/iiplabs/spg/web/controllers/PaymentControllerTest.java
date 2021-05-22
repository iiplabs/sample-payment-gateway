package com.iiplabs.spg.web.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.iiplabs.spg.web.App;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = App.class)
@SpringBootTest
public class PaymentControllerTest {

  @Autowired
  PaymentController paymentController;

  /* @Test
  public void whenPaymentControllerInjected_thenNotNull() throws Exception {
    assertThat(paymentController).isNotNull();
  } */

}
