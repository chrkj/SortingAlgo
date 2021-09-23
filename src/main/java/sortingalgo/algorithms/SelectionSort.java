package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;
import sortingalgo.util.Settings;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

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

    @Override
    public Integer[] getWorstCase()
    {
        Integer[] worstCaseInt = new Integer[Settings.arraySize.get()];
        for (int i = 0; i < Settings.arraySize.get(); i++)
        {
            worstCaseInt[i] = ThreadLocalRandom.current().nextInt(Settings.MIN_BAR_HEIGHT, Settings.MAX_BAR_HEIGHT + 1);
        }
        Arrays.sort(worstCaseInt, Collections.reverseOrder());
        return worstCaseInt;
    }

}
