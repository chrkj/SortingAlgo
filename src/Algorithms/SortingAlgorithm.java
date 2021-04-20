package Algorithms;

import Resourses.arrayPanel;

public abstract class SortingAlgorithm {

    protected arrayPanel arr;

    public SortingAlgorithm(arrayPanel arr) {
        this.arr = arr;
    }

    public abstract void run();
    public abstract String getAlgorithmName();

}
