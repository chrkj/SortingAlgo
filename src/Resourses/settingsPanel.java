package Resourses;

import Algorithms.*;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.util.concurrent.ThreadLocalRandom;

public class settingsPanel extends JPanel implements PopupMenuListener {

    private boolean running;
    private SortingAlgorithm selectedAlgorithm;
    private SwingWorker<Void, Void> swingWorker;
    private final JLabel arrayAccesses;
    private final JLabel arrayComparisons;
    private final JLabel barCounter;

    public settingsPanel(arrayPanel arr) {
        running = false;
        selectedAlgorithm = new BubbleSort(arr);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBounds(5, 5, Settings.SETTINGS_WIDTH, Settings.SETTINGS_HEIGHT);

        ////
        // Initializing settings components
        ////

        // Run button
        Button runButton = new Button();
        runButton.setLabel("Run");
        runButton.addActionListener(e -> {
            System.err.println("RunButton pressed!");
            if (!running) {
                System.err.println("{RUN}");
                running = true;
                swingWorker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        System.err.println("SwingWorker initialized.");
                        SortingAlgorithm algorithm = selectedAlgorithm;
                        algorithm.run();
                        return null;
                    }
                };
                Settings.arrayAccesses = 0;
                Settings.arrayComparisons = 0;
                swingWorker.execute();
                runButton.setLabel("Stop");
            } else {
                System.err.println("{STOP}");
                running = false;
                swingWorker.cancel(true);
                System.err.println("SwingWorker canceled");
                runButton.setLabel("Run");
            }
        });

        // Shuffle button
        Button shuffleButton = new Button();
        shuffleButton.setLabel("Shuffle");
        shuffleButton.addActionListener(e -> {
            System.err.println("shuffleButton pressed!");
            arr.shuffle();
        });

        // Run speed slider
        JSlider runSpeedSlider = new JSlider(JSlider.HORIZONTAL, Settings.SPEED_MIN, Settings.SPEED_MAX, Settings.SPEED_INIT);
        // runSpeedSlider.setBackground(Color.LIGHT_GRAY);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(Settings.SPEED_MIN, new JLabel("Fast") );
        labelTable.put(Settings.SPEED_MAX, new JLabel("Slow") );
        runSpeedSlider.setLabelTable(labelTable);
        runSpeedSlider.setPaintLabels(true);
        runSpeedSlider.setInverted(true);
        runSpeedSlider.addChangeListener(e -> {
            System.err.println("Speed: " + runSpeedSlider.getValue() + "ms");
            Settings.speed = runSpeedSlider.getValue();
        });

        // Algorithm selector dropdown
        ArrayList<SortingAlgorithm> algorithms = new ArrayList<>();
        algorithms.add(new BubbleSort(arr));
        algorithms.add(new SelectionSort(arr));
        algorithms.add(new InsertionSort(arr));
        algorithms.add(new MergeSort(arr));
        algorithms.add(new HeapSort(arr));
        algorithms.add(new QuickSort(arr));
        algorithms.add(new RadixSort(arr));

        String[] boxStrings = new String[algorithms.size()];
        for (int i = 0; i < algorithms.size(); i++) {
            boxStrings[i] = algorithms.get(i).getAlgorithmName();
        }
        JComboBox<String> algoSelector = new JComboBox<>(boxStrings);
        algoSelector.setSelectedIndex(0);
        algoSelector.addActionListener(e -> {
            System.err.println("Selected: " + boxStrings[algoSelector.getSelectedIndex()]);
            selectedAlgorithm = algorithms.get(algoSelector.getSelectedIndex());
        });
        algoSelector.addPopupMenuListener(this);

        // Add bar to sorting array button
        Button addBar = new Button();
        addBar.setLabel("Add bar");
        addBar.addActionListener(e -> {
            Settings.barCounter++;
            System.err.println("addButton pressed!");
            int randomHeight = ThreadLocalRandom.current().nextInt(Settings.minBarHeight, Settings.maxBarHeight + 1);
            JBarComponent tmpBar = new JBarComponent(randomHeight);
            arr.dataBars.add(tmpBar);
            repaint();
            arr.reDraw();
        });

        // Remove bar from sorting array button
        Button removeBar = new Button();
        removeBar.setLabel("Remove bar");
        removeBar.addActionListener(e -> {
            System.err.println("removeButton pressed!");
            if (Settings.barCounter > 2) { Settings.barCounter--; }
            if(arr.dataBars.size() > 2) {
                arr.dataBars.remove(arr.dataBars.size() - 1);
            }
            repaint();
            arr.reDraw();
        });

        // Add Array accesses counter
        arrayAccesses = new JLabel();
        arrayAccesses.setText("Array accesses: " + Settings.arrayAccesses);
        arrayAccesses.setForeground(Color.BLACK);

        // Add Array comparisons counter
        arrayComparisons = new JLabel();
        arrayComparisons.setText("Array comparisons: " + Settings.arrayComparisons);
        arrayComparisons.setForeground(Color.BLACK);

        // Add Array comparisons counter
        barCounter = new JLabel();
        arrayComparisons.setText("Number of elements: " + Settings.barCounter);
        arrayComparisons.setForeground(Color.BLACK);

        ////
        // Adding components to settings panel
        ////
        this.add(runButton);
        this.add(runSpeedSlider);
        this.add(algoSelector);
        this.add(shuffleButton);
        this.add(addBar);
        this.add(removeBar);
        this.add(arrayAccesses);
        this.add(arrayComparisons);
        this.add(barCounter);
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) { }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        // Needed to redraw components under the popup menu
        revalidate();
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) { }

    @Override
    public void paintComponent(Graphics g) {
        arrayAccesses.setText("Array accesses: " + Settings.arrayAccesses);
        arrayComparisons.setText("Array comparisons: " + Settings.arrayComparisons);
        barCounter.setText("Number of elements: " + Settings.barCounter);
    }
}
