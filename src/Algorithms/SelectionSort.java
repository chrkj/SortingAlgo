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
                if (arr.dataBars.get(j).getHeight() < arr.dataBars.get(min).getHeight()) {
                    arr.setColor(min, Color.BLACK);
                    arr.setColor(i, Color.YELLOW);
                    min = j;
                    continue;
                }
                arr.setColor(j, Color.BLACK);
            }
            arr.swap(i, min);
            arr.setColor(i, Color.BLACK);
            arr.setColor(min, Color.BLACK);
        }
        arr.done();
    }

    @Override
    public String getAlgorithmName() {
        return "Selection Sort";
    }
}
