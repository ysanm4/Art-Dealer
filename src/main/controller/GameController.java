/*
    Authored by Dustin Yochim
 */

package main.controller;

import main.log.File;
import main.model.Card;
import main.model.Deck;
import main.model.Hand;
import main.model.Suit;
import main.view.GUI;

public class GameController {
    private final GUI gui;
    private final int NUM_CARDS_TO_DEAL = 4;
    private int currentLevel = 1;
    private boolean patternMatchedOnce = false;



    public GameController(Deck deck, GUI gui) {
        this.gui = gui;
        this.gui.addStartButtonListener(e -> startGame());
        this.gui.addDealButtonListener(e -> dealCards());
        this.gui.addQuitButtonListener(e -> quitGame());
    }

    private void startGame() {
        gui.showGameScreen();
        File.openFile();
    }

    private void dealCards() {
        Hand hand = new Hand();
        Card[] userHand = gui.displayChoice();
        if (userHand != null) {
            for (Card card : userHand) {
                hand.addCard(card);
            }


            boolean patternMatch = false;
            switch (currentLevel) {
                case 1:
                    patternMatch = checkAllRed(userHand);
                    break;
                case 2:
                    patternMatch = checkAllClubs(userHand);
                    break;
                case 3:
                    patternMatch = checkAllFaceCards(userHand);
                    break;
                case 4:
                    patternMatch = checkAllSingleDigits(userHand);
                    break;
                case 5:
                    patternMatch = checkAllSingleDigitPrimes(userHand);
                    break;
                case 6:
                    Card highest = getHighestRank(userHand);
                    patternMatch = true; // In level 6, always matches as you buy the highest rank card
                    hand.clear(); // Clear previous selections
                    hand.addCard(highest); // Add only the highest rank card to hand
                    break;
            }

            if (patternMatch) {
                if (patternMatchedOnce) { // Pattern matched for the second time
                    if (currentLevel < 6) {
                        gui.displayMessage("Level " + currentLevel + " pattern matched twice. Moving to next level!");
                        currentLevel++;
                        patternMatchedOnce = false; // Reset for the next level
                    } else {
                        gui.displayLargeMessage("CONGRATULATIONS YOU BEAT THE GAME");
                        System.exit(0);
                    }
                } else {
                    gui.displayMessage("Pattern matched. Match it once more to advance!");
                    patternMatchedOnce = true; // Mark that the pattern has been matched once
                }
            } else {
                patternMatchedOnce = false; // Reset since the pattern did not match this time
            }

            File.writeToFile(hand.format_hand_for_logger());
            gui.displayHand(hand);
            gui.displayPrevious(hand.format_hand_for_logger());
        }
    }


    private boolean checkAllRed(Card[] userHand) {
        for (Card card : userHand) {
            if (card.getSuit() != Suit.HEARTS && card.getSuit() != Suit.DIAMONDS) {
                return false;
            }
        }
        return true;
    }



    private boolean checkAllClubs(Card[] userHand) {
        for (Card card : userHand) {
            if (card.getSuit() != Suit.CLUBS) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAllFaceCards(Card[] userHand) {
        for (Card card : userHand) {
            switch (card.getRank()) {
                case JACK:
                case QUEEN:
                case KING:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private boolean checkAllSingleDigits(Card[] userHand) {
        for (Card card : userHand) {
            switch (card.getRank()) {
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private boolean checkAllSingleDigitPrimes(Card[] userHand) {
        for (Card card : userHand) {
            switch (card.getRank()) {
                case TWO:
                case THREE:
                case FIVE:
                case SEVEN:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private Card getHighestRank(Card[] userHand) {
        Card highest = userHand[0];
        for (int i = 1; i < userHand.length; i++) {
            if (userHand[i].getRank().compareTo(highest.getRank()) > 0) {
                highest = userHand[i];
            }
        }
        return highest;
    }


    private void quitGame() {
        File.closeFile();
        gui.showGoodbyeScreen();
    }
}
