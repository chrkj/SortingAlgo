package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;

public class HeapSort extends SortingAlgorithm {

    public HeapSort()
    {
    }

    @Override
    public void run()
    {
        sort();
        ArrayPanel.finishAnimation();
    }

    private void sort()
    {
        int n = ArrayPanel.subPanels.size();
        // Heap phase
        for (int k = n / 2; k >= 1; k--) {
            sink(k, n);
        }
        // Sort phase
        int k = n;
        while (k > 1) {
            ArrayPanel.swap(0, (k--) - 1);
            sink(1, k);
        }
    }

    private void sink(int k, int n)
    {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && (ArrayPanel.getValue(j - 1) < ArrayPanel.getValue(j))) {
                ArrayPanel.compare(j - 1, j);
                j++;
            }
            if (!(ArrayPanel.getValue(k - 1) < ArrayPanel.getValue(j - 1))) {
                ArrayPanel.compare(k - 1, j - 1);
                break;
            }
            ArrayPanel.swap(k - 1, j - 1);
            k = j;
        }
    }

    @Override
    public String getAlgorithmName()
    {
        return "Heap Sort";
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
