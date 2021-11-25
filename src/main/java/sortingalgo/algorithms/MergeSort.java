package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;
import sortingalgo.panels.SubPanel;
import sortingalgo.util.Settings;

import java.util.ArrayList;

public class MergeSort extends SortingAlgorithm {

    public MergeSort()
    {
    }

    @Override
    public void run()
    {
        ArrayList<SubPanel> aux = new ArrayList<>(ArrayPanel.subPanels);
        sort(ArrayPanel.subPanels, aux, 0, ArrayPanel.subPanels.size() - 1);
        ArrayPanel.finishAnimation();
    }

    private void sort(ArrayList<SubPanel> array, ArrayList<SubPanel> aux, int lo, int hi)
    {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(array, aux, lo, mid);
        sort(array, aux, mid + 1, hi);
        merge(array, aux, lo, mid, hi);
    }

    private void merge(ArrayList<SubPanel> array, ArrayList<SubPanel> aux, int lo, int mid, int hi)
    {
        ArrayPanel.delay(Settings.speed.get());
        for (int k = lo; k <= hi; k++) {
            aux.set(k, array.get(k));
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                array.set(k, aux.get(j++));
            } else if (j > hi) {
                array.set(k, aux.get(i++));
            } else if (aux.get(j).getValue() < aux.get(i).getValue()) {
                array.set(k, aux.get(j++));
            } else {
                array.set(k, aux.get(i++));
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

    @Override
    public Integer[] getWorstCase()
    {
        return new Integer[0];
    }

}
