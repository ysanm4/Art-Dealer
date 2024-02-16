/*
The GUI class handles the graphical user-interface for the app. The GUI features 3 main screens: the Welcome screen,
the main Game Window, and the Goodbye screen.
It uses Java's swing library. Most things used in this class I learned from watching this video.
https://youtu.be/Kmgo00avvEw?si=5o_pSH_PQPu56Ss3
 */

package main.view;

import main.model.Card;
import main.model.Hand;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI {

    // Data Attributes
    private final JFrame frame;
    private JPanel cardPanel;
    private ActionListener startButtonListener;
    private ActionListener dealButtonListener;
    private ActionListener quitButtonListener;

    /*
    Constructor is used to create the frame that is used for the entire program.
     */
    public GUI() {
        frame = new JFrame("Art Dealer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
    }

    /*
    startApp() is a simple method that calls the createAndShowWelcomeScreen in order to show the programs welcome screen
     */
    public void startApp() {
        showWelcomeScreen();
    }


    /*
    createAndShowWelcomeScreen() establishes a JPanel and adds a description, welcome, and button label to the panel.
     The description panel contains a description and instructions for using the program, the welcome panel is a
     simple label with an image and the name of the program, and the button label contains a button that will exit
     the welcome screen and start the program.
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

    private static JLabel getjLabel() {
        JLabel descriptionLabel = new JLabel("<html><body><h1>How to Play</h1>Art Dealer is designed to randomly " +
                "select and display four " +
                "cards from a standard deck of 52 cards.<br><br>Each time you click the \"Draw Cards\" button, you " +
                "will " +
                "be dealt a new set of 4 cards.<br>You can repeat this process as many times as you would like" +
                ".<br>When you are ready to exit the program, simply click the \"Quit\" button.<br><br>Press " +
                "\"Start\" at the bottom of the screen to begin.<br><br><strong>Happy drawing!</strong>" +
                "</body></html>");
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        return descriptionLabel;
    }


    /*
    showGameScreen is responsible for creating and showing the main game window
     */
    public void showGameScreen() {
        JPanel welcomeScreenPanel = new JPanel(new BorderLayout());

        // Card Panel
        cardPanel = new JPanel(new FlowLayout());
        cardPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        welcomeScreenPanel.add(cardPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton dealButton = new JButton("Deal Cards");
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

        welcomeScreenPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().removeAll(); // clear components from previous screens
        frame.getContentPane().add(welcomeScreenPanel); // Add new components defined in welcomeScreenPanel
        frame.revalidate(); // Re-validate the frame
        frame.repaint(); // Repaint the frame
    }

    /*
    displayHand takes in a Hand parameter and displays its cards inside the cardPanel.
     */
    public void displayHand(Hand hand) {
        cardPanel.removeAll(); // remove previous cards

        // loop through the hand and display each card
        for (Card card : hand.getHand()) {
            String imagePath = "/main/resources/PlayingCards/" + card.getImageFilePath();
            // System.out.println(imagePath);
            ImageIcon cardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));

            // https://docs.oracle.com/javase/8/docs/api/java/awt/Image.html
            Image scaledImage = cardImage.getImage().getScaledInstance(75, 100, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(resizedIcon);

            // adds padding between the cards
            imageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            cardPanel.add(imageLabel);
        }
        cardPanel.revalidate();
        cardPanel.repaint();

    }

    /*
    The program's action listeners are defined in the game controller so that they can return the flow to the
    controller upon events. The below methods take in the listener declared in the game controller and apply them to
    the buttons in the GUI.
     */
    public void addStartButtonListener(ActionListener listener) {
        startButtonListener = listener;
    }

    public void addDealButtonListener(ActionListener listener) {
        dealButtonListener = listener;
    }

    public void addQuitButtonListener(ActionListener listener) {
        quitButtonListener = listener;
    }

    /*
    Simple goodbye splash screen that shows for 5 seconds before terminating the program.
     */
    public void showGoodbyeScreen() {
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
