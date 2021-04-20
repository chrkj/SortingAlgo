package Resourses;

import Controls.ControlFrame;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class sortArray extends JPanel {

    public ArrayList<JBarComponent> dataBars;
    public ControlFrame frame;

    public sortArray(ControlFrame frame) {
        this.frame = frame;
        this.dataBars = new ArrayList<>(Settings.INITIAL_RECT_COUNT);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        for (int i = 0; i < Settings.INITIAL_RECT_COUNT; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(Settings.minBarHeight, Settings.maxBarHeight + 1);
            this.dataBars.add(new JBarComponent(randomNum));
        }
        add(new JSpacerComponent());
        for (JBarComponent bar : this.dataBars) {
            add(bar);
            add(new JSpacerComponent());
        }
        setOpaque(true);
        setSize(new Dimension(100, 100));
    }

    public void shuffle() {
        for (JBarComponent bar : dataBars) {
            bar.setHeight(ThreadLocalRandom.current().nextInt(Settings.minBarHeight, Settings.maxBarHeight + 1));
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
        dataBars.get(firstIndex).setColor(Color.GREEN);
        dataBars.get(secondIndex).setColor(Color.GREEN);
        Collections.swap(dataBars, firstIndex, secondIndex);
        delay(Settings.speed);
    }

    @Override
    public void paintComponent(Graphics g) {
        reDraw();
    }

    public void done() {
        for (JBarComponent dataBar : dataBars) {
            dataBar.setColor(Color.GREEN);
            delay(50);
            dataBar.setColor(Color.BLACK);
        }
    }

    public void compare(int firstIndex, int secondIndex) {
        dataBars.get(firstIndex).setColor(Color.RED);
        dataBars.get(secondIndex).setColor(Color.RED);
        delay(Settings.speed);
    }
}
