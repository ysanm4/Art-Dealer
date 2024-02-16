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
        createAndShowWelcomeScreen();
    }


    /*
    createAndShowWelcomeScreen() establishes a JPanel and adds a description, welcome, and button label to the panel.
     The description panel contains a description and instructions for using the program, the welcome panel is a
     simple label with an image and the name of the program, and the button label contains a button that will exit
     the welcome screen and start the program.
     */
    private void createAndShowWelcomeScreen() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());

        // Description Section
        JLabel descriptionLabel = new JLabel("<html><body>Art Dealer is designed to randomly select and display four " +
                "cards from a standard deck of 52 cards.<br>Each time you click the \"Draw Cards\" button, you will " +
                "be dealt a new set of 4 cards.<br>You can repeat this process as many times as you would like" +
                ".<br>When you are ready to exit the program, simply click the \"Quit\" button.<br>Press " +
                "\"Start\" at the bottom of the screen to begin.<br>Happy drawing!" +
                "</body></html>");
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomePanel.add(descriptionLabel, BorderLayout.NORTH);

        // Welcome, Section
        JLabel welcomeLabel = new JLabel("Welcome to Art Dealer!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/cardLogo.png")));
        welcomeLabel.setHorizontalTextPosition(JLabel.CENTER);
        welcomeLabel.setVerticalTextPosition(JLabel.TOP);
        welcomeLabel.setIcon(image);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        // Button Section
        JButton startButton = new JButton("Start Game");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);

        welcomePanel.setBorder(new EmptyBorder(10, 10, 40, 10));

        // Instead of handling events in the GUI, the flow is returned to the GameController
        startButton.addActionListener(e -> {
            if (startButtonListener != null) {
                startButtonListener.actionPerformed(e);
            }
        });

        /*
        once everything is added to the welcomePanel, add it to the main frame
         */
        frame.getContentPane().add(welcomePanel);
        frame.setVisible(true);
    }



    /*
    showGameScreen is responsible for creating and showing the main game window
     */
    public void showGameScreen() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Card Panel
        cardPanel = new JPanel(new FlowLayout());
        cardPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton dealButton = new JButton("Deal Cards");
        buttonPanel.add(dealButton);

        JButton quitButton = new JButton("Quit");
        buttonPanel.add(quitButton);


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

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().removeAll(); // clear components from previous screens
        frame.getContentPane().add(mainPanel); // Add new components defined in mainPanel
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
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
            Image image = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(image);
            JLabel label = new JLabel(resizedIcon);

            // adds padding between the cards
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            cardPanel.add(label);
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
        JPanel goodbyePanel = new JPanel();
        goodbyePanel.setLayout(new BorderLayout());

        // Goodbye label setup
        JLabel goodbyeLabel = new JLabel("Thanks for playing Art Dealer!");
        goodbyeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        goodbyeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        goodbyePanel.add(goodbyeLabel, BorderLayout.CENTER);

        frame.getContentPane().removeAll(); // remove previous content from the frame
        frame.getContentPane().add(goodbyePanel);
        frame.setVisible(true);

        // Adds a 5 second timer for the goodbye screen to show before terminating the program
        Timer timer = new Timer(5000, e -> System.exit(0));
        timer.setRepeats(false);
        timer.start();
    }
}
