package pl.bartlomiejstepien.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuWindow extends JFrame
{
    private JPanel contentPanel;
    private JPanel mainMenuPanel;
    private JPanel localMenuPanel;
    private JPanel onlineMenuPanel;

    public MenuWindow()
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
        setupMainMenu();
        setupLocalMenu();
        setupOnlineMenu();
        showMainMenu(null);
    }

    private void setupUI()
    {
        this.contentPanel = new JPanel();
        this.contentPanel.setLayout(null);
        setContentPane(contentPanel);
    }

    private void showMainMenu(ActionEvent actionEvent)
    {
        new Thread(() ->{
            System.out.println("Showing main menu");
            this.getContentPane().removeAll();
            this.getContentPane().add(this.mainMenuPanel);
            revalidate();
            repaint();
        }).start();
    }

    private void showLocalMenu(ActionEvent actionEvent)
    {
        new Thread(() -> {
            System.out.println("Showing local menu");
            this.contentPanel.removeAll();
            this.contentPanel.add(this.localMenuPanel);
            revalidate();
            repaint();
        }).start();
    }

    private void showOnlineMenu(ActionEvent actionEvent)
    {
        new Thread(() -> {
            System.out.println("Showing online menu");
            this.contentPanel.removeAll();
            this.contentPanel.add(this.onlineMenuPanel);
            revalidate();
            repaint();
        });
    }

    private void setupLocalMenu()
    {
        this.localMenuPanel = new JPanel();
        localMenuPanel.setBounds(200, 200, 200, 300);
        localMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));


        JButton playerVsComputerButtons = new JButton("Player vs Computer");

        JButton playerVsPlayerButton = new JButton("Player vs Player");

        JButton backButton = new JButton("Back");
        backButton.addActionListener(this::showMainMenu);

        this.localMenuPanel.add(playerVsComputerButtons);
        this.localMenuPanel.add(Box.createVerticalStrut(20));
        this.localMenuPanel.add(playerVsPlayerButton);
        this.localMenuPanel.add(Box.createVerticalStrut(20));
        this.localMenuPanel.add(backButton);
    }

    private void setupOnlineMenu()
    {
        this.onlineMenuPanel = new JPanel();
        onlineMenuPanel.setBounds(200, 200, 200, 300);
        onlineMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JButton hostGameButton = new JButton("Host Game");

        JButton joinGameButton = new JButton("Join Game");

        JButton backButton = new JButton("Back");
        backButton.addActionListener(this::showMainMenu);

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

        // Pong Label
        JLabel pongLabel = new JLabel("PONG");
        pongLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        // Local
        JButton localButton = new JButton("Local");
        localButton.setPreferredSize(new Dimension(200, 30));
        localButton.addActionListener(this::showLocalMenu);

        // Online
        JButton onlineButton = new JButton("Online");
        onlineButton.setPreferredSize(new Dimension(200, 30));
        onlineButton.addActionListener(this::showOnlineMenu);

        // Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(200, 30));
        exitButton.addActionListener(e -> System.exit(0));

        mainMenuPanel.add(pongLabel);
        mainMenuPanel.add(Box.createVerticalStrut(80));
        mainMenuPanel.add(localButton);
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(onlineButton);
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(exitButton);
    }
}
