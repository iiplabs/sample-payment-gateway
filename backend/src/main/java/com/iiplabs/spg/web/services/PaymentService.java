package com.iiplabs.spg.web.services;

import java.util.Arrays;
import java.util.Collection;

import com.iiplabs.spg.web.model.Card;
import com.iiplabs.spg.web.model.CardHolder;
import com.iiplabs.spg.web.model.Payment;
import com.iiplabs.spg.web.model.dto.PaymentDto;
import com.iiplabs.spg.web.reps.IPaymentRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("paymentService")
public class PaymentService implements IPaymentService {

    private IPaymentRepository paymentsRepository;
    private IAuditService auditService;

    public PaymentService(@Qualifier("paymentsRepository") IPaymentRepository paymentsRepository, 
                            @Qualifier("auditService") IAuditService auditService) {
        this.paymentsRepository = paymentsRepository;
        this.auditService = auditService;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Payment> findByInvoice(String invoice) {
        return paymentsRepository.findByInvoice(invoice);
    }

    @Transactional
    @Override
    public Payment addPayment(PaymentDto paymentDto) {
        // persist to DB
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getIntAmount());

        if (paymentDto.card() != null) {
            Card card = new Card();
            card.setExpiry(paymentDto.card().expiry());
            card.setPan(paymentDto.card().pan());
            payment.setCard(card);
        }

        if (paymentDto.cardHolder() != null) {
            CardHolder cardHolder = new CardHolder();
            cardHolder.setEmail(paymentDto.cardHolder().email());
            cardHolder.setName(paymentDto.cardHolder().name());
            payment.setCardHolder(cardHolder);
        }

        payment.setCurrency(paymentDto.currency());
        payment.setInvoice(paymentDto.invoice());

        paymentsRepository.save(payment);

        // save audit trail
        // part of the same transaction,
        // as per specs
        auditService.writeToAudit(Arrays.asList(payment));

        return payment;
    }

}
