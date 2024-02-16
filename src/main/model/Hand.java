/*
The Hand class is used to represent a hand of cards. It includes methods for adding cards to the hand, returning the
hand, and formatting the hand for being output to a log,
 */
package main.model;

import java.util.ArrayList;

public class Hand {
    private final ArrayList<Card> cards; // cards represents the user's "hand" of cards.

    // Constructor
    public Hand() {
        cards = new ArrayList<>();
    }

    // Method takes in a Card and adds it to the hand
    public void addCard(Card card) {
        cards.add(card);
    }

    // Getter for the hand
    public ArrayList<Card> getHand() {
        return cards;
    }


    // Hands are formatted in a specific format, the Card class handles the formatting of individual cards while this
    // method handles the formatting of the entire hand.
    public String format_hand_for_logger() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.format_card_for_logger()).append(",");
        }
        if (!sb.isEmpty()) {
            // Remove trailing comma
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
