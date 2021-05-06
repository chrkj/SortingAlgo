package Algorithms;

import Resourses.JBarComponent;
import Resourses.Settings;
import Resourses.arrayPanel;

import java.awt.*;
import java.util.ArrayList;

public class MergeSort extends SortingAlgorithm {
    public MergeSort(arrayPanel arr) {
        super(arr);
    }

    @Override
    public void run() {
        System.out.println("Merge start");
        ArrayList<JBarComponent> aux = new ArrayList<>(arr.dataBars);
        sort(arr.dataBars, aux, 0, arr.dataBars.size() - 1);
        System.out.println("Done");
    }

    @Override
    public String getAlgorithmName() {
        return "Merge Sort";
    }

    private void sort(ArrayList<JBarComponent> a, ArrayList<JBarComponent> aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private void merge(ArrayList<JBarComponent> a, ArrayList<JBarComponent> aux, int lo, int mid, int hi) {
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
            } else if (aux.get(j).getHeight() <  aux.get(i).getHeight()) {
                a.set(k, aux.get(j++));
            } else {
                a.set(k, aux.get(i++));
            }
        }
    }

}
