package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;

import java.awt.*;

public class InsertionSort extends SortingAlgorithm {
    public InsertionSort(ArrayPanel arr)
    {
        super(arr);
    }

    @Override
    public void run()
    {
        int n = arr.sortArray.size();
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                arr.setColor(i, Color.YELLOW);
                arr.compare(j, j - 1);
                arr.setColor(i, Color.YELLOW);
                if (arr.getValue(j) < arr.getValue(j - 1)) {
                    arr.swap(j, j - 1);
                    arr.setColor(i, Color.YELLOW);
                } else {
                    break;
                }
            }
        }
        arr.setColor(n - 1, Color.BLACK);
        arr.finishAnimation();
    }

    @Override
    public String getAlgorithmName()
    {
        return "Insertion Sort";
    }

    @Override
    public String getTimeComplexity()
    {
        return "O(n^2)";
    }
}
