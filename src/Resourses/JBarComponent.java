package Resourses;

import java.awt.*;
import javax.swing.*;

public class JBarComponent extends JPanel {

    private JLabel dataLabel;

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

    public void setBarColor(Color color) {
        this.setBackground(color);
    }

    public void setLabelColor(Color color) {
        this.dataLabel.setForeground(color);
    }

}
