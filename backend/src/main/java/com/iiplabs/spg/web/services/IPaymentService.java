package com.iiplabs.spg.web.services;

import java.util.Collection;

import com.iiplabs.spg.web.model.Payment;
import com.iiplabs.spg.web.model.dto.PaymentDto;

public interface IPaymentService {

    Collection<Payment> findByInvoice(String invoice);

    Payment addPayment(PaymentDto payment);

}
