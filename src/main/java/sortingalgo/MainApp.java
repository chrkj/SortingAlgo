package sortingalgo;

import sortingalgo.util.Settings;
import sortingalgo.panels.ArrayPanel;
import sortingalgo.panels.SettingsPanel;

import javax.swing.*;

public class MainApp {
    private final JFrame window;

    public MainApp()
    {
        window = new JFrame("SortingAlgo");
        ArrayPanel arrayPanel = new ArrayPanel(window);
        SettingsPanel settingsPanel = new SettingsPanel(arrayPanel);
        window.add(settingsPanel);
        window.add(arrayPanel);
    }

    public void start()
    {
        window.setSize(Settings.INITIAL_WINDOW_WIDTH, Settings.INITIAL_WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        } finally {
            new MainApp().start();
        }
    }

}
