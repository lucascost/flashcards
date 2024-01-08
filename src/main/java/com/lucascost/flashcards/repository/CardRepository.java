package com.lucascost.flashcards.repository;

import com.lucascost.flashcards.entity.Card;
import com.lucascost.flashcards.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findAllByDeck(Deck deck);
}
