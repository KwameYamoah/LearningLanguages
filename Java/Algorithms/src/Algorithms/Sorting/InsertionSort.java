package Algorithms.Sorting;

import java.util.List;

public class InsertionSort implements SortingAlgorithm {


    @Override
    public void sort(List<Integer> unsortedCollection) {
        sortStage.reset();
        sort(unsortedCollection, true);
    }

    @Override
    public void sort(List<Integer> unsortedCollection, boolean ascendingOder) {
        if (unsortedCollection.size() == 0) return;
        if (unsortedCollection.size() == 1) return;

        if (!sortStage.isStarted()) sortStage.setI(1);
        for (int i = sortStage.getI(); i < unsortedCollection.size(); i++) {
            Integer temp = unsortedCollection.get(i);
            int lastIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (ascendingOder) {
                    if (unsortedCollection.get(j) > temp) {
                        unsortedCollection.set(j + 1, unsortedCollection.get(j));
                        lastIndex = j;
                    } else {
                        break;
                    }
                } else {
                    if (unsortedCollection.get(j) < temp) {
                        unsortedCollection.set(j + 1, unsortedCollection.get(j));
                        lastIndex = j;
                    } else {
                        break;
                    }
                }

            }
            unsortedCollection.set(lastIndex, temp);
            sortStage.setCurrentTargets(lastIndex, i);
            sortStage.setI(i + 1);
            if (sortStage.isStarted()) return;
        }
        sortStage.setSorted(true);
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
        if (sortStage != null) {
            this.sortStage.update(sortStage);
            if (sortStage.getI() != 1 && !sortStage.isStarted()) {
                this.sortStage.setI(1);
            }
        }
        this.sortStage.setStarted(true);
        sort(unsortedCollection, this.sortStage.isAscendingOrder());
        //System.out.println(unsortedCollection);
        return this.sortStage;
    }
}
