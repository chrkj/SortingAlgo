package Resourses;

import java.awt.*;
import javax.swing.*;

public class JSpacerComponent extends JPanel {
    public JSpacerComponent() {
        setBackground(Color.WHITE); // To make the spacer invisible on the white background
        setMinimumSize(new Dimension(Settings.BAR_SPACING, 0)); // Height is irrelevant for the spacer since we only care about the width
        setMaximumSize(new Dimension(Settings.BAR_SPACING, 0));
        setAlignmentY(Component.BOTTOM_ALIGNMENT);
    }
}
