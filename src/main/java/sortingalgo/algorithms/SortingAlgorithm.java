package sortingalgo.algorithms;

public abstract class SortingAlgorithm {

    public SortingAlgorithm()
    {
    }

    public abstract void run();

    public abstract String getAlgorithmName();

    public abstract String getTimeComplexity();

    public abstract Integer[] getWorstCase();

}
