package sortingalgo.algorithms;

import sortingalgo.panels.ArrayPanel;

public class BubbleSort extends SortingAlgorithm {

    public BubbleSort(ArrayPanel arr)
    {
        super(arr);
    }

    @Override
    public void run()
    {
        int n = arr.sortArray.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                arr.compare(j, j - 1);
                if (arr.getValue(j - 1) > arr.getValue(j)) {
                    arr.swap(j - 1, j);
                }
            }
        }
        arr.finishAnimation();
    }

    @Override
    public String getAlgorithmName()
    {
        return "Bubble Sort";
    }

    @Override
    public String getTimeComplexity()
    {
        return "O(n^2)";
    }

}
