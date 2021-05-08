package Resourses;

import Controls.ControlFrame;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayPanel extends JPanel {

    public final ControlFrame frame;
    public final ArrayList<JBarComponent> dataBars;

    public ArrayPanel(ControlFrame frame) {
        this.frame = frame;
        this.dataBars = new ArrayList<>(Settings.INITIAL_RECT_COUNT);

        RelativeLayout layout = new RelativeLayout(RelativeLayout.X_AXIS, Settings.BAR_SPACING);
        layout.setFill(true);
        layout.setRoundingPolicy(RelativeLayout.EQUAL);
        layout.setAlignment(RelativeLayout.TRAILING);

        setLayout(layout);
        setBackground(Settings.ARRAY_PANEL_COLOR);

        // Populate dataBars with JBarComponents of random height
        for (int i = 0; i < Settings.INITIAL_RECT_COUNT; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(Settings.MIN_BAR_HEIGHT, Settings.MAX_BAR_HEIGHT + 1);
            dataBars.add(new JBarComponent(randomNum, i));
        }

    }

    public void shuffle() {
        for (JBarComponent bar : dataBars) {
            bar.setHeight(ThreadLocalRandom.current().nextInt(Settings.MIN_BAR_HEIGHT, Settings.MAX_BAR_HEIGHT + 1));
        }
    }

    // Redraw the GUI based on the date from dataBars
    public void reDraw() {
        removeAll();
        for (JBarComponent bar : dataBars ) {
            add(bar, 1f);
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
        Graphics2D panelGraphics = (Graphics2D) g.create();
        try
        {
            Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
            renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            panelGraphics.addRenderingHints(renderingHints);
            g.drawString("Current algorithm: " + Settings.selectedAlgorithm.getAlgorithmName(), 515, 20);
            g.drawString("Time Complexity: " + Settings.selectedAlgorithm.getTimeComplexity(), 515, 40);
            g.drawString("Array accesses: " + Settings.arrayAccesses, 515, 60);
            g.drawString("Array comparisons: " + Settings.arrayComparisons, 515, 80);
            g.drawString("Number of elements: " + Settings.barCounter, 515, 100);
            g.drawString("Speed: " + Settings.speed + " ms", 515, 120);
            reDraw();
        } finally {
            panelGraphics.dispose();
        }
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
        return dataBars.get(index).getValue();
    }
}
