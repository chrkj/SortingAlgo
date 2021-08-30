package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;

public abstract class SortingAlgorithm {

    protected final ArrayPanel arrayPanel;

    public SortingAlgorithm(ArrayPanel arrayPanel)
    {
        this.arrayPanel = arrayPanel;
    }

    public abstract void run();

    public abstract String getAlgorithmName();

    public abstract String getTimeComplexity();

}
