package com.iiplabs.spg.web.controllers;

import java.util.Collection;

import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;
import com.iiplabs.spg.web.annotations.RestControllerAnnotation;
import com.iiplabs.spg.web.model.Payment;
import com.iiplabs.spg.web.model.dto.PaymentDto;
import com.iiplabs.spg.web.model.dto.TransactionResponseDto;
import com.iiplabs.spg.web.services.IPaymentService;
import com.iiplabs.spg.web.views.Views;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RestControllerAnnotation
public class PaymentController {

    private IPaymentService paymentService;

    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @JsonView(Views.Public.class)
    @GetMapping("/payments/{invoice}")
    public ResponseEntity<Collection<Payment>> getPayment(@PathVariable String invoice) {
        Collection<Payment> c = paymentService.findByInvoice(invoice);
        return new ResponseEntity<>(c, c.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping("/payments")
    public ResponseEntity<TransactionResponseDto> addPayment(@Valid @RequestBody PaymentDto paymentDto) {
        paymentService.addPayment(paymentDto);
        return ResponseEntity.ok(new TransactionResponseDto());
    }

}
