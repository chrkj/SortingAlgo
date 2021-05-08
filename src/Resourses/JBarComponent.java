package Resourses;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class JBarComponent extends JPanel {

    private final JPanel column;
    private final JLabel valueLabel;
    private final JLabel indexLabel;

    public JBarComponent(int height, int index)
    {
        setOpaque(false);
        setLayout(new BorderLayout());

        // Construct column
        column = new JPanel(new BorderLayout());
        column.setBackground(Color.BLACK);
        column.setPreferredSize(new Dimension(20, height));

        // Add the value label to the bar
        valueLabel = new JLabel(String.valueOf(height), JLabel.CENTER);
        valueLabel.setForeground(Color.WHITE);
        column.add(valueLabel, BorderLayout.NORTH);

        // Add the index label to the bar
        indexLabel = new JLabel(String.valueOf(index), JLabel.CENTER);
        indexLabel.setForeground(Color.RED);
        column.add(indexLabel, BorderLayout.SOUTH);

        add(column, BorderLayout.PAGE_END);
    }

    public void setHeight(int height)
    {
        valueLabel.setText(String.valueOf(height));
        column.setPreferredSize(new Dimension(20, height));
    }

    public void setIndex(ArrayList<JBarComponent> arr)
    {
        indexLabel.setText(String.valueOf(arr.indexOf(this)));
    }

    public void setBarColor(Color color)
    {
        column.setBackground(color);
    }

    public void setLabelColor(Color color)
    {
        this.valueLabel.setForeground(color);
    }

    public int getValue()
    {
        return column.getHeight();
    }
}
