package Algorithms;

import Resourses.ArrayPanel;

public class HeapSort extends SortingAlgorithm {

    public HeapSort(ArrayPanel arr)
    {
        super(arr);
    }

    @Override
    public void run()
    {
        sort();
        arr.finishAnimation();
    }

    private void sort()
    {
        int n = arr.dataBars.size();
        // Heap phase
        for (int k = n / 2; k >= 1; k--) {
            sink(k, n);
        }
        // Sort phase
        int k = n;
        while (k > 1) {
            arr.swap(0, (k--) - 1);
            sink(1, k);
        }
    }

    private void sink(int k, int n)
    {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && (arr.getValue(j - 1) < arr.getValue(j))) {
                arr.compare(j - 1, j);
                j++;
            }
            if (!(arr.getValue(k - 1) < arr.getValue(j - 1))) {
                arr.compare(k - 1, j - 1);
                break;
            }
            arr.swap(k - 1, j - 1);
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
}
