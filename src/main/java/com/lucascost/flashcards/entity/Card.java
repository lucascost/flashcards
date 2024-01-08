package com.lucascost.flashcards.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import static org.hibernate.boot.jaxb.hbm.spi.JaxbHbmOnDeleteEnum.CASCADE;


@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="front", nullable = false)
    private String front;

    @Column(name="back", nullable = false)
    private String back;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_deck", referencedColumnName = "id")
    @JsonIgnore()
    private Deck deck;


    public Card() {
    }

    public Card(Integer id, String front, String back, Deck deck) {
        this.id = id;
        this.front = front;
        this.back = back;
        this.deck = deck;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
