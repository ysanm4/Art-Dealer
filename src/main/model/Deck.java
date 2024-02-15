package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public void dealCardsIntoHand(Hand hand, int numCards) {
        this.shuffleDeck();
        for (int i = 0; i < numCards; i++) {
            hand.addCard(cards.get(i));
        }
    }
}
