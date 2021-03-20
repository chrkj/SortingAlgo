import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import GUI.SortingGUI;

public class SortingAlgo {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    public static void main(String[] args) {
        try {
            // Setting up the GUI:
            SortingGUI gui = new SortingGUI();

            // Setting up the frame to contain the GUI:
            JFrame frame = new JFrame();
            frame.setSize(WIDTH, HEIGHT);
        	frame.setTitle("SortingAlgo");
        	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        	frame.getContentPane().add(gui);    
        	frame.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
