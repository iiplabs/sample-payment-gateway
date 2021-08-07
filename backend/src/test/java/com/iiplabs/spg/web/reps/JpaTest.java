package com.iiplabs.spg.web.reps;

import static org.assertj.core.api.Assertions.assertThat;

import com.iiplabs.spg.web.App;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(classes = App.class)
@DataJpaTest
public class JpaTest {

	@Autowired
	private IPaymentRepository paymentRepository;

	@Test
	void injectedComponents() {
		assertThat(paymentRepository).isNotNull();
	}
	
}