package sortingalgo.panels;

import javax.swing.*;

public class Window extends JFrame
{
    private static Window window;

    public Window(String title)
    {
        window = this;
        setTitle(title);
    }

    public static void update()
    {
        window.repaint();
    }
}
