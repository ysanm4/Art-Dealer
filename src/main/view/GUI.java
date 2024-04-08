/**
 * Author: Dustin Yochim (unless otherwise specified)
 * Created: 02/2024
 * Description: This class handles the graphical user-interface for the app. The GUI features 3 main screens:
 the Welcome screen, the main Game window, and the Goodbye screen. It uses Java's swing library. Most things
 used in this class were learnt from https://youtu.be/Kmgo00avvEw?si=5o_pSH_PQPu56Ss3
 */

package main.view;

import main.model.Card;
import main.model.Hand;
import main.model.Rank;
import main.model.Suit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * The graphical user-interface for the application.
 */
public class GUI {
    // https://www.w3schools.com/java/java_hashset.asp
    // https://www.geeksforgeeks.org/set-in-java/
    private final Set<Card> selectedCardsSet = new HashSet<>();

    // Data Attributes
    private final JFrame frame;
    private JPanel cardPanel;
    private JPanel previousCards;
    private ActionListener startButtonListener;
    private ActionListener dealButtonListener;
    private ActionListener quitButtonListener;

    /**
     * The constructor is used to create the frame that is used throughout the game.
     */
    public GUI() {
        frame = new JFrame("Art Dealer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
    }


    // Author Yosef Alqufidi. Display message
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }


    // In your GUI.java class
    public void displayLargeMessage(String message) {
        // Customize this method as needed for your application's requirements
        JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }



    /**
     * Starts the game by showing the welcome screen.
     */
    public void startApp() {
        showWelcomeScreen();
    }

    /**
     * Shows the welcome screen containing a title, logo, instructions for playing the game,
     * and a button for continuing.
     */
    private void showWelcomeScreen() {
        JPanel welcomeScreenPanel = new JPanel();
        welcomeScreenPanel.setLayout(new BorderLayout());

        // Description Section
        JLabel gameDescriptionLabel = getjLabel(); // a method below that returns the content of the label
        // gameDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        welcomeScreenPanel.add(gameDescriptionLabel, BorderLayout.NORTH);

        // Welcome, Section
        JLabel welcomeMessageLabel = new JLabel("Welcome to Art Dealer!");
        welcomeMessageLabel.setFont(new Font("Arial", Font.BOLD, 30));
        ImageIcon cardLogo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/cardLogo.png")));
        welcomeMessageLabel.setHorizontalTextPosition(JLabel.CENTER);
        welcomeMessageLabel.setVerticalTextPosition(JLabel.TOP);
        welcomeMessageLabel.setIcon(cardLogo);
        welcomeMessageLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeScreenPanel.add(welcomeMessageLabel, BorderLayout.CENTER);

        // Button Section
        JButton startButton = new JButton("Start Game");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        welcomeScreenPanel.add(buttonPanel, BorderLayout.SOUTH);

        welcomeScreenPanel.setBorder(new EmptyBorder(10, 10, 40, 10));

        // Instead of handling events in the GUI, the flow is returned to the GameController
        startButton.addActionListener(e -> {
            if (startButtonListener != null) {
                startButtonListener.actionPerformed(e);
            }
        });

        /*
        once everything is added to the welcomeScreenPanel, add it to the main frame
         */
        frame.getContentPane().add(welcomeScreenPanel);
        frame.setVisible(true);
    }

