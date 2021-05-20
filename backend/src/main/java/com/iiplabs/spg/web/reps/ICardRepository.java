package com.iiplabs.spg.web.reps;

import com.iiplabs.spg.web.model.Card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICardRepository extends JpaRepository<Card, Long>, JpaSpecificationExecutor<Card> {
}
