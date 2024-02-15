package main.model;

import java.util.ArrayList;

public class Hand {
    private final ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getHand() {
        return cards;
    }

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