    /**
     * @return The instructions for playing the game.
     */
    private static JLabel getjLabel() {
        JLabel descriptionLabel = new JLabel("<html>" +
                 "<body>"
                + "<h2>Welcome to \"The Art Dealer\" Game!</h2>"
                + "<p>In this game, you'll be playing against the computer, known as the Art Dealer. Your goal is to get the Art Dealer to \"buy\" as many cards as possible.</p>"
                + "<h3>Gameplay Instructions:</h3>"
                + "<ol>"
                + "<li><strong>Selection Process:</strong>"
                + "<ul>"
                + "<li>You will pick four cards, one by one.</li>"
                + "<li>Each time you make a selection, the program will display the chosen card immediately.</li>"
                + "<li>If you attempt to pick a card that has already been chosen in the current round, you'll receive an error message and be asked to choose another card.</li>"
                + "</ul>"
                + "</li>"
                + "<li><strong>Round Structure:</strong>"
                + "<ul>"
                + "<li>After you pick four cards, the Art Dealer will make its selection based on a predetermined pattern.</li>"
                + "<li>The Art Dealer will select any red cards (hearts or diamonds) and will not select any black cards (spades or clubs).</li>"
                + "<li>The Art Dealer's selections will be displayed to you, indicating which cards it has chosen.</li>"
                + "<li>You can choose to continue playing more rounds or stop the program altogether.</li>"
                + "</ul>"
                + "</li>"
                + "<li><strong>History Display:</strong>"
                + "<ul>"
                + "<li>All selected cards, along with an indication of which cards were chosen by the Art Dealer, will be added to the history.</li>"
                + "<li>You can view the history, likely in a scrollable window, to keep track of your progress.</li>"
                + "</ul>"
                + "</li>"
                + "</ol>"
                + "<p>Enjoy playing \"The Art Dealer\" game!</p>"
                + "</body>"
                + "</html>");
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        return descriptionLabel;
    }


    /**
     * Creates and displays the main game window.
     */
    public void showGameScreen() {
        JPanel gameScreenPanel = new JPanel(new BorderLayout());

        // Card Panel
        cardPanel = new JPanel(new FlowLayout());
        cardPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        gameScreenPanel.add(cardPanel, BorderLayout.CENTER);
        // Previous Cards
        previousCards = new JPanel();
        JLabel heading = new JLabel("Previous Hands");
        heading.setFont(new Font("Arial", Font.BOLD, 12));
        previousCards.add(heading);
        previousCards.setLayout(new BoxLayout(previousCards, BoxLayout.Y_AXIS));
        previousCards.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane previousCardsScroll = new JScrollPane(previousCards);
        gameScreenPanel.add(previousCardsScroll, BorderLayout.WEST);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton dealButton = new JButton("Pick Cards");
        buttonPanel.add(dealButton);

        JButton quitButton = new JButton("Quit");
        buttonPanel.add(quitButton);

        buttonPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        /*
        Once again, the action listeners for the buttons return flow to the game controller rather than handling them
         directly in the GUI class.
         */
        dealButton.addActionListener(e -> {
            if (dealButtonListener != null) {
                dealButtonListener.actionPerformed(e);
            }
        });

        quitButton.addActionListener(e -> {
            if (quitButtonListener != null) {
                quitButtonListener.actionPerformed(e);
            }
        });

        gameScreenPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().removeAll(); // clear components from previous screens
        frame.getContentPane().add(gameScreenPanel); // Add new components defined in gameScreenPanel
        frame.revalidate(); // Re-validate the frame
        frame.repaint(); // Repaint the frame
    }

