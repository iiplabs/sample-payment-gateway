package com.iiplabs.spg.web.services;

import java.util.Arrays;
import java.util.Collection;

import com.iiplabs.spg.web.model.Card;
import com.iiplabs.spg.web.model.CardHolder;
import com.iiplabs.spg.web.model.Payment;
import com.iiplabs.spg.web.model.dto.PaymentDto;
import com.iiplabs.spg.web.reps.IPaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService implements IPaymentService {

	@Autowired
	private IPaymentRepository payments;

	@Autowired
	private IAuditService auditService;

	@Transactional(readOnly = true)
	@Override
	public Collection<Payment> findByInvoice(String invoice) {
		return payments.findByInvoice(invoice);
	}

	@Transactional
	@Override
	public Payment addPayment(PaymentDto paymentDto) {
		// persist to DB
		Payment payment = new Payment();
		payment.setAmount(paymentDto.getIntAmount());

		if (paymentDto.getCard() != null) {
			Card card = new Card();
			card.setExpiry(paymentDto.getCard().getExpiry());
			card.setPan(paymentDto.getCard().getPan());
			payment.setCard(card);
		}

		if (paymentDto.getCardHolder() != null) {
			CardHolder cardHolder = new CardHolder();
			cardHolder.setEmail(paymentDto.getCardHolder().getEmail());
			cardHolder.setName(paymentDto.getCardHolder().getName());
			payment.setCardHolder(cardHolder);
		}

		payment.setCurrency(paymentDto.getCurrency());
		payment.setInvoice(paymentDto.getInvoice());

		payments.save(payment);

		// save audit trail
		// part of the same transaction,
		// as per specs
		auditService.writeToAudit(Arrays.asList(payment));

		return payment;
	}

}
