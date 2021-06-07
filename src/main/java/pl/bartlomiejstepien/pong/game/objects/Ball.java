package pl.bartlomiejstepien.pong.game.objects;

import pl.bartlomiejstepien.pong.game.PongGame;

import java.awt.*;

public class Ball extends GameObject
{
    private static final int WIDTH = 15;
    private static final int BASE_SPEED = 3;

    public Ball(int x, int y)
    {
        super(x, y);
        this.velocityX = 3;
        this.velocityY = 3;
    }

    @Override
    public void update()
    {
        this.x += this.velocityX;
        this.y += this.velocityY;

        handleCollisionWithPlayer();
        changeMovementSide();
    }

    private void handleCollisionWithPlayer()
    {
        Player playerOne = PongGame.getInstance().getPlayerOne();
        Player playerTwo = PongGame.getInstance().getPlayerTwo();

        Player.BarHitSide barHitSidePlayerOne = playerOne.intersectsWithBall(this);
        if (barHitSidePlayerOne != null)
        {
            if (barHitSidePlayerOne == Player.BarHitSide.TOP)
            {
                this.velocityY = -3;
            }
            else if (barHitSidePlayerOne == Player.BarHitSide.MIDDLE)
            {
                this.velocityY = 0;
            }
            else if (barHitSidePlayerOne == Player.BarHitSide.BOTTOM)
            {
                this.velocityY = 3;
            }
            this.velocityX = Math.negateExact(Math.decrementExact(this.velocityX));
        }

        Player.BarHitSide barHitSidePlayerTwo = playerTwo.intersectsWithBall(this);
        if (barHitSidePlayerTwo != null)
        {
            if (barHitSidePlayerTwo == Player.BarHitSide.TOP)
            {
                this.velocityY = -3;
            }
            else if (barHitSidePlayerTwo == Player.BarHitSide.MIDDLE)
            {
                this.velocityY = 0;
            }
            else if (barHitSidePlayerTwo == Player.BarHitSide.BOTTOM)
            {
                this.velocityY = 3;
            }
            this.velocityX = Math.negateExact(Math.incrementExact(this.velocityX));
        }
    }

    private void changeMovementSide()
    {
        if (this.x + WIDTH >= PongGame.WINDOW_WIDTH)
        {
            this.x = PongGame.WINDOW_WIDTH / 2;
            this.y = PongGame.WINDOW_HEIGHT / 2;
            this.velocityX = BASE_SPEED;
            PongGame.getInstance().addPlayerOneScore();
        }
        else if (this.x <= 0)
        {
            this.x = PongGame.WINDOW_WIDTH / 2;
            this.y = PongGame.WINDOW_HEIGHT / 2;
            this.velocityX = -BASE_SPEED;
            PongGame.getInstance().addPlayerTwoScore();
        }

        if (this.y + WIDTH >= PongGame.WINDOW_HEIGHT)
        {
            this.velocityY = -3;
        }
        else if (this.y <= PongGame.PLAYABLE_PANEL_START_Y)
        {
            this.velocityY = 3;
        }
    }

    @Override
    public void render(Graphics graphics)
    {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(x, y, WIDTH, WIDTH);
    }
}
