package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;

import java.awt.*;

public class SelectionSort extends SortingAlgorithm {

    public SelectionSort(ArrayPanel arrayPanel)
    {
        super(arrayPanel);
    }

    @Override
    public void run()
    {
        int n = arrayPanel.array.size();
        for (int i = 0; i < n; i++) {
            arrayPanel.setColor(i, Color.YELLOW);
            int min = i;
            for (int j = i + 1; j < n; j++) {
                arrayPanel.compare(j, min);
                if (arrayPanel.getValue(j) < arrayPanel.getValue(min)) {
                    arrayPanel.setColor(i, Color.YELLOW);
                    min = j;
                }
            }
            arrayPanel.swap(i, min);
        }
        arrayPanel.finishAnimation();
    }

    @Override
    public String getAlgorithmName()
    {
        return "Selection Sort";
    }

    @Override
    public String getTimeComplexity()
    {
        return "O(n^2)";
    }
}
