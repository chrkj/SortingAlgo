package Algorithms;

import Resourses.arrayPanel;

import java.awt.*;

public class BubbleSort extends SortingAlgorithm {

    public BubbleSort(arrayPanel arr) {
        super(arr);
    }

    @Override
    public void run() {
        int n = arr.dataBars.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                arr.compare(j, j - 1);
                if (arr.dataBars.get(j - 1).getHeight() > arr.dataBars.get(j).getHeight()) {
                    //swap elements
                    arr.swap(j - 1, j);
                }
                arr.dataBars.get(j - 1).setColor(Color.BLACK);
                arr.dataBars.get(j).setColor(Color.BLACK);
            }
        }
        arr.done();
    }

}
