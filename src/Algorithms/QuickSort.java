package Algorithms;

import Resourses.arrayPanel;
import Resourses.JBarComponent;

import java.util.ArrayList;

public class QuickSort extends SortingAlgorithm {
    public QuickSort(arrayPanel arr) {
        super(arr);
    }

    @Override
    public void run() {
        sort(arr.dataBars, 0, arr.dataBars.size() - 1);
        arr.done();
    }

    private void sort(ArrayList<JBarComponent> a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private int partition(ArrayList<JBarComponent> a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a.get(lo).getHeight();
        while (true) {
            // find item on lo to swap
            while (a.get(++i).getHeight() < v) {
                arr.compare(i, lo);
                if (i == hi) break;
            }

            // find item on hi to swap
            while (a.get(--j).getHeight() > v) {
                arr.compare(j, lo);
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            arr.swap(i, j);
        }

        // put partitioning item v at a[j]
        arr.swap(lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    @Override
    public String getAlgorithmName() {
        return "Quick Sort";
    }
}
