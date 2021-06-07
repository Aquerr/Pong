package pl.bartlomiejstepien.pong.game.objects;

import pl.bartlomiejstepien.pong.game.PongGame;

import java.awt.*;

public class Player extends GameObject
{
    private static final int HEIGHT = 90;
    private static final int WIDTH = 15;

    private static final int TOP_BAR_END_Y = HEIGHT / 3;
    private static final int MIDDLE_BAR_END_Y = (HEIGHT / 3) * 2;

    private final Color color;

    public Player(Color color, int x, int y)
    {
        super(x, y);
        this.color = color;
    }

    @Override
    public void update()
    {
        this.x += this.velocityX;
        this.y += this.velocityY;

        if (this.y + HEIGHT >= PongGame.WINDOW_HEIGHT)
        {
            this.y = PongGame.WINDOW_HEIGHT - HEIGHT;
        }
        else if (this.y <= PongGame.PLAYABLE_PANEL_START_Y)
        {
            this.y = PongGame.PLAYABLE_PANEL_START_Y;
        }
    }

    @Override
    public void render(Graphics graphics)
    {
        graphics.setColor(color);
        graphics.fillRect(x, y, WIDTH, HEIGHT);
    }

    public BarHitSide intersectsWithBall(Ball ball)
    {
        Rectangle rectangle = new Rectangle(ball.getX(), ball.getY(), 15, 15);
        Rectangle playerRectangle = new Rectangle(this.x, this.y, WIDTH, HEIGHT);

        if (rectangle.intersects(playerRectangle))
        {
            // Check top bar
            if (checkTopBarHit(ball))
            {
                return BarHitSide.TOP;
            }
            else if (checkMiddleBarHit(ball))
            {
                return BarHitSide.MIDDLE;

            }
            else if (checkBottomBarHit(ball))
            {
                return BarHitSide.BOTTOM;
            }
        }

        return null;

        // Check middle bar


        // Check bottom bar



//        return aabb.intersects(AABB.of(Vector3i.of(this.x, this.y), Vector3i.of(this.x + WIDTH, this.y + HEIGHT)));

//        if (x >= this.x && x <= this.x + WIDTH && y >= this.y && y <= this.y + HEIGHT)
//        {
//
//        }
//        return false;
    }

    private boolean checkTopBarHit(Ball ball)
    {
        return ball.getY() >= this.y && ball.getY() <= this.y + TOP_BAR_END_Y;
    }

    private boolean checkMiddleBarHit(Ball ball)
    {
        return ball.getY() >= this.y + TOP_BAR_END_Y && ball.getY() <= this.y + MIDDLE_BAR_END_Y;
    }

    private boolean checkBottomBarHit(Ball ball)
    {
        return ball.getY() >= this.y + MIDDLE_BAR_END_Y && ball.getY() <= this.y + HEIGHT;
    }

    public enum BarHitSide
    {
        TOP, MIDDLE, BOTTOM
    }
}
