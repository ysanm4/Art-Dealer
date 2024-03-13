/*
    Authored by Dustin Yochim
 */

package main.model;

/**
 * Represents a playing card. Contains methods for formatting the card in different ways.
 * @param rank The card's rank.
 * @param suit The card's suit.
 */
public record Card(Rank rank, Suit suit) {
    /**
     * @return A card formatted for logging.
     */
    public String format_card_for_logger() {
        String rankString = format_rank_for_logger();
        String suitString = format_suit_for_logger();
        return rankString + suitString;
    }

    /**
     * @return The file path for the current card.
     */
    public String getImageFilePath() {
        return format_rank_for_image_path() + "_of_" + format_suit_for_image_path() + ".png";
    }


    /**
     * @return The rank of the current card, formatted for logging.
     */
    public String format_rank_for_logger() {
        return switch (rank) {
            case ACE -> "A";
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
            case EIGHT -> "8";
            case NINE -> "9";
            case TEN -> "10";
            case JACK -> "J";
            case QUEEN -> "Q";
            case KING -> "K";
        };
    }

    /**
     * @return The rank of the current card, formatted for the image path.
     */
    public String format_rank_for_image_path() {
        return switch (rank) {
            case ACE -> "14";
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
            case EIGHT -> "8";
            case NINE -> "9";
            case TEN -> "10";
            case JACK -> "11";
            case QUEEN -> "12";
            case KING -> "13";
        };
    }

    /**
     * @return The suit of the current card, formatted for the logger.
     */
    public String format_suit_for_logger() {
        return suit.name().substring(0, 1);
    }

    /**
     * @return The suit of the current card, formatted for the image path.
     */
    private String format_suit_for_image_path() {
        return suit.name().toLowerCase();
    }

    /**
     * @return The suit of the current card.
     */
    public Suit getSuit() {
        return this.suit;
    }
}