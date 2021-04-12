import Resourses.Constants;
import Resourses.JBarComponent;
import Resourses.JSpacerComponent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ControlFrame extends JFrame implements MouseListener, KeyListener {

    private JPanel GUI;
    private int rectCount = 5;
    private ArrayList<JBarComponent> dataBars;

    public ControlFrame(String title) {
        super(title);
        // Get content pane -- contents of the window
        dataBars = new ArrayList<>(rectCount);

        GUI = createContentPane();
        this.add(GUI);

        pack(); // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the operation that will happen by default when the user initiates a "close" on this frame.
        setVisible(true); // make it show up on screen

        // Adding Listeners
        addKeyListener(this); // can be added to specific components
        addMouseListener(this);
    }

    // Create the initial BarComponents
    public JPanel createContentPane() {
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(new BoxLayout(totalGUI, BoxLayout.LINE_AXIS));
        for (int i = 0; i < rectCount; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(Constants.minBarHeight, Constants.maxBarHeight + 1);
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
        GUI.removeAll();
        GUI.add(new JSpacerComponent());
        for (JBarComponent bar : dataBars ) {
            GUI.add(bar);
            GUI.add(new JSpacerComponent());
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
                reDraw();
            }
        } else if (e.getKeyChar() == '-') {
            for (JBarComponent bar : dataBars) {
                bar.setHeight(bar.getHeight() - 5);
                reDraw();
            }
        } else if (e.getKeyChar() == '1') {
            for (JBarComponent bar : dataBars) {
                bar.setHeight(ThreadLocalRandom.current().nextInt(Constants.minBarHeight, Constants.maxBarHeight + 1));
                reDraw();
            }
        } else if (e.getKeyChar() == '2') {
            for (JBarComponent bar : dataBars) {
                bar.setColor(Color.RED);
                reDraw();
            }
        } else if (e.getKeyChar() == '3') {
            for (JBarComponent bar : dataBars) {
                bar.setColor(Color.BLACK);
                reDraw();
            }
        } else if (e.getKeyChar() == '4') {
            int height = ThreadLocalRandom.current().nextInt(Constants.minBarHeight, Constants.maxBarHeight + 1);
            JBarComponent tmp = new JBarComponent(height);
            dataBars.add(tmp);
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

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        new ControlFrame("Control Frame").setSize(600, 800); // Temp size
    }

}