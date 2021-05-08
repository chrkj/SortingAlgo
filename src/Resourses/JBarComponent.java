package Resourses;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class JBarComponent extends JPanel {

    private final JLabel dataValueLabel;
    private final JLabel dataIndexLabel;

    public JBarComponent(int height, int index) {
        // Setup dimensions of the bar
        setBackground(Color.BLACK);
        setMinimumSize(new Dimension(20, 20));
        setMaximumSize(new Dimension(1000, height));
        setPreferredSize(new Dimension(50, height));
        setAlignmentY(Component.BOTTOM_ALIGNMENT);
        setLayout(new BorderLayout());

        // Add the data value label to the bar
        dataValueLabel = new JLabel(String.valueOf(height), JLabel.CENTER);
        dataValueLabel.setForeground(Color.WHITE);
        this.add(dataValueLabel, BorderLayout.NORTH);

        // Add the data index label to the bar
        dataIndexLabel = new JLabel(String.valueOf(index), JLabel.CENTER);
        dataIndexLabel.setForeground(Color.GRAY);
        this.add(dataIndexLabel, BorderLayout.SOUTH);
    }

    public void setHeight(int height) {
        dataValueLabel.setText(String.valueOf(height)); // Update the data label
        this.setMaximumSize(new Dimension(1000, height));
    }

    public void setIndex(ArrayList<JBarComponent> arr) {
        dataIndexLabel.setText(String.valueOf(arr.indexOf(this)));
    }

    public void setBarColor(Color color) {
        this.setBackground(color);
    }

    public void setLabelColor(Color color) {
        this.dataValueLabel.setForeground(color);
    }

}
