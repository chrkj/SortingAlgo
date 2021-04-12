package Controls;

import Resourses.Settings;
import Resourses.JBarComponent;
import Resourses.JSpacerComponent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class ControlFrame extends JFrame implements MouseListener, KeyListener {

    private final JPanel dataGUI;
    private final ArrayList<JBarComponent> dataBars;

    public ControlFrame(String title) {
        super(title);
        // Get content pane -- contents of the window
        dataBars = new ArrayList<>(Settings.INITIAL_RECT_COUNT);

        dataGUI = initDataGUI();
        // controls = initControls();
        this.add(dataGUI);
        // this.add(controls);

        // pack(); // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the operation that will happen by default when the user initiates a "close" on this frame.
        setVisible(true); // make it show up on screen

        // Adding Listeners
        addKeyListener(this);
        addMouseListener(this);
    }

    // Create the initial BarComponents
    public JPanel initDataGUI() {
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(new BoxLayout(totalGUI, BoxLayout.LINE_AXIS));
        for (int i = 0; i < Settings.INITIAL_RECT_COUNT; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(Settings.minBarHeight, Settings.maxBarHeight + 1);
            dataBars.add(new JBarComponent(randomNum));
        }

        totalGUI.add(new JSpacerComponent());
        for (JBarComponent bar : dataBars ) {
            totalGUI.add(bar);
            totalGUI.add(new JSpacerComponent());
        }

        totalGUI.setOpaque(true);
        this.setSize(new Dimension(100, 100));
        return totalGUI;
    }

    // Redraw the GUI based on the date from dataBars
    public void reDraw() {
        dataGUI.removeAll();
        dataGUI.add(new JSpacerComponent());
        for (JBarComponent bar : dataBars ) {
            dataGUI.add(bar);
            dataGUI.add(new JSpacerComponent());
        }
        validate();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar() + " pressed.");
        if (e.getKeyChar() == '+') {
            for (JBarComponent bar : dataBars) {
                bar.setHeight(bar.getHeight() + 5);
            }
        } else if (e.getKeyChar() == '-') {
            for (JBarComponent bar : dataBars) {
                bar.setHeight(bar.getHeight() - 5);
            }
        } else if (e.getKeyChar() == '1') {
            for (JBarComponent bar : dataBars) {
                bar.setHeight(ThreadLocalRandom.current().nextInt(Settings.minBarHeight, Settings.maxBarHeight + 1));
            }
        } else if (e.getKeyChar() == '2') {
            for (JBarComponent bar : dataBars) {
                bar.setColor(Color.RED);
            }
        } else if (e.getKeyChar() == '3') {
            for (JBarComponent bar : dataBars) {
                bar.setColor(Color.BLACK);
            }
        } else if (e.getKeyChar() == '4') {
            int randomHeight = ThreadLocalRandom.current().nextInt(Settings.minBarHeight, Settings.maxBarHeight + 1);
            JBarComponent tmpBar = new JBarComponent(randomHeight);
            dataBars.add(tmpBar);
            reDraw();
        } else if (e.getKeyChar() == '5') {
            Collections.sort(dataBars);
            reDraw();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("(" + e.getX() + ", " + e.getY() + ") mouse pressed.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public ArrayList<JBarComponent> getDataBars() {
        return this.dataBars;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        new ControlFrame("Control Frame").setSize(Settings.INITIAL_WIDTH, Settings.INITIAL_HEIGHT);
    }

}