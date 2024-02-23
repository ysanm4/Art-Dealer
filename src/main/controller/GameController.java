/*
GameController handles the flow of the program. In the early stages of the program the controller just handles the
starting, dealing, and ending the program.
 */

package main.controller;

import main.log.File;
import main.model.Card;
import main.model.Deck;
import main.model.Hand;
import main.view.GUI;

public class GameController {

    // deck and gui are initialized in App.java and passed in to the controller's constructor so that the controller
    // can control the flow between the game and the GUI.
    private final Deck deck;
    private final GUI gui;

    final int NUM_CARDS_TO_DEAL = 4;

    /*
    Constructor takes in a Deck and GUI component as parameters
     */
    public GameController(Deck deck, GUI gui) {
        this.deck = deck;
        this.gui = gui;

        // listeners in GUI return flow back here so that we can control the game flow
        this.gui.addStartButtonListener(e -> startGame());
        this.gui.addDealButtonListener(e -> dealCards());
        this.gui.addQuitButtonListener(e -> quitGame());
    }

    /*
    startGame() is called whenever the user clicks the start button in the welcome screen. Once clicked, the main game
    screen is loaded and the log file is opened to prepare for the writing of cards.
    */
    private void startGame() {
        gui.showGameScreen();
        File.openFile();
    }

    /*
    quitGame() is used to end the program. The log file is closed and the goodbye splash screen is displayed.
     */
    private void quitGame() {
        File.closeFile();
        gui.showGoodbyeScreen();
    }

    /*
    dealCards() deals cards into a user's hand and display's the cards in the GUI. It also calls the File.writeToFile
     method in order to log the hand in the external log.
     */
    private void dealCards() {
        // System.out.println("Deal Cards entered");
        Hand hand = new Hand();
       // deck.dealCardsIntoHand(hand, NUM_CARDS_TO_DEAL);
        Card[] userHand = gui.displayChoice();
        for (int i = 0; i < userHand.length; i++){
            hand.addCard(userHand[i]);
        }
        gui.displayHand(hand);
        gui.displayPrevious(hand.format_hand_for_logger());
        File.writeToFile(hand.format_hand_for_logger());
    }
}
