package Algorithms;

import Resourses.arrayPanel;

public class HeapSort extends SortingAlgorithm {

    public HeapSort(arrayPanel arr) {
        super(arr);
    }

    @Override
    public void run() {

    }

    @Override
    public String getAlgorithmName() {
        return "Heap Sort";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n log(n))";
    }
}
