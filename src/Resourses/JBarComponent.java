package Resourses;

import java.awt.*;
import javax.swing.*;

public class JBarComponent extends JPanel {
    JLabel dataLabel;

    public JBarComponent(int height) {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(50, height));
        this.setMinimumSize(new Dimension(20, 20));
        this.setMaximumSize(new Dimension(1000, height));
        this.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        this.dataLabel = new JLabel();
        dataLabel.setText(String.valueOf(height));
        dataLabel.setForeground(Color.WHITE);
        this.add(dataLabel);
    }

    public void setHeight(int height) {
        dataLabel.setText(String.valueOf(height));
        this.setMaximumSize(new Dimension(1000, height));
    }

    public void setColor(Color bg) {
        this.setBackground(bg);
    }

}
