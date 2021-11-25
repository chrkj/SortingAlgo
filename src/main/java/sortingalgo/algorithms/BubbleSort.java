package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;
import sortingalgo.util.Settings;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class BubbleSort extends SortingAlgorithm {

    public BubbleSort()
    {
    }

    @Override
    public void run()
    {
        int n = ArrayPanel.subPanels.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                ArrayPanel.compare(j, j - 1);
                if (ArrayPanel.getValue(j - 1) > ArrayPanel.getValue(j)) {
                    ArrayPanel.swap(j - 1, j);
                }
            }
        }
        ArrayPanel.finishAnimation();
    }

    @Override
    public String getAlgorithmName()
    {
        return "Bubble Sort";
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
