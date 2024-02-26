/*
 Art Dealer by Dustin Yochim, Ellis Twiggs Jr, Jonathan Schweighauser, Adam Loepker, and Yosef Alqufidi

 This Java program was an attempt to design a program following the Model View Controller design pattern
 discussed here https://www.geeksforgeeks.org/mvc-design-pattern/

 Homework 1
 The program lets you deal out 4 cards where it will show you your hand, and log your hand in a separate file, you
 can continue to draw cards or quit the program.

 Homework 2
 Now the program lets you pick your own cards, rather than the cards being randomly dealt to you. This iteration
 also adds the history of your previous hands to the display on the game screen.
 */

package main;

import main.controller.GameController;
import main.model.Deck;
import main.view.GUI;

/**
 * App is the starting point of the program and where the card Deck, GUI, and GameController will be initialized.
 */
public class App {
    public static void main(String[] args) {
        Deck deck = new Deck(); // initialize deck
        GUI gui = new GUI(); // initialize GUI
        GameController controller = new GameController(deck, gui); // controller will need deck and GUI to control flow
        gui.startApp(); // this starts the GUI
    }
}
