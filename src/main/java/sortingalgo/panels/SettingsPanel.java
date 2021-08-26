package sortingalgo.panels;

import sortingalgo.algorithms.*;
import sortingalgo.util.Settings;
import sortingalgo.util.Worker;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.util.concurrent.ThreadLocalRandom;

public class SettingsPanel extends JPanel implements PopupMenuListener {

    private final Button runButton;

    public SettingsPanel(ArrayPanel sortArray)
    {
        setBackground(Settings.SETTINGS_PANEL_COLOR);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setBounds(5, 5, Settings.SETTINGS_PANEL_WIDTH, Settings.SETTINGS_PANEL_HEIGHT);
        Settings.selectedAlgorithm = new BubbleSort(sortArray); // Select initial algorithm

        ////
        // Initializing settings components
        ////

        // Run button
        Settings.currentWorker = new Worker(Settings.selectedAlgorithm);
        runButton = new Button("Run");
        runButton.addActionListener(e ->
        {
            System.err.print("RunButton pressed! ");
            if (!Settings.isRunning.get()) {
                System.err.println("{RUN}");
                Settings.isRunning.set(true);
                Settings.isStepping.set(false);
                Settings.currentWorker.setAlgorithm(Settings.selectedAlgorithm);
                Settings.currentWorker.execute();
            } else {
                System.err.println("{PAUSE}");
                Settings.isRunning.set(false);
            }
        });

        // Reset button
        Button resetButton = new Button("Reset");
        resetButton.addActionListener(e ->
        {
            System.err.println("resetButton pressed!");
            if (!Settings.isRunning.get()) {
                sortArray.reset();
                Settings.arrayAccesses.set(0);
                Settings.arrayComparisons.set(0);
                Settings.currentWorker.cancel(true);
                Settings.currentWorker = new Worker(Settings.selectedAlgorithm);
            }
        });

        // Step button
        Button stepButton = new Button("Step");
        stepButton.addActionListener(e ->
        {
            System.err.println("stepButton pressed!");
            Settings.isStepping.set(true);
            Settings.isRunning.set(true);
        });

        // Shuffle button
        Button shuffleButton = new Button("Shuffle");
        shuffleButton.addActionListener(e ->
        {
            System.err.println("shuffleButton pressed!");
            if (!Settings.isRunning.get()) {
                sortArray.shuffle();
            }
        });

        // Run speed slider
        JSlider runSpeedSlider = new JSlider(JSlider.HORIZONTAL, Settings.SPEED_MAX, Settings.SPEED_MIN, Settings.INITIAL_SPEED);
        runSpeedSlider.setBackground(Settings.SETTINGS_PANEL_COLOR);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(Settings.SPEED_MAX, new JLabel("Fast"));
        labelTable.put(Settings.SPEED_MIN, new JLabel("Slow"));
        runSpeedSlider.setLabelTable(labelTable);
        runSpeedSlider.setPaintLabels(true);
        runSpeedSlider.setInverted(true);
        runSpeedSlider.addChangeListener(e ->
        {
            System.err.println("Speed: " + runSpeedSlider.getValue() + "ms");
            Settings.speed.set(runSpeedSlider.getValue());
        });

        // Add bar to sorting array button
        Button addBar = new Button("Add bar");
        addBar.addActionListener(e ->
        {
            System.err.println("addButton pressed!");
            if (!Settings.isRunning.get()) {
                Settings.barCounter.getAndIncrement();
                int randomHeight = ThreadLocalRandom.current().nextInt(Settings.MIN_BAR_HEIGHT, Settings.MAX_BAR_HEIGHT + 1);
                SubPanel tmpBar = new SubPanel(randomHeight, sortArray.sortArray.size());
                sortArray.sortArray.add(tmpBar);
                repaint();
            }
        });

        // Remove bar from sorting array button
        Button removeBar = new Button("Remove bar");
        removeBar.addActionListener(e ->
        {
            System.err.println("removeButton pressed!");
            if (!Settings.isRunning.get()) {
                if (Settings.barCounter.get() > 2) {
                    Settings.barCounter.getAndDecrement();
                }
                if (sortArray.sortArray.size() > 2) {
                    sortArray.sortArray.remove(sortArray.sortArray.size() - 1);
                }
                repaint();
            }
        });

        // Algorithm selector dropdown menu
        ArrayList<SortingAlgorithm> algorithms = new ArrayList<>();
        algorithms.add(new BubbleSort(sortArray));
        algorithms.add(new SelectionSort(sortArray));
        algorithms.add(new InsertionSort(sortArray));
        algorithms.add(new MergeSort(sortArray));
        algorithms.add(new HeapSort(sortArray));
        algorithms.add(new QuickSort(sortArray));

        String[] boxStrings = new String[algorithms.size()];
        for (int i = 0; i < algorithms.size(); i++) {
            boxStrings[i] = algorithms.get(i).getAlgorithmName();
        }
        JComboBox<String> algoSelector = new JComboBox<>(boxStrings);
        algoSelector.setSelectedIndex(0);
        algoSelector.addActionListener(e ->
        {
            System.err.println("Selected: " + boxStrings[algoSelector.getSelectedIndex()]);
            Settings.selectedAlgorithm = algorithms.get(algoSelector.getSelectedIndex());
        });
        algoSelector.addPopupMenuListener(this);

        ////
        // Adding components to settings panel
        ////
        this.add(runButton);
        this.add(resetButton);
        this.add(stepButton);
        this.add(runSpeedSlider);
        this.add(algoSelector);
        this.add(shuffleButton);
        this.add(addBar);
        this.add(removeBar);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(!Settings.isRunning.get()) {
            runButton.setLabel("Run");
        } else {
            runButton.setLabel("Stop");
        }
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e)
    {
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
    {
        revalidate(); // Needed to redraw components under the popup menu
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e)
    {
    }

}
