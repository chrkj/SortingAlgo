package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;
import sortingalgo.panels.SubPanel;
import sortingalgo.util.Settings;

import java.util.ArrayList;

public class MergeSort extends SortingAlgorithm {

    public MergeSort(ArrayPanel arr)
    {
        super(arr);
    }

    @Override
    public void run()
    {
        ArrayList<SubPanel> aux = new ArrayList<>(arr.sortArray);
        sort(arr.sortArray, aux, 0, arr.sortArray.size() - 1);
    }

    private void sort(ArrayList<SubPanel> a, ArrayList<SubPanel> aux, int lo, int hi)
    {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private void merge(ArrayList<SubPanel> a, ArrayList<SubPanel> aux, int lo, int mid, int hi)
    {
        arr.delay(Settings.speed);
        for (int k = lo; k <= hi; k++) {
            aux.set(k, a.get(k));
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a.set(k, aux.get(j++));
            } else if (j > hi) {
                a.set(k, aux.get(i++));
            } else if (aux.get(j).getValue() < aux.get(i).getValue()) {
                a.set(k, aux.get(j++));
            } else {
                a.set(k, aux.get(i++));
            }
        }
    }

    @Override
    public String getAlgorithmName()
    {
        return "Merge Sort";
    }

    @Override
    public String getTimeComplexity()
    {
        return "O(n log(n))";
    }

}
