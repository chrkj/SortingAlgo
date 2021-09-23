package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;
import sortingalgo.util.Settings;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class BubbleSort extends SortingAlgorithm {

    public BubbleSort(ArrayPanel arrayPanel)
    {
        super(arrayPanel);
    }

    @Override
    public void run()
    {
        int n = arrayPanel.array.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                arrayPanel.compare(j, j - 1);
                if (arrayPanel.getValue(j - 1) > arrayPanel.getValue(j)) {
                    arrayPanel.swap(j - 1, j);
                }
            }
        }
        arrayPanel.finishAnimation();
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
