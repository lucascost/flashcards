package com.lucascost.flashcards.service;


import com.lucascost.flashcards.entity.Card;
import com.lucascost.flashcards.entity.Deck;
import com.lucascost.flashcards.repository.CardRepository;
import com.lucascost.flashcards.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private DeckRepository deckRepository;

    public List<Card> listCardByDeck(Integer deckId){
        try {
            Optional<Deck> existingDeck = deckRepository.findById(deckId);
            if(existingDeck.isPresent())
                return cardRepository.findAllByDeck(existingDeck.get());
        }
        catch (Exception ex){
            System.out.println("Debug "+ex.getMessage());
        }
        return null;
    }

    public Card updateCard(Integer deckId, Card card){
        try{
            Optional<Deck> existingDeck = deckRepository.findById(deckId);
            Optional<Card> existingCard = cardRepository.findById(card.getId());

            if(existingDeck.isPresent() && existingCard.isPresent() && card.getId() != null && card.getFront() != null && card.getFront().trim().length() > 0 && card.getBack() != null && card.getBack().trim().length() > 0 ){
                card.setDeck(existingDeck.get());
                cardRepository.save(card);
                return card;
            }
        }
        catch (Exception ex){
            System.out.println("Debug "+ex.getMessage());
        }
        return null;
    }

    public boolean deleteCard(Integer deckId, Integer cardId){
        Optional<Deck> existingDeck = deckRepository.findById(deckId);
        Optional<Card> existingCard = cardRepository.findById(cardId);

        if (existingDeck.isPresent() && existingCard.isPresent()){
            cardRepository.delete(existingCard.get());
            return true;
        }
        return false;
    }
}
