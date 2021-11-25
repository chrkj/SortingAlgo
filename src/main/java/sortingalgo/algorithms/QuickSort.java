package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;

public class QuickSort extends SortingAlgorithm {
    public QuickSort()
    {
    }

    @Override
    public void run()
    {
        sort(0, ArrayPanel.subPanels.size() - 1);
        ArrayPanel.finishAnimation();
    }

    private void sort(int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(lo, hi);
        sort(lo, j - 1);
        sort(j + 1, hi);
    }

    private int partition(int lo, int hi)
    {
        int i = lo;
        int j = hi + 1;
        int v = ArrayPanel.getValue(lo);
        while (true) {
            while (ArrayPanel.getValue(++i) < v) {
                ArrayPanel.compare(i, lo);
                if (i == hi) break;
            }

            while (ArrayPanel.getValue(--j) > v) {
                ArrayPanel.compare(j, lo);
                if (j == lo) break;
            }
            if (i >= j) break;

            ArrayPanel.swap(i, j);
        }
        ArrayPanel.swap(lo, j);
        return j;
    }

    @Override
    public String getAlgorithmName()
    {
        return "Quick Sort";
    }

    @Override
    public String getTimeComplexity()
    {
        return "O(n log(n))";
    }

    @Override
    public Integer[] getWorstCase()
    {
        return new Integer[0];
    }

}
