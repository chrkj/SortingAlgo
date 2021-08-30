package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;

public class QuickSort extends SortingAlgorithm {
    public QuickSort(ArrayPanel arrayPanel)
    {
        super(arrayPanel);
    }

    @Override
    public void run()
    {
        sort(0, arrayPanel.array.size() - 1);
        arrayPanel.finishAnimation();
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
        int v = arrayPanel.getValue(lo);
        while (true) {
            while (arrayPanel.getValue(++i) < v) {
                arrayPanel.compare(i, lo);
                if (i == hi) break;
            }

            while (arrayPanel.getValue(--j) > v) {
                arrayPanel.compare(j, lo);
                if (j == lo) break;
            }
            if (i >= j) break;

            arrayPanel.swap(i, j);
        }
        arrayPanel.swap(lo, j);
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
}
