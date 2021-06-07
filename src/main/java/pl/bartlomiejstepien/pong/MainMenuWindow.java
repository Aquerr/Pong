package pl.bartlomiejstepien.pong;

import pl.bartlomiejstepien.pong.game.PongGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuWindow extends JFrame
{
    private static final Color BACKGROUND_COLOR = Color.decode("#222222");
    private static final Color FOREGROUND_COLOR = Color.GREEN;

    private JPanel contentPanel;
    private JPanel mainMenuPanel;
    private JPanel localMenuPanel;
    private JPanel onlineMenuPanel;

    public MainMenuWindow()
    {
        super("Pong");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT));
        setMinimumSize(new Dimension(Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT));
        setMaximumSize(new Dimension(Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT));
        setResizable(false);

        // Open MenuWindow in center of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setupUI();
    }

    private void setupUI()
    {
        this.contentPanel = new JPanel();
        this.contentPanel.setLayout(null);
        setContentPane(contentPanel);
        this.getContentPane().setBackground(BACKGROUND_COLOR);

        setupMainMenu();
        setupLocalMenu();
        setupOnlineMenu();
        showMainMenu(null);
    }

    private void showMainMenu(ActionEvent actionEvent)
    {
        System.out.println("Showing main menu");
        this.getContentPane().removeAll();
        this.getContentPane().add(this.mainMenuPanel);
        revalidate();
        repaint();
    }

    private void showLocalMenu(ActionEvent actionEvent)
    {
        System.out.println("Showing local menu");
        this.contentPanel.removeAll();
        this.contentPanel.add(this.localMenuPanel);
        revalidate();
        repaint();
    }

    private void showOnlineMenu(ActionEvent actionEvent)
    {
        System.out.println("Showing online menu");
        this.contentPanel.removeAll();
        this.contentPanel.add(this.onlineMenuPanel);
        revalidate();
        repaint();
    }

    private void setupLocalMenu()
    {
        this.localMenuPanel = new JPanel();
        localMenuPanel.setBounds(200, 200, 200, 200);
        localMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        localMenuPanel.setBackground(BACKGROUND_COLOR);

        JButton playerVsComputerButtons = new JButton("Player vs Computer");
        playerVsComputerButtons.setPreferredSize(new Dimension(200, 30));
        playerVsComputerButtons.setBackground(BACKGROUND_COLOR);
        playerVsComputerButtons.setForeground(FOREGROUND_COLOR);

        JButton playerVsPlayerButton = new JButton("Player vs Player");
        playerVsPlayerButton.setPreferredSize(new Dimension(200, 30));
        playerVsPlayerButton.setBackground(BACKGROUND_COLOR);
        playerVsPlayerButton.setForeground(FOREGROUND_COLOR);
        playerVsPlayerButton.addActionListener(this::startGamePlayerVsPlayer);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(this::showMainMenu);
        backButton.setPreferredSize(new Dimension(200, 30));
        backButton.setBackground(BACKGROUND_COLOR);
        backButton.setForeground(FOREGROUND_COLOR);

        this.localMenuPanel.add(playerVsComputerButtons);
        this.localMenuPanel.add(Box.createVerticalStrut(20));
        this.localMenuPanel.add(playerVsPlayerButton);
        this.localMenuPanel.add(Box.createVerticalStrut(20));
        this.localMenuPanel.add(backButton);
    }

    private void startGamePlayerVsPlayer(ActionEvent actionEvent)
    {
        PongGame pongGame = new PongGame();
        JFrame frame = new JFrame("PONG");
//        frame.setPreferredSize(new Dimension(800, 600));
//        frame.setMaximumSize(new Dimension(800, 600));
//        frame.setMinimumSize(new Dimension(800, 600));
        frame.setContentPane(pongGame);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
        new Thread(pongGame).start();
    }

    private void setupOnlineMenu()
    {
        this.onlineMenuPanel = new JPanel();
        onlineMenuPanel.setBounds(200, 200, 200, 300);
        onlineMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        onlineMenuPanel.setBackground(BACKGROUND_COLOR);

        JButton hostGameButton = new JButton("Host Game");
        hostGameButton.setPreferredSize(new Dimension(200, 30));
        hostGameButton.setBackground(BACKGROUND_COLOR);
        hostGameButton.setForeground(FOREGROUND_COLOR);

        JButton joinGameButton = new JButton("Join Game");
        joinGameButton.setPreferredSize(new Dimension(200, 30));
        joinGameButton.setBackground(BACKGROUND_COLOR);
        joinGameButton.setForeground(FOREGROUND_COLOR);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 30));
        backButton.addActionListener(this::showMainMenu);
        backButton.setBackground(BACKGROUND_COLOR);
        backButton.setForeground(FOREGROUND_COLOR);

        this.onlineMenuPanel.add(hostGameButton);
        this.onlineMenuPanel.add(Box.createVerticalStrut(20));
        this.onlineMenuPanel.add(joinGameButton);
        this.onlineMenuPanel.add(Box.createVerticalStrut(20));
        this.onlineMenuPanel.add(backButton);
    }

    private void setupMainMenu()
    {
        this.mainMenuPanel = new JPanel();
        mainMenuPanel.setBounds(200, 100, 200, 300);
        mainMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        mainMenuPanel.setBackground(BACKGROUND_COLOR);

        // Pong Label
        JLabel pongLabel = new JLabel("PONG");
        pongLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        pongLabel.setForeground(FOREGROUND_COLOR);

        // Local
        JButton localButton = new JButton("Local");
        localButton.setPreferredSize(new Dimension(200, 30));
        localButton.addActionListener(this::showLocalMenu);
        localButton.setBackground(BACKGROUND_COLOR);
        localButton.setForeground(FOREGROUND_COLOR);

        // Online
        JButton onlineButton = new JButton("Online");
        onlineButton.setPreferredSize(new Dimension(200, 30));
        onlineButton.addActionListener(this::showOnlineMenu);
        onlineButton.setBackground(BACKGROUND_COLOR);
        onlineButton.setForeground(FOREGROUND_COLOR);

        // Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(200, 30));
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setBackground(BACKGROUND_COLOR);
        exitButton.setForeground(FOREGROUND_COLOR);

        mainMenuPanel.add(pongLabel);
        mainMenuPanel.add(Box.createVerticalStrut(80));
        mainMenuPanel.add(localButton);
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(onlineButton);
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(exitButton);
    }
}
