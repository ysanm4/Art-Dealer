package main.controller;


import main.log.File;
import main.model.Deck;
import main.model.Hand;
import main.view.GUI;

public class GameController {
    private final Deck deck;
    private final GUI gui;

    public GameController(Deck deck, GUI gui) {
        this.deck = deck;
        this.gui = gui;
        this.gui.addStartButtonListener(e -> startGame());
        this.gui.addDealButtonListener(e -> dealCards());
        this.gui.addQuitButtonListener(e -> quitGame());

    }

    private void startGame() {
        gui.showGameScreen();
        File.openFile();
    }

    private void quitGame() {
        File.closeFile();
        gui.showGoodbyeScreen();
    }

    private void dealCards() {
        // System.out.println("Deal Cards entered");
        Hand hand = new Hand();
        deck.dealCardsIntoHand(hand, 4);
        gui.displayHand(hand);
        File.writeToFile(hand.format_hand_for_logger());
    }
}
