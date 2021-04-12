package Resourses;

import java.awt.*;
import javax.swing.*;

public class JBarComponent extends JPanel implements Comparable<JBarComponent> {
    JLabel dataLabel;

    public JBarComponent(int height) {
        // Setup dimensions of the bar
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(50, height));
        this.setMinimumSize(new Dimension(20, 20));
        this.setMaximumSize(new Dimension(1000, height));
        this.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        // Add the data label to the bar
        this.dataLabel = new JLabel();
        dataLabel.setText(String.valueOf(height));
        dataLabel.setForeground(Color.WHITE);
        this.add(dataLabel);
    }

    public void setHeight(int height) {
        dataLabel.setText(String.valueOf(height)); // Update the data label
        this.setMaximumSize(new Dimension(1000, height));
    }

    public void setColor(Color bg) {
        this.setBackground(bg);
    }

    @Override
    public int compareTo(JBarComponent o) {
        if (this.getHeight() > o.getHeight()) {
            return 1;
        } else if (this.getHeight() < o.getHeight()) {
            return -1;
        }
        return 0;
    }
}
