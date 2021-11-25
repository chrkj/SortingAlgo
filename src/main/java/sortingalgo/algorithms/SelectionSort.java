package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;
import sortingalgo.util.Settings;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class SelectionSort extends SortingAlgorithm {

    public SelectionSort()
    {
    }

    @Override
    public void run()
    {
        int n = ArrayPanel.subPanels.size();
        for (int i = 0; i < n; i++) {
            ArrayPanel.setColor(i, Color.YELLOW);
            int min = i;
            for (int j = i + 1; j < n; j++) {
                ArrayPanel.compare(j, min);
                if (ArrayPanel.getValue(j) < ArrayPanel.getValue(min)) {
                    ArrayPanel.setColor(i, Color.YELLOW);
                    min = j;
                }
            }
            ArrayPanel.swap(i, min);
        }
        ArrayPanel.finishAnimation();
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