    /**
     * Displays a user's "hand" of cards on the screen.
     * @param hand A hand of cards.
     */
    public void displayHand(Hand hand) {
        cardPanel.removeAll(); // remove previous cards
        // loop through the hand and display each card
        for (Card card : hand.getHand()) {
            String imagePath = "/main/resources/PlayingCards/" + card.getImageFilePath();
            // System.out.println(imagePath);
            ImageIcon cardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
            // https://docs.oracle.com/javase/8/docs/api/java/awt/Image.html
            Image scaledImage = cardImage.getImage().getScaledInstance(90, 140, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(resizedIcon);

            // Check if the card was chosen by the dealer
            if (card.getChosenByDealer()) {
                // Add a border to the card if chosen by the dealer
                imageLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            } else {
                // Otherwise, add an empty border
                imageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }

            cardPanel.add(imageLabel);
        }
        cardPanel.revalidate();
        cardPanel.repaint();

    }

    /**
     * Displays the log of "hands" on the screen.
     * @param cards A "hand" of cards formatted for logging.
     */
    public void displayPrevious(String cards){
        // Authored by Ellis Twiggs Jr
        JLabel label = new JLabel(cards);
        label.setBorder(BorderFactory.createEmptyBorder());
        previousCards.add(label);
        previousCards.revalidate();
        previousCards.repaint();
    }

    // Authored by Ellis Twiggs Jr
    /**
     * Display's a window, allowing the user to select which cards to be added to his hand.
     * @return An array of cards to be added to the user's hand.
     */
    public Card[] displayChoice() {
        // Arrays for the suits and ranks
        String[] suits = {"CLUBS", "DIAMONDS", "HEARTS", "SPADES"};
        String[] ranks = {"ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN",
                "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"};
        Card[] userHand = new Card[4];
        Hand hand = new Hand();

        for (int i = 0; i < 4; i++) {
            JComboBox<String> suitComboBox = new JComboBox<>(suits);
            JComboBox<String> rankComboBox = new JComboBox<>(ranks);



            // Create a JPanel to hold the JComboBoxes and labels
            JPanel panel = new JPanel();
            panel.add(new JLabel("Select a Rank:"));
            panel.add(rankComboBox);
            panel.add(new JLabel("Select a Suit:"));
            panel.add(suitComboBox);

            // Show the pop-up window with the drop-down menus
            int result = JOptionPane.showConfirmDialog(null, panel, "Select a Card",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Check if the user made a selection
            if (result == JOptionPane.OK_OPTION) {
                // Get the selected suit and rank
                String selectedRank = (String) rankComboBox.getSelectedItem();
                String selectedSuit = (String) suitComboBox.getSelectedItem();
                // Convert the string to the Suit enum
                // https://www.geeksforgeeks.org/converting-a-string-to-an-enum-in-java/
                try {
                    Suit suitToEnum = Suit.valueOf(selectedSuit);
                    Rank rankToEnum = Rank.valueOf(selectedRank);
                    // https://www.w3schools.com/java/java_classes.asp
                    Card card = new Card(rankToEnum, suitToEnum);
                    // Check if the card is already selected
                    if (!selectedCardsSet.contains(card)) {
                        // Add the selected card to the list and set
                        userHand[i] = card;
                        hand.addCard(card);
                        displayHand(hand);
                        selectedCardsSet.add(card);

                    } else {
                        // Display a message or handle the case where the same card is selected again
                        int alreadyPicked = JOptionPane.showConfirmDialog(null, panel, "You already selected this card. Please try again.",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        i--; // Decrement the loop counter to prompt the user for the same position again
                    }

                    // Add the selected card to the list
                    userHand[i] = new Card(rankToEnum, suitToEnum);
                } catch (IllegalArgumentException e) {
                    System.out.println("There was an error " + e);
                }
            } else {
                System.out.println("User canceled the selection.");
                // Clearing the selected cards
                selectedCardsSet.clear();
                hand.clear();
                displayHand(hand);
                return null;
            }
        }
        selectedCardsSet.clear();
        return userHand;
    }

    /*
    The program's action listeners are defined in the game controller so that they can return the flow to the
    controller upon events. The below methods take in the listener declared in the game controller and apply them to
    the buttons in the GUI.
     */

    /**
     * Applies a listener to the start button.
     * @param listener The start button action listener.
     */
    public void addStartButtonListener (ActionListener listener){
        startButtonListener = listener;
    }

    /**
     * Applies a listener to the deal button.
     * @param listener The deal button action listener.
     */
    public void addDealButtonListener (ActionListener listener){
        dealButtonListener = listener;
    }

    /**
     * Applies a listener to the quit button.
     * @param listener The quit button action listener.
     */
    public void addQuitButtonListener (ActionListener listener){
        quitButtonListener = listener;
    }


    /**
     * Shows a goodbye screen for 5 seconds before terminating the program.
     */
    public void showGoodbyeScreen () {
        // Goodbye Panel Setup
        JPanel goodbyeScreenPanel = new JPanel();
        goodbyeScreenPanel.setLayout(new BorderLayout());

        // Goodbye label setup
        JLabel goodbyeMessageLabel = new JLabel("Thanks for playing Art Dealer!");
        goodbyeMessageLabel.setFont(new Font("Arial", Font.BOLD, 40));
        goodbyeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        goodbyeScreenPanel.add(goodbyeMessageLabel, BorderLayout.CENTER);

        frame.getContentPane().removeAll(); // remove previous content from the frame
        frame.getContentPane().add(goodbyeScreenPanel);
        frame.setVisible(true);

        // Adds a 5-second exitTimer for the goodbye screen to show before terminating the program
        Timer exitTimer = new Timer(5000, e -> System.exit(0));
        exitTimer.setRepeats(false);
        exitTimer.start();
    }

}
