package pl.bartlomiejstepien.pong.game;

public class Vector3i
{
    private int x;
    private int y;

    public static Vector3i of(int x, int y)
    {
        return new Vector3i(x, y);
    }

    private Vector3i(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getY()
    {
        return y;
    }

    public int getX()
    {
        return x;
    }
}
