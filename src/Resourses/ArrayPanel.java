package Resourses;

import Controls.ControlFrame;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayPanel extends JPanel {

    public final ControlFrame frame;
    public final ArrayList<JBarComponent> dataBars;

    public ArrayPanel(ControlFrame frame) {
        this.frame = frame;
        this.dataBars = new ArrayList<>(Settings.INITIAL_RECT_COUNT);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBackground(Color.LIGHT_GRAY);

        // Populate dataBars with JBarComponents of random height
        for (int i = 0; i < Settings.INITIAL_RECT_COUNT; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(Settings.MIN_BAR_HEIGHT, Settings.MAX_BAR_HEIGHT + 1);
            dataBars.add(new JBarComponent(randomNum, i));
        }

        // Add JBarComponents and spacers to the ArrayPanel
        add(new JSpacerComponent());
        for (JBarComponent bar : this.dataBars) {
            add(bar);
            add(new JSpacerComponent());
        }
        setSize(new Dimension(100, 100));
    }

    public void shuffle() {
        for (JBarComponent bar : dataBars) {
            bar.setHeight(ThreadLocalRandom.current().nextInt(Settings.MIN_BAR_HEIGHT, Settings.MAX_BAR_HEIGHT + 1));
        }
    }

    // Redraw the GUI based on the date from dataBars
    public void reDraw() {
        removeAll();
        add(new JSpacerComponent());
        for (JBarComponent bar : dataBars ) {
            add(bar);
            add(new JSpacerComponent());
        }
        validate();
        repaint();
        frame.repaint();
    }

    public void delay(long ms) {
        repaint();
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void swap(int firstIndex, int secondIndex) {
        setColor(firstIndex, Color.GREEN);
        setColor(secondIndex, Color.GREEN);
        Settings.arrayAccesses++;
        Collections.swap(dataBars, firstIndex, secondIndex);
        dataBars.get(firstIndex).setIndex(dataBars);
        dataBars.get(secondIndex).setIndex(dataBars);
        delay(Settings.speed);
        setColor(firstIndex, Color.BLACK);
        setColor(secondIndex, Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        reDraw();
    }

    public void done() {
        for (int i = 0; i < dataBars.size(); i++) {
            setColor(i, Color.GREEN);
            delay(25);
            setColor(i, Color.BLACK);
        }
    }

    public void compare(int firstIndex, int secondIndex) {
        Settings.arrayComparisons++;
        setColor(firstIndex, Color.RED);
        setColor(secondIndex, Color.RED);
        delay(Settings.speed);
        setColor(firstIndex, Color.BLACK);
        setColor(secondIndex, Color.BLACK);
    }

    public void setColor(int index, Color color) {
        dataBars.get(index).setBarColor(color);
        if (color == Color.YELLOW || color == Color.GREEN) {
            dataBars.get(index).setLabelColor(Color.BLACK);
        } else {
            dataBars.get(index).setLabelColor(Color.WHITE);
        }
        delay(1);
    }

    public int getValue(int index) {
        return dataBars.get(index).getHeight();
    }
}
