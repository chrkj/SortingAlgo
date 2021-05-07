package Algorithms;

import Resourses.arrayPanel;

public abstract class SortingAlgorithm {

    protected final arrayPanel arr;

    public SortingAlgorithm(arrayPanel arr) {
        this.arr = arr;
    }

    public abstract void run();
    public abstract String getAlgorithmName();
    public abstract String getTimeComplexity();

}
