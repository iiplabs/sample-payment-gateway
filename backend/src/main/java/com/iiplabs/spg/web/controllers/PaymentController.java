package com.iiplabs.spg.web.controllers;

import java.util.Collection;

import com.iiplabs.spg.web.model.Payment;
import com.iiplabs.spg.web.model.dto.PaymentDto;
import com.iiplabs.spg.web.model.dto.TransactionResponseDto;
import com.iiplabs.spg.web.services.IPaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@Validated
public class PaymentController {

		@Autowired
		private IPaymentService paymentService;
		
		@GetMapping("/payments/{invoice}")
		public Collection<Payment> getPayment(@PathVariable String invoice) {
				return paymentService.findByInvoice(invoice);
		}
		
		@PostMapping("/payments")
		public TransactionResponseDto addPayment(@RequestBody PaymentDto paymentDto) {
				return paymentService.addPayment(paymentDto);
		}

}
