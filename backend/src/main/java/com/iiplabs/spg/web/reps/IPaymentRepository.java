package com.iiplabs.spg.web.reps;

import java.util.Collection;

import com.iiplabs.spg.web.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

public interface IPaymentRepository extends JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {

	@Transactional(readOnly=true)
	Collection<Payment> findByInvoice(String invoice);
	
}
