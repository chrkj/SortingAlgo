package Algorithms;

import Resourses.arrayPanel;

import java.awt.*;

public class InsertionSort extends SortingAlgorithm {
    public InsertionSort(arrayPanel arr) {
        super(arr);
    }

    @Override
    public void run() {
        int n = arr.dataBars.size();
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && arr.dataBars.get(j).getHeight() < arr.dataBars.get(j - 1).getHeight(); j--) {
                arr.swap(j, j - 1);
                arr.setColor(j, Color.BLACK);
                arr.setColor(j - 1, Color.BLACK);
            }
        }
        arr.done();
    }

    @Override
    public String getAlgorithmName() {
        return "Insertion Sort";
    }
}
