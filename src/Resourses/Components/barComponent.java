package Resourses.Components;

import java.awt.Color;

import javax.swing.JComponent;

public class barComponent extends JComponent{


    public barComponent(int height, int width) {
        this.setSize(width, height);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }


}
