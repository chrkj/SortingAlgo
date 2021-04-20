package Algorithms;

import Resourses.sortArray;

public abstract class SortingAlgorithm {

    protected sortArray arr;

    public SortingAlgorithm(sortArray arr) {
        this.arr = arr;
    }

    public abstract void run();

}
