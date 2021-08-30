package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;

import java.awt.*;

public class InsertionSort extends SortingAlgorithm {
    public InsertionSort(ArrayPanel arrayPanel)
    {
        super(arrayPanel);
    }

    @Override
    public void run()
    {
        int n = arrayPanel.array.size();
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                arrayPanel.setColor(i, Color.YELLOW);
                arrayPanel.compare(j, j - 1);
                arrayPanel.setColor(i, Color.YELLOW);
                if (arrayPanel.getValue(j) < arrayPanel.getValue(j - 1)) {
                    arrayPanel.swap(j, j - 1);
                    arrayPanel.setColor(i, Color.YELLOW);
                } else {
                    break;
                }
            }
        }
        arrayPanel.setColor(n - 1, Color.BLACK);
        arrayPanel.finishAnimation();
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
