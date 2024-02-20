package main.model;

public record Card(Rank rank, Suit suit) {
    public String format_card_for_logger() {
        String rankString = format_rank_for_logger();
        String suitString = format_suit_for_logger();
        return rankString + suitString;
    }

    public String getImageFilePath() {
        return format_rank_for_image_path() + "_of_" + format_suit_for_image_path() + ".png";
    }

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

    public String format_suit_for_logger() {
        return suit.name().substring(0, 1);
    }

    private String format_suit_for_image_path() {
        return suit.name().toLowerCase();
    }
}

