package com.iiplabs.spg.web.reps;

import com.iiplabs.spg.web.model.CardHolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICardHolderRepository extends JpaRepository<CardHolder, Long>, JpaSpecificationExecutor<CardHolder> {
}
