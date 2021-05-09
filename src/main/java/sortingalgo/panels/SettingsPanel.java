package sortingalgo.panels;

import sortingalgo.algorithms.*;
import sortingalgo.util.Settings;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.util.concurrent.ThreadLocalRandom;

public class SettingsPanel extends JPanel implements PopupMenuListener {

    private boolean running;
    private SwingWorker<Void, Void> swingWorker;

    public SettingsPanel(ArrayPanel arr)
    {
        running = false;
        Settings.selectedAlgorithm = new BubbleSort(arr); // Select initial algorithm
        setBackground(Settings.SETTINGS_PANEL_COLOR);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setBounds(5, 5, Settings.SETTINGS_PANEL_WIDTH, Settings.SETTINGS_PANEL_HEIGHT);

        ////
        // Initializing settings components
        ////

        // Run button
        Button runButton = new Button();
        runButton.setLabel("Run");
        runButton.addActionListener(e ->
        {
            System.err.println("RunButton pressed!");
            if (!running) {
                System.err.println("{RUN}");
                running = true;
                swingWorker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground()
                    {
                        System.err.println("SwingWorker initialized.");
                        SortingAlgorithm algorithm = Settings.selectedAlgorithm;
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
        shuffleButton.addActionListener(e ->
        {
            System.err.println("shuffleButton pressed!");
            arr.shuffle();
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
            Settings.speed = runSpeedSlider.getValue();
        });

        // Add bar to sorting array button
        Button addBar = new Button();
        addBar.setLabel("Add bar");
        addBar.addActionListener(e ->
        {
            Settings.barCounter++;
            System.err.println("addButton pressed!");
            int randomHeight = ThreadLocalRandom.current().nextInt(Settings.MIN_BAR_HEIGHT, Settings.MAX_BAR_HEIGHT + 1);
            SubPanel tmpBar = new SubPanel(randomHeight, arr.dataBars.size());
            arr.dataBars.add(tmpBar);
            repaint();
        });

        // Remove bar from sorting array button
        Button removeBar = new Button();
        removeBar.setLabel("Remove bar");
        removeBar.addActionListener(e ->
        {
            System.err.println("removeButton pressed!");
            if (Settings.barCounter > 2) {
                Settings.barCounter--;
            }
            if (arr.dataBars.size() > 2) {
                arr.dataBars.remove(arr.dataBars.size() - 1);
            }
            repaint();
        });

        // Algorithm selector dropdown menu
        ArrayList<SortingAlgorithm> algorithms = new ArrayList<>();
        algorithms.add(new BubbleSort(arr));
        algorithms.add(new SelectionSort(arr));
        algorithms.add(new InsertionSort(arr));
        algorithms.add(new MergeSort(arr));
        algorithms.add(new HeapSort(arr));
        algorithms.add(new QuickSort(arr));

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
        this.add(runSpeedSlider);
        this.add(algoSelector);
        this.add(shuffleButton);
        this.add(addBar);
        this.add(removeBar);
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e)
    {
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
    {
        // Needed to redraw components under the popup menu
        revalidate();
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e)
    {
    }

}
