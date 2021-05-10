package sortingalgo.util;

import sortingalgo.algorithms.SortingAlgorithm;

import javax.swing.*;

public class Worker extends SwingWorker<Void, Void> {
    private final SortingAlgorithm algorithm;

    public Worker(SortingAlgorithm algorithm) {
        super();
        this.algorithm = algorithm;
    }

    @Override
    protected Void doInBackground()
    {
        algorithm.run();
        return null;
    }

}
