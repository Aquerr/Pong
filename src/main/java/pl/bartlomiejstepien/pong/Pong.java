package pl.bartlomiejstepien.pong;

public class Pong
{
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_WIDTH = 600;

    public static void main(String[] args)
    {
        MainMenuWindow mainMenuWindow = new MainMenuWindow();
        mainMenuWindow.setVisible(true);
        mainMenuWindow.pack();
        mainMenuWindow.setVisible(true);
    }
}
