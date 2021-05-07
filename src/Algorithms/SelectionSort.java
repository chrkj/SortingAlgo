package Algorithms;

import Resourses.arrayPanel;

import java.awt.*;

public class SelectionSort extends SortingAlgorithm {

    public SelectionSort(arrayPanel arr) {
        super(arr);
    }

    @Override
    public void run() {
        int n = arr.dataBars.size();
        for (int i = 0; i < n; i++) {
            arr.setColor(i, Color.YELLOW);
            int min = i;
            for (int j = i + 1; j < n; j++) {
                arr.compare(j, min);
                if (arr.getValue(j) < arr.getValue(min)) {
                    arr.setColor(i, Color.YELLOW);
                    min = j;
                }
            }
            arr.swap(i, min);
        }
        arr.done();
    }

    @Override
    public String getAlgorithmName() {
        return "Selection Sort";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n^2)";
    }
}
