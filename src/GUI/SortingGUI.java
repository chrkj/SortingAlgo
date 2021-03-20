package GUI;

import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.JComponent;

public class SortingGUI extends JComponent implements MouseListener {

    private static final long serialVersionUID = -5097077992437305425L;

    public SortingGUI() {
        // ToDo:
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(10, 60, 10, 60);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub 
        System.out.println("Mouse clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse exited");
    }

    
}
