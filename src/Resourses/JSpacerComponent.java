package Resourses;

import java.awt.*;
import javax.swing.*;

public class JSpacerComponent extends JPanel {
    public JSpacerComponent() {
        this.setBackground(Color.WHITE); // To make the spacer invisible on the white background
        this.setMinimumSize(new Dimension(Settings.spacing, 0)); // Height is irrelevant for the spacer since we only care about the width
        this.setMaximumSize(new Dimension(Settings.spacing, 0));
        this.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    }
}
