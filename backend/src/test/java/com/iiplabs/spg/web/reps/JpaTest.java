package com.iiplabs.spg.web.reps;

import static org.assertj.core.api.Assertions.assertThat;

import com.iiplabs.spg.web.App;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = App.class)
@DataJpaTest
public class JpaTest {

	@Autowired
	private ICardRepository cards;

	@Autowired
	private ICardHolderRepository cardHolders;
	
	@Autowired
	private IPaymentRepository paymentRepository;

	@Test
	void injectedComponents() {
		assertThat(cards).isNotNull();
		assertThat(cardHolders).isNotNull();
		assertThat(paymentRepository).isNotNull();
	}
	
}