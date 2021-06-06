package pl.bartlomiejstepien.pong;

public class Pong
{
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_WIDTH = 600;

    public static void main(String[] args)
    {
        MenuWindow menuWindow = new MenuWindow();
        menuWindow.setVisible(true);
        menuWindow.pack();
        menuWindow.setVisible(true);
    }
}
