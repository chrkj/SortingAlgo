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

public class Controller extends JPanel implements PopupMenuListener {

    private static Controller singleInstance = null;
    private final Button runButton = new Button("Run");

    private Controller()
    {
        setBackground(Settings.SETTINGS_PANEL_COLOR);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setBounds(5, 5, Settings.SETTINGS_PANEL_WIDTH, Settings.SETTINGS_PANEL_HEIGHT);
        Settings.selectedAlgorithm = new BubbleSort(); // Select initial algorithm

        ////
        // Initializing settings components
        ////

        // Reset button
        Button resetButton = new Button("Reset");
        resetButton.addActionListener(e ->
        {
            System.err.println("resetButton pressed!");
            if (!Settings.isRunning.get()) {
                ArrayPanel.reset();
                Settings.arrayAccesses.set(0);
                Settings.arrayComparisons.set(0);
                Settings.currentWorker.cancel(true);
                Settings.currentWorker = new Worker(Settings.selectedAlgorithm);
            }
        });

        // Run button
        Settings.currentWorker = new Worker(Settings.selectedAlgorithm);
        runButton.setPreferredSize(new Dimension(44, 22));
        runButton.addActionListener(e ->
                {
                    System.err.print("RunButton pressed! ");
                    if (!Settings.isRunning.get()) {
                        System.err.println("{RUN}");
                        Settings.isRunning.set(true);
                        Settings.isStepping.set(false);
                        Settings.arrayAccesses.set(0);
                        Settings.arrayComparisons.set(0);
                        resetButton.setEnabled(false);
                        Settings.currentWorker.setAlgorithm(Settings.selectedAlgorithm);
                        Settings.currentWorker.execute();
                    } else {
                        System.err.println("{PAUSE}");
                        Settings.isRunning.set(false);
                        resetButton.setEnabled(true);
                    }
                }
        );

        // Step button
        Button stepButton = new Button("Step");
        stepButton.addActionListener(e ->
                {
                    System.err.println("stepButton pressed!");
                    Settings.isStepping.set(true);
                    Settings.isRunning.set(true);
                    resetButton.setEnabled(true);
                }
        );

        // Shuffle button
        Button shuffleButton = new Button("Shuffle");
        shuffleButton.addActionListener(e ->
                {
                    System.err.println("shuffleButton pressed!");
                    if (!Settings.isRunning.get()) {
                        ArrayPanel.shuffle();
                    }
                }
        );

        // Speed slider
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
                }
        );

        // Add bar button
        Button addBar = new Button("Add bar");
        addBar.addActionListener(e ->
                {
                    System.err.println("addButton pressed!");
                    if (!Settings.isRunning.get()) {
                        Settings.arraySize.getAndIncrement();
                        int randomHeight = ThreadLocalRandom.current().nextInt(Settings.MIN_BAR_HEIGHT, Settings.MAX_BAR_HEIGHT + 1);
                        SubPanel tmpBar = new SubPanel(randomHeight, ArrayPanel.subPanels.size());
                        ArrayPanel.subPanels.add(tmpBar);
                        repaint();
                    }
                }
        );

        // Remove bar button
        Button removeBar = new Button("Remove bar");
        removeBar.addActionListener(e ->
                {
                    System.err.println("removeButton pressed!");
                    if (!Settings.isRunning.get()) {
                        if (Settings.arraySize.get() > 2) {
                            Settings.arraySize.getAndDecrement();
                        }
                        if (ArrayPanel.subPanels.size() > 2) {
                            ArrayPanel.subPanels.remove(ArrayPanel.subPanels.size() - 1);
                        }
                        repaint();
                    }
                }
        );

        // Set values
        Button changeValue = new Button("Change value");
        changeValue.addActionListener(e ->
                {
                    String values = JOptionPane.showInputDialog(this, "Insert values eg. (10 22 43 54 ...)\n" +
                            "Current size is " + ArrayPanel.subPanels.size(), null);
                    if (values != null) {
                        String[] parsedValues = values.split(" ");
                        Integer[] parsedValuesInt = new Integer[parsedValues.length];
                        for (int i = 0; i < parsedValues.length; i++)
                        {
                            parsedValuesInt[i] = Integer.parseInt(parsedValues[i]);
                        }
                        if (parsedValues.length == ArrayPanel.subPanels.size()) {
                            ArrayPanel.setValues(parsedValuesInt);
                        }
                    }
                }
        );

        // Algorithm selector dropdown menu
        ArrayList<SortingAlgorithm> algorithms = new ArrayList<>();
        algorithms.add(new BubbleSort());
        algorithms.add(new SelectionSort());
        algorithms.add(new InsertionSort());
        algorithms.add(new MergeSort());
        algorithms.add(new HeapSort());
        algorithms.add(new QuickSort());

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
                }
        );
        algoSelector.addPopupMenuListener(this);

        // Shuffle worst case
        Button worstCaseShuffle = new Button("Worst case shuffle");
        worstCaseShuffle.addActionListener(e ->
                {
                    if (!Settings.isRunning.get()) {
                        System.err.println("Worst case shuffle pressed!");
                        ArrayPanel.setValues(Settings.selectedAlgorithm.getWorstCase());
                    }
                }
        );

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
        this.add(changeValue);
        this.add(worstCaseShuffle);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (!Settings.isRunning.get()) {
            runButton.setLabel("Run");
            runButton.setBackground(Color.GREEN);
        } else {
            if (!Settings.isStepping.get()) {
                runButton.setLabel("Pause");
                runButton.setBackground(Color.RED);
            }
        }
    }

    public static Controller getInstance()
    {
        if (singleInstance == null) {
            singleInstance = new Controller();
        }
        return singleInstance;
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
