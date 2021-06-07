package pl.bartlomiejstepien.pong.game.objects;

import java.awt.*;

public abstract class GameObject
{
    protected int x;
    protected int y;
    protected int velocityX;
    protected int velocityY;

    protected GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setVelocityX(int velocityX)
    {
        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY)
    {
        this.velocityY = velocityY;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public abstract void update();

    public abstract void render(Graphics graphics);
}
