package com.lucascost.flashcards.service;

import com.lucascost.flashcards.entity.Deck;
import com.lucascost.flashcards.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DeckService {

    @Autowired
    private DeckRepository repository;

    public List<Deck> listDecks(){
        return repository.findAll();
    }

    public Deck findById(Integer id){
        Optional<Deck> deck = repository.findById(id);
        return deck.orElse(null);
    }

    public Deck saveDeck(Deck deck){
        try {
            if(deck.getTitle() != null && deck.getTitle().trim().length() > 0){
                repository.save(deck);
                return deck;
            }
        }
        catch (Exception ex){
            System.out.println("Debug "+ex.getMessage());
        }
        return null;
    }

    public Deck updateDeck(Deck deck){
        try {
            if(deck.getId() != null && deck.getTitle() != null && deck.getTitle().trim().length() > 0){
                repository.save(deck);
                return deck;
            }
        }
        catch (Exception ex){
            System.out.println("Debug "+ex.getMessage());
        }
        return null;
    }

    public boolean deleteDeck(Integer deckId){
        Optional<Deck> deck = repository.findById(deckId);
        if (deck.isPresent()){
            repository.delete(deck.get());
            return true;
        }
        return false;
    }

    public List<Deck> listDecksByTitle(String title) {
        return repository.findAllByTitleContainingIgnoreCase(title);
    }
}
