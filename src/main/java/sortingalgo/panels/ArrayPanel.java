package sortingalgo.panels;

import sortingalgo.util.*;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayPanel extends JPanel {

    public final JFrame frame;
    public final ArrayList<SubPanel> array;

    public ArrayPanel(JFrame frame)
    {
        this.frame = frame;
        this.array = new ArrayList<>(Settings.INITIAL_ARRAY_SIZE);

        RelativeLayout layout = new RelativeLayout(RelativeLayout.X_AXIS, Settings.BAR_SPACING);
        layout.setFill(true);
        layout.setRoundingPolicy(RelativeLayout.EQUAL);
        layout.setAlignment(RelativeLayout.TRAILING);

        setLayout(layout);
        setBackground(Settings.ARRAY_PANEL_COLOR);

        // Populate dataBars with JBarComponents of random height
        for (int i = 0; i < Settings.INITIAL_ARRAY_SIZE; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(Settings.MIN_BAR_HEIGHT, Settings.MAX_BAR_HEIGHT + 1);
            array.add(new SubPanel(randomNum, i));
        }
    }

    public void shuffle()
    {
        for (SubPanel bar : array) {
            bar.setHeight(ThreadLocalRandom.current().nextInt(Settings.MIN_BAR_HEIGHT, Settings.MAX_BAR_HEIGHT + 1));
        }
    }

    // Redraw the GUI based on the date from dataBars
    public void reDraw()
    {
        removeAll();
        for (SubPanel element : array) {
            add(element, 1f);
        }
        validate();
        repaint();
        frame.repaint();
    }

    public void delay(long ms)
    {
        repaint();
        try {
            if (Settings.isStepping.get()) {
                Thread.sleep(Settings.STEPPING_SPEED);
            } else {
                Thread.sleep(ms);
            }

            while (!Settings.isRunning.get()) {
                Thread.sleep(10);
            }

            if (Settings.isStepping.get()) {
                Settings.isRunning.set(false);
            }
        } catch (InterruptedException ex) {
            System.err.println("Thread interrupted");
            Thread.currentThread().stop(); // TODO: Fix (deprecated method).
        }
    }

    public void swap(int firstIndex, int secondIndex)
    {
        setColor(firstIndex, Color.GREEN);
        setColor(secondIndex, Color.GREEN);
        delay(Settings.speed.get());
        Settings.arrayAccesses.getAndIncrement();
        Collections.swap(array, firstIndex, secondIndex);
        array.get(firstIndex).setIndex(array);
        array.get(secondIndex).setIndex(array);
        delay(Settings.speed.get());
        setColor(firstIndex, Color.BLACK);
        setColor(secondIndex, Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();
        try {
            Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
            renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.addRenderingHints(renderingHints);
            g2D.setFont(new Font("SansSerif", Font.PLAIN, Settings.SETTINGS_PANEL_TEXT_SIZE));
            g2D.drawString("Current algorithm: " + Settings.selectedAlgorithm.getAlgorithmName(), 515, 20);
            g2D.drawString("Time Complexity: " + Settings.selectedAlgorithm.getTimeComplexity(), 515, 40);
            g2D.drawString("Array accesses: " + Settings.arrayAccesses, 515, 60);
            g2D.drawString("Array comparisons: " + Settings.arrayComparisons, 515, 80);
            g2D.drawString("Number of elements: " + Settings.arraySize, 515, 100);
            g2D.drawString("Speed: " + Settings.speed + " ms", 515, 120);
            reDraw();
        } finally {
            g2D.dispose();
        }
    }

    public void finishAnimation()
    {
        for (int i = 0; i < array.size(); i++) {
            setColor(i, Color.GREEN);
            delay(25);
            setColor(i, Color.BLACK);
        }
        Settings.isRunning.set(false);
        Settings.isStepping.set(false);
        Settings.currentWorker.cancel(true);
        Settings.currentWorker = new Worker(Settings.selectedAlgorithm);
    }

    public void compare(int firstIndex, int secondIndex)
    {
        Settings.arrayComparisons.getAndIncrement();
        setColor(firstIndex, Color.RED);
        setColor(secondIndex, Color.RED);
        delay(Settings.speed.get());
        setColor(firstIndex, Color.BLACK);
        setColor(secondIndex, Color.BLACK);
    }

    public void setColor(int index, Color color)
    {
        array.get(index).setBarColor(color);
    }

    public int getValue(int index)
    {
        return array.get(index).getValue();
    }

    public void reset()
    {
        for (SubPanel panel : array) {
            panel.setBarColor(Color.BLACK);
        }
    }

    public void setValues(Integer[] values)
    {
        for (int i = 0; i < values.length; i++) {
            array.get(i).setHeight(values[i]);
        }
    }

}
