package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;

public class HeapSort extends SortingAlgorithm {

    public HeapSort(ArrayPanel arrayPanel)
    {
        super(arrayPanel);
    }

    @Override
    public void run()
    {
        sort();
        arrayPanel.finishAnimation();
    }

    private void sort()
    {
        int n = arrayPanel.array.size();
        // Heap phase
        for (int k = n / 2; k >= 1; k--) {
            sink(k, n);
        }
        // Sort phase
        int k = n;
        while (k > 1) {
            arrayPanel.swap(0, (k--) - 1);
            sink(1, k);
        }
    }

    private void sink(int k, int n)
    {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && (arrayPanel.getValue(j - 1) < arrayPanel.getValue(j))) {
                arrayPanel.compare(j - 1, j);
                j++;
            }
            if (!(arrayPanel.getValue(k - 1) < arrayPanel.getValue(j - 1))) {
                arrayPanel.compare(k - 1, j - 1);
                break;
            }
            arrayPanel.swap(k - 1, j - 1);
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
