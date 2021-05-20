package com.iiplabs.spg.web.services;

import java.util.Arrays;
import java.util.Collection;

import com.iiplabs.spg.web.model.Card;
import com.iiplabs.spg.web.model.CardHolder;
import com.iiplabs.spg.web.model.Payment;
import com.iiplabs.spg.web.model.dto.PaymentDto;
import com.iiplabs.spg.web.model.dto.TransactionResponseDto;
import com.iiplabs.spg.web.reps.ICardHolderRepository;
import com.iiplabs.spg.web.reps.ICardRepository;
import com.iiplabs.spg.web.reps.IPaymentRepository;
import com.iiplabs.spg.web.utils.TransactionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService implements IPaymentService {

		@Autowired
		private ICardRepository cards;

		@Autowired
		private ICardHolderRepository cardHolders;

		@Autowired
		private IPaymentRepository payments;

		@Autowired
		private IAuditService auditService;

		@Transactional(readOnly=true)
		@Override
		public Collection<Payment> findByInvoice(String invoice) {
				return payments.findByInvoice(invoice);
		}

		@Transactional
		@Override
		public TransactionResponseDto addPayment(PaymentDto paymentDto) {
				TransactionResponseDto transactionStatus = TransactionUtil.validateTransaction(paymentDto);
				if (transactionStatus.isApproved()) {
						// persist to DB
						Payment payment = new Payment();
						payment.setAmount(paymentDto.getIntAmount());

						Card card = new Card();
						card.setExpiry(paymentDto.getCard().getExpiry());
						card.setPan(paymentDto.getCard().getPan());
						card.addPayment(payment);
						payment.setCard(card);

						CardHolder cardHolder = new CardHolder();
						cardHolder.setEmail(paymentDto.getCardHolder().getEmail());
						cardHolder.setName(paymentDto.getCardHolder().getName());
						cardHolder.addPayment(payment);
						payment.setCardHolder(cardHolder);

						payment.setCurrency(paymentDto.getCurrency());
						payment.setInvoice(paymentDto.getInvoice());
						
						cards.save(card);
						cardHolders.save(cardHolder);
						payments.save(payment);

						// save audit trail
						// part of the same transaction,
						// as per specs
						auditService.writeToAudit(Arrays.asList(payment));
				}
				return transactionStatus;
		}

}
