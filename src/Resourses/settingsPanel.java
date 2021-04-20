package Resourses;

import Algorithms.BubbleSort;
import Algorithms.SelectionSort;
import Algorithms.SortingAlgorithm;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.util.concurrent.ThreadLocalRandom;

public class settingsPanel extends JPanel implements PopupMenuListener {

    private SortingAlgorithm selectedAlgorithm;

    public settingsPanel(arrayPanel arr) {
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
            System.out.println("RunButton pressed!");
            SwingWorker<Void, Void> swingWorker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    System.out.println("SwingWorker initialized.");
                    SortingAlgorithm algorithm = selectedAlgorithm;
                    algorithm.run();
                    return null;
                }
            };
            swingWorker.execute();
        });

        // Shuffle button
        Button shuffleButton = new Button();
        shuffleButton.setLabel("Shuffle");
        shuffleButton.addActionListener(e -> {
            System.out.println("shuffleButton pressed!");
            arr.shuffle();
        });

        // Run speed slider
        JSlider runSpeedSlider = new JSlider(JSlider.HORIZONTAL, Settings.SPEED_MIN, Settings.SPEED_MAX, Settings.SPEED_INIT);
        runSpeedSlider.setBackground(Color.LIGHT_GRAY);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(Settings.SPEED_MIN, new JLabel("Slow") );
        labelTable.put(Settings.SPEED_MAX, new JLabel("Fast") );
        runSpeedSlider.setLabelTable(labelTable);
        runSpeedSlider.setPaintLabels(true);
        runSpeedSlider.addChangeListener(e -> {
            System.out.println(runSpeedSlider.getValue());
            Settings.speed = runSpeedSlider.getValue();
        });

        // Algorithm selector dropdown
        ArrayList<SortingAlgorithm> algorithms = new ArrayList<>();
        algorithms.add(new BubbleSort(arr));
        algorithms.add(new SelectionSort(arr));
        String[] boxStrings = new String[algorithms.size()];
        for (int i = 0; i < algorithms.size(); i++) {
            boxStrings[i] = algorithms.get(i).getAlgorithmName();
        }
        JComboBox<String> algoSelector = new JComboBox<>(boxStrings);
        algoSelector.setSelectedIndex(0);
        algoSelector.addActionListener(e -> {
            System.out.println(boxStrings[algoSelector.getSelectedIndex()]);
            selectedAlgorithm = algorithms.get(algoSelector.getSelectedIndex());
        });
        algoSelector.addPopupMenuListener(this);

        // Add bar to sorting array button
        Button addBar = new Button();
        addBar.setLabel("Add bar");
        addBar.addActionListener(e -> {
            System.out.println("addBar pressed!");
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
            System.out.println("removeBar pressed!");
            if(arr.dataBars.size() > 2) {
                arr.dataBars.remove(arr.dataBars.size() - 1);
            }
            repaint();
            arr.reDraw();
        });

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
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) { }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        // Needed to redraw components under the popup menu
        revalidate();
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) { }
}
