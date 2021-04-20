package Controls;

import Resourses.*;
import javax.swing.*;

public class ControlFrame extends JFrame {

    public ControlFrame(String title) {
        super(title);

        arrayPanel dataGUI = new arrayPanel(this);
        settingsPanel settings = new settingsPanel(dataGUI);
        this.add(settings);
        this.add(dataGUI);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        new ControlFrame("Sorting Visualizer").setSize(Settings.INITIAL_WIDTH, Settings.INITIAL_HEIGHT);
    }

}