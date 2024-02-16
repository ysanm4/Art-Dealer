/*
The Deck class is used to represent a deck of playing cards. The Deck is made up of Card objects.
The Deck class has methods for shuffling and dealing cards.
 */

package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards; // "cards" represents the deck of cards

    // Constructor
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    // The initializeDeck() method utilizes the Rank and Suit enums to initialize a deck of cards
    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    /* shuffleDeck() uses the Collections class to "shuffle" the deck of cards.
    I found this method here https://stackoverflow.com/a/16112539 */
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    /*
    dealCardsIntoHand() takes in a Hand and an integer representing the number of cards to be drawn.
    The method first shuffles the deck, and then "deals" the given number of cards into the hand.
     */
    public void dealCardsIntoHand(Hand hand, int numCards) {
        this.shuffleDeck();

        /*
        Because the deck is shuffled, there is no need for a random number generator,
        instead the first four cards can just be drawn.
         */
        for (int i = 0; i < numCards; i++) {
            hand.addCard(cards.get(i));
        }
    }
}
