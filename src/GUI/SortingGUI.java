package GUI;

import java.awt.event.*;
import java.awt.Graphics;
import Resourses.Constants;
import javax.swing.JComponent;
import java.util.concurrent.ThreadLocalRandom;

public class SortingGUI extends JComponent implements MouseListener {

    private int spacing = 4;
    private int rectAmount = 25;
    private int borderMargin = 50;
    private int graphLength = Constants.WIDTH - 2 * borderMargin - (rectAmount - 1) * spacing;
    private int barLength = graphLength / rectAmount;
    private int[] data;
    private int minBarHeight = 5;
    private int maxBarHeight = 500;

    private static final long serialVersionUID = -5097077992437305425L;

    public SortingGUI() {
        data = new int[rectAmount];
        for (int i = 0; i < data.length; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(minBarHeight, maxBarHeight + 1);
            data[i] = randomNum;
        }
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < rectAmount; i++) {
            g.fillRect(borderMargin + i * spacing + i * barLength, Constants.HEIGHT - borderMargin * 2, barLength, -data[i]);
            g.drawString(Integer.toString(data[i]), borderMargin + i * spacing + i * barLength + barLength / 4, Constants.HEIGHT - 85);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO: Make the specific algorithm make a "sorting step" each click
        System.out.println(e.getX() + " " + e.getY());
        for (int i = 0; i < data.length; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(minBarHeight, maxBarHeight + 1);
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

    
}
