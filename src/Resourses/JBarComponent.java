package Resourses;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class JBarComponent extends JPanel {

    private final JPanel labelPanel;
    private final JLabel dataValueLabel;
    private final JLabel dataIndexLabel;

    public JBarComponent(int height, int index) {
        setLayout(new BorderLayout());
        setOpaque(false);

        labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBackground(Color.BLACK);
        labelPanel.setPreferredSize(new Dimension(20, height));
        add(labelPanel, BorderLayout.PAGE_END);

        // Add the data value label to the bar
        dataValueLabel = new JLabel(String.valueOf(height), JLabel.CENTER);
        dataValueLabel.setForeground(Color.WHITE);
        labelPanel.add(dataValueLabel, BorderLayout.NORTH);

        // Add the data index label to the bar
        dataIndexLabel = new JLabel(String.valueOf(index), JLabel.CENTER);
        dataIndexLabel.setForeground(Color.RED);
        labelPanel.add(dataIndexLabel, BorderLayout.SOUTH);
    }

    public void setHeight(int height) {
        dataValueLabel.setText(String.valueOf(height)); // Update the data label
        labelPanel.setPreferredSize(new Dimension(20, height));
    }

    public void setIndex(ArrayList<JBarComponent> arr) {
        dataIndexLabel.setText(String.valueOf(arr.indexOf(this)));
    }

    public void setBarColor(Color color) {
        labelPanel.setBackground(color);
    }

    public void setLabelColor(Color color) {
        this.dataValueLabel.setForeground(color);
    }


    public int getValue() {
        return labelPanel.getHeight();
    }
}
