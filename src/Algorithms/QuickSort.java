package Algorithms;

import Resourses.arrayPanel;

public class QuickSort extends SortingAlgorithm {
    public QuickSort(arrayPanel arr) {
        super(arr);
    }

    @Override
    public void run() {
        sort(0, arr.dataBars.size() - 1);
        arr.done();
    }

    private void sort(int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(lo, hi);
        sort(lo, j - 1);
        sort(j + 1, hi);
    }

    private int partition(int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = arr.getValue(lo);
        while (true) {
            while (arr.getValue(++i) < v) {
                arr.compare(i, lo);
                if (i == hi) break;
            }

            while (arr.getValue(--j) > v) {
                arr.compare(j, lo);
                if (j == lo) break;
            }
            if (i >= j) break;

            arr.swap(i, j);
        }
        arr.swap(lo, j);
        return j;
    }

    @Override
    public String getAlgorithmName() {
        return "Quick Sort";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n log(n))";
    }
}
