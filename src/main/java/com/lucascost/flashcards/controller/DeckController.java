package com.lucascost.flashcards.controller;

import com.lucascost.flashcards.entity.Deck;
import com.lucascost.flashcards.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/deck")
public class DeckController {
    @Autowired
    private DeckService service;


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
            service.saveDeck(deck);
            return ResponseEntity.status(201).body(deck);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<Deck> updateDeck(@RequestBody Deck deck){
        try{
            service.updateDeck(deck);
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

}
