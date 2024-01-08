package com.lucascost.flashcards.controller;

import com.lucascost.flashcards.entity.Card;
import com.lucascost.flashcards.entity.Deck;
import com.lucascost.flashcards.service.CardService;
import com.lucascost.flashcards.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("ALL")
@RestController
@RequestMapping("/deck")
public class DeckController {
    @Autowired
    private DeckService service;

    @Autowired
    private CardService cardService;


    @GetMapping("")
    public ResponseEntity<List<Deck>> listAll(@RequestParam(required = false) String query){
        if(query != null)
            return ResponseEntity.ok().body(service.listDecksByTitle(query));

        return ResponseEntity.ok().body(service.listDecks());
    }

    @PostMapping("")
    public ResponseEntity<Deck> saveDeck(@RequestBody Deck deck){
        try{
            deck.setId(null);
            Deck createdDeck = service.saveDeck(deck);
            if (createdDeck != null)
                return ResponseEntity.status(201).body(deck);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<Deck> updateDeck(@RequestBody Deck deck){
        try{
            Deck updateResult = service.updateDeck(deck);
            if(updateResult != null)
                return ResponseEntity.ok().body(deck);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeck(@PathVariable Integer id){
        try{
            if(service.deleteDeck(id))
                return ResponseEntity.noContent().build();

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{deckId}")
    public ResponseEntity<List<Card>> listCards (@PathVariable Integer deckId){
        List<Card> response = cardService.listCardByDeck(deckId);
        if(response != null)
            return ResponseEntity.ok().body(response);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{deckId}")
    public ResponseEntity<Card> updateCard (@PathVariable Integer deckId, @RequestBody Card card){
        Card updateResult = cardService.updateCard(deckId, card);
        if (updateResult != null)
            return ResponseEntity.ok().body(updateResult);
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{deckId}/{card}")
    public ResponseEntity<String> deleteCard(@PathVariable Integer deckId, @PathVariable Integer card){
        Boolean deleteResult = cardService.deleteCard(deckId, card);
        if (deleteResult)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}
