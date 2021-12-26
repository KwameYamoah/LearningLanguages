package Algorithms.Sorting;

import java.util.List;

public  interface SortingAlgorithm {
    public SortStage sortStage = new SortStage();
    public void sort(List<Integer> unsortedCollection);
    public void sort(List<Integer> unsortedCollection, boolean ascendingOrder);
    public void timeComplexity();
    public void spaceComplexity();
    public SortStage nextStepSort(SortStage sortStage, List<Integer> unsortedCollection);
}
