package main;

import main.controller.GameController;
import main.model.Deck;
import main.view.GUI;

public class App {
    public static void main(String[] args) {
        Deck deck = new Deck();
        GUI gui = new GUI();
        GameController controller = new GameController(deck, gui);
        gui.startApp();
    }
}
