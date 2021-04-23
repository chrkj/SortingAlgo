package Algorithms;

import Resourses.Settings;
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
            for (int j = i; j > 0; j--) {
                arr.setColor(i, Color.YELLOW);
                arr.compare(j, j - 1);
                arr.setColor(i, Color.YELLOW);
                if (arr.dataBars.get(j).getHeight() < arr.dataBars.get(j - 1).getHeight()) {
                    arr.swap(j, j - 1);
                    arr.setColor(i, Color.YELLOW);
                    arr.setColor(j, Color.BLACK);
                    arr.setColor(j - 1, Color.BLACK);
                } else {
                    arr.setColor(j, Color.BLACK);
                    arr.setColor(j - 1, Color.BLACK);
                    break;
                }
            }
        }
        arr.setColor(n - 1, Color.BLACK);
        arr.done();
    }

    @Override
    public String getAlgorithmName() {
        return "Insertion Sort";
    }
}
