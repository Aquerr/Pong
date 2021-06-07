package pl.bartlomiejstepien.pong.game;

import pl.bartlomiejstepien.pong.game.objects.Ball;
import pl.bartlomiejstepien.pong.game.objects.GameObject;
import pl.bartlomiejstepien.pong.game.objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

public class PongGame extends JPanel implements KeyListener, Runnable
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int PLAYABLE_HEIGHT = WINDOW_HEIGHT - 50;
    public static final int PLAYABLE_PANEL_START_Y = 50;

    private static final Color BACKGROUND_COLOR = Color.decode("#222222");
    private static final int PLAYER_MOVEMENT_SPEED = 4;

    private final List<GameObject> gameObjects = new LinkedList<>();
    private final Player playerOne;
    private final Player playerTwo;
    private final Ball ball;

    private final Line2D verticalLine = new Line2D.Float(WINDOW_WIDTH / 2, 50, WINDOW_WIDTH / 2, 700);
    private final Line2D horizontalLine = new Line2D.Float(0, 50, WINDOW_WIDTH, 50);

    private boolean isRunning;

    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    private JLabel playerOneScoreLabel = new JLabel(String.valueOf(this.playerOneScore));
    private JLabel playerTwoScoreLabel = new JLabel(String.valueOf(this.playerTwoScore));

    private int secondsLeft = 60;
    private JLabel timerLabel = new JLabel(String.valueOf(secondsLeft));
    private Timer timer = new Timer(1000, this::updateTimerLabel);

    private void updateTimerLabel(ActionEvent actionEvent)
    {
        secondsLeft--;
        timerLabel.setText(String.valueOf(secondsLeft));

        if (secondsLeft <= 0)
        {
            timer.stop();
//            this.getParent().dispatchEvent(new WindowEvent((Window) this.getParent(), WindowEvent.WINDOW_CLOSING));
        }
    }

    private static PongGame INSTANCE;

    public static PongGame getInstance()
    {
        return INSTANCE;
    }

    public PongGame()
    {
        setLayout(null);

        INSTANCE = this;
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setMaximumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.playerOne = new Player(Color.GREEN, 50, WINDOW_HEIGHT/ 2);
        this.playerTwo = new Player(Color.RED, WINDOW_WIDTH - 50, WINDOW_HEIGHT/ 2);
        this.ball = new Ball(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
        gameObjects.add(this.playerOne);
        gameObjects.add(this.playerTwo);
        gameObjects.add(this.ball);
        addKeyListener(this);
        this.setBackground(BACKGROUND_COLOR);
        isRunning = true;
        this.add(playerOneScoreLabel);
        this.add(playerTwoScoreLabel);

        // Setup score labels
        playerOneScoreLabel.setLocation(20, 15);
        playerOneScoreLabel.setForeground(Color.WHITE);
        playerOneScoreLabel.setSize(30, 20);
        playerOneScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        playerTwoScoreLabel.setLocation(760, 15);
        playerTwoScoreLabel.setForeground(Color.WHITE);
        playerTwoScoreLabel.setSize(30, 20);
        playerTwoScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Fire timer
        this.add(timerLabel);
        timerLabel.setLocation((WINDOW_WIDTH / 2) - 13, 15);
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setSize(40 , 20);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timer.start();
    }

    public Player getPlayerOne()
    {
        return playerOne;
    }

    public Player getPlayerTwo()
    {
        return playerTwo;
    }

    public Ball getBall()
    {
        return ball;
    }


    public void addPlayerOneScore()
    {
        this.playerOneScore++;
        this.playerOneScoreLabel.setText(String.valueOf(this.playerOneScore));
    }

    public void addPlayerTwoScore()
    {
        this.playerTwoScore++;
        this.playerTwoScoreLabel.setText(String.valueOf(this.playerTwoScore));
    }

    public int getPlayerOneScore()
    {
        return playerOneScore;
    }

    public int getPlayerTwoScore()
    {
        return playerTwoScore;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.draw(this.verticalLine);
        graphics2D.draw(this.horizontalLine);
//        graphics2D.drawString(String.valueOf(this.playerOneScore), 20, 20);
//        graphics2D.drawString(String.valueOf(this.playerTwoScore), 780, 20);

        for (final GameObject gameObject : gameObjects)
        {
            gameObject.render(graphics2D);
        }
    }

    private void update()
    {
        for (GameObject gameObject : this.gameObjects)
        {
            gameObject.update();
        }
    }

    @Override
    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                //updates++;
                delta--;
            }
            repaint();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
//                System.out.println(frames);
                //updates = 0;
            }
        }
        //stop();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        handlePlayerOneKeyPressed(e);
        handlePlayerTwoKeyPressed(e);
    }

    private void handlePlayerOneKeyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            playerOne.setVelocityY(-PLAYER_MOVEMENT_SPEED);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            playerOne.setVelocityY(PLAYER_MOVEMENT_SPEED);
        }
    }

    private void handlePlayerTwoKeyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            playerTwo.setVelocityY(-PLAYER_MOVEMENT_SPEED);
        }
        else if (e.getKeyCode() == KeyEvent.VK_S)
        {
            playerTwo.setVelocityY(PLAYER_MOVEMENT_SPEED);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        handlePlayerOneKeyReleased(e);
        handlePlayerTwoKeyReleased(e);
    }

    private void handlePlayerTwoKeyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            playerTwo.setVelocityY(0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_S)
        {
            playerTwo.setVelocityY(0);
        }
    }

    private void handlePlayerOneKeyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            playerOne.setVelocityY(0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            playerOne.setVelocityY(0);
        }
    }
}
