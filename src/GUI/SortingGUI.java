package GUI;

import java.awt.*;
import java.awt.event.*;

import Resourses.Constants;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class SortingGUI extends JComponent implements MouseListener, KeyListener {

    private int[] data;
    private JTextField typingArea;

    private static final long serialVersionUID = -5097077992437305425L;

    public SortingGUI() {
        // Setup the GUI
        data = new int[Constants.rectAmount];
        for (int i = 0; i < data.length; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(Constants.minBarHeight, Constants.maxBarHeight + 1);
            data[i] = randomNum;
        }

        // Add listeners
        addMouseListener(this);

        typingArea = new JTextField(0);
        typingArea.addKeyListener(this);
        add(typingArea, BorderLayout.PAGE_START);

        this.addComponentListener(new ResizeListener());
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < Constants.rectAmount; i++) {
            g.fillRect(Constants.borderMargin + i * Constants.spacing + i * Constants.barLength, Constants.INITIAL_HEIGHT - Constants.borderMargin * 2, Constants.barLength, -data[i]);
            g.drawString(Integer.toString(data[i]), Constants.borderMargin + i * Constants.spacing + i * Constants.barLength + Constants.barLength / 4, Constants.INITIAL_HEIGHT - 85);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO: Make the specific algorithm make a "sorting step" each click
        System.out.println(e.getX() + " " + e.getY());
        for (int i = 0; i < data.length; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(Constants.minBarHeight, Constants.maxBarHeight + 1);
            data[i] = randomNum;
        }
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) { // Not in use 
    }

    @Override
    public void mouseReleased(MouseEvent e) { // Not in use 
    }

    @Override
    public void mouseEntered(MouseEvent e) { // Not in use 
    }

    @Override
    public void mouseExited(MouseEvent e) { // Not in use 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) { // Not in use
    }

    @Override
    public void keyReleased(KeyEvent e) { // Not in use
    }
}
