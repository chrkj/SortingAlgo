import javax.swing.JFrame;
import javax.swing.WindowConstants;

import GUI.SortingGUI;
import Resourses.Constants;

public class SortingAlgo {
    public static void main(String[] args) {
        try {
            // Setting up the GUI:
            SortingGUI gui = new SortingGUI();

            // Setting up the frame to contain the GUI:
            JFrame frame = new JFrame();
            frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        	frame.setTitle("SortingAlgo");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.getContentPane().add(gui);    
        	frame.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
