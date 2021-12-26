package Algorithms.Sorting;

import java.util.List;

public class BubbleSort implements SortingAlgorithm {
    @Override
    public void sort(List<Integer> unsortedCollection) {
        sortStage.reset();
        sort(unsortedCollection, true);
    }

    @Override
    public void sort(List<Integer> unsortedCollection, boolean ascendingOrder) {
        if (unsortedCollection.size() == 0 || unsortedCollection.size() == 1) return;
        int swapCounter = sortStage.getCounter();
        for (int i = sortStage.getI(); i < unsortedCollection.size() - 1; i++) {
            for (int j = sortStage.getJ(); j < unsortedCollection.size() - (i + 1); j++) {
                sortStage.setCurrentTargets(j, j + 1);
                if (ascendingOrder) {
                    if (unsortedCollection.get(j) > unsortedCollection.get(j + 1)) {
                        swapCounter++;
                        int temp = unsortedCollection.get(j);
                        unsortedCollection.set(j, unsortedCollection.get(j + 1));
                        unsortedCollection.set(j + 1, temp);
                    }
                } else {
                    if (unsortedCollection.get(j) < unsortedCollection.get(j + 1)) {
                        swapCounter++;
                        int temp = unsortedCollection.get(j);
                        unsortedCollection.set(j, unsortedCollection.get(j + 1));
                        unsortedCollection.set(j + 1, temp);
                    }
                }
                if (sortStage.isStarted()) {
                    sortStage.setJ(j + 1);
                    if (j == unsortedCollection.size() - (i + 2)) {
                        if(sortStage.getCounter()==0){
                            sortStage.setSorted(true);
                            return;
                        }
                        sortStage.setI(i + 1);
                        sortStage.setJ(0);
                    }
                    if (i == unsortedCollection.size() - 2) sortStage.setSorted(true);
                    sortStage.setCounter(sortStage.getCounter()+swapCounter);
                    return;
                }

            }
            if (swapCounter == 0) {
                return;
            } else {
                swapCounter = 0;
                sortStage.setCounter(0);
            }
        }
    }


    @Override
    public void timeComplexity() {
        System.out.println("Big O notation");
        System.out.println("Worst Case Time Complexity: O(n^2)");
        System.out.println("Average Case Time Complexity: O(n^2)");
        System.out.println("Best Case Time Complexity: O(n)");

    }

    @Override
    public void spaceComplexity() {
        System.out.println("Space Complexity: O(1)"); //Only needs one temp variable
    }

    @Override
    public SortStage nextStepSort(SortStage sortStage, List<Integer> unsortedCollection) {
        if (sortStage != null) this.sortStage.update(sortStage);
        this.sortStage.setStarted(true);
        sort(unsortedCollection, this.sortStage.isAscendingOrder());
        //System.out.println(unsortedCollection);
        return this.sortStage;
    }
}
