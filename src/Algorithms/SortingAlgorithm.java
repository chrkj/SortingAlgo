package Algorithms;

import Resourses.ArrayPanel;

public abstract class SortingAlgorithm {

    protected final ArrayPanel arr;

    public SortingAlgorithm(ArrayPanel arr)
    {
        this.arr = arr;
    }

    public abstract void run();

    public abstract String getAlgorithmName();

    public abstract String getTimeComplexity();

}
