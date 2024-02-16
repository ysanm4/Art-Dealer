package main.view;

import main.model.Card;
import main.model.Hand;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI {

    private final JFrame frame;
    private JPanel cardPanel;

    private ActionListener startButtonListener;
    private ActionListener dealButtonListener;
    private ActionListener quitButtonListener;

    public GUI() {
        frame = new JFrame("Art Dealer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
    }

    public void startApp() {
        createAndShowWelcomeScreen();
    }

    private void createAndShowWelcomeScreen() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());

        // Game description label
        JLabel descriptionLabel = new JLabel("<html><body>Art Dealer is designed to randomly select and display four " +
                "cards from a standard deck of 52 cards.<br>Each time you click the \"Draw Cards\" button, you will " +
                "be dealt a new set of 4 cards.<br>You can repeat this process as many times as you would like" +
                ".<br>When you are ready to exit the program, simply click the \"Quit\" button.<br>Press " +
                "\"Start\" at the bottom of the screen to begin.<br>Happy drawing!" +
                "</body></html>");
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomePanel.add(descriptionLabel, BorderLayout.NORTH);

        // Welcome label with image
        JLabel welcomeLabel = new JLabel("Welcome to Art Dealer!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/cardLogo.png")));
        welcomeLabel.setHorizontalTextPosition(JLabel.CENTER);
        welcomeLabel.setVerticalTextPosition(JLabel.TOP);
        welcomeLabel.setIcon(image);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        // Start game button
        JButton startButton = new JButton("Start Game");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);

        welcomePanel.setBorder(new EmptyBorder(10, 10, 40, 10));

        startButton.addActionListener(e -> {
            if (startButtonListener != null) {
                startButtonListener.actionPerformed(e);
            }
        });

        frame.getContentPane().add(welcomePanel);
        frame.setVisible(true);
    }



    public void showGameScreen() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        cardPanel = new JPanel(new FlowLayout());
        cardPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton dealButton = new JButton("Deal Cards"); // Changed to use class variable
        buttonPanel.add(dealButton);

        dealButton.addActionListener(e -> {
            if (dealButtonListener != null) {
                dealButtonListener.actionPerformed(e);
            }
        });

        JButton quitButton = new JButton("Quit"); // Changed to use class variable
        buttonPanel.add(quitButton);

        quitButton.addActionListener(e -> {
            if (quitButtonListener != null) {
                quitButtonListener.actionPerformed(e);
            }
        });

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().removeAll(); // Remove previous components
        frame.getContentPane().add(mainPanel); // Add new components
        frame.revalidate(); // Re-validate the frame to reflect changes
        frame.repaint(); // Repaint the frame to reflect changes
    }

    public void displayHand(Hand hand) {
        cardPanel.removeAll();
        for (Card card : hand.getHand()) {
            String imagePath = "/main/resources/PlayingCards/" + card.getImageFilePath();
            // System.out.println(imagePath);
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
            Image image = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH); // Adjust width and height as needed
            ImageIcon resizedIcon = new ImageIcon(image);
            JLabel label = new JLabel(resizedIcon);
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add some padding
            cardPanel.add(label);
        }
        cardPanel.revalidate();
        cardPanel.repaint();

    }

    // Methods for Adding Action Listeners
    public void addStartButtonListener(ActionListener listener) {
        startButtonListener = listener;
    }

    public void addDealButtonListener(ActionListener listener) {
        dealButtonListener = listener;
    }

    public void addQuitButtonListener(ActionListener listener) {
        quitButtonListener = listener;
    }

    public void showGoodbyeScreen() {
        JPanel goodbyePanel = new JPanel();
        goodbyePanel.setLayout(new BorderLayout());

        JLabel goodbyeLabel = new JLabel("Thanks for playing Art Dealer!");
        goodbyeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        goodbyeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        goodbyePanel.add(goodbyeLabel, BorderLayout.CENTER);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(goodbyePanel);
        frame.setVisible(true);

        Timer timer = new Timer(5000, e -> System.exit(0));
        timer.setRepeats(false);
        timer.start();

    }
}
