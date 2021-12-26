package Algorithms.Sorting;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.util.List;

public class SelectionSort implements SortingAlgorithm {
    @Override
    public void sort(List<Integer> unsortedCollection) {
        sortStage.reset();
        sort(unsortedCollection, true);
    }

    @Override
    public void sort(List<Integer> unsortedCollection, boolean ascendingOrder) {
        int flag = sortStage.getCounter();
        for (int i = sortStage.getI(); i < unsortedCollection.size(); i++) {
            List<Integer> unsorted = unsortedCollection.subList(flag, unsortedCollection.size());
            int count = flag;
            int min = unsortedCollection.get(flag);
            int minIndex = flag;
            for (Integer n : unsorted) {
                if (ascendingOrder) {
                    if (n < min) {
                        min = n;
                        minIndex = count;
                    }
                } else {
                    if (n > min) {
                        min = n;
                        minIndex = count;
                    }
                }
                count++;
            }

            if (flag != minIndex) {
                int temp = unsortedCollection.get(flag);
                unsortedCollection.set(flag, unsortedCollection.get(minIndex));
                unsortedCollection.set(minIndex, temp);
                sortStage.setCurrentTargets(flag, minIndex);

            }
            flag++;

            if (sortStage.isStarted()) {
                sortStage.setCounter(flag);
                sortStage.setI(i + 1);
                break;
            }
        }
        if (sortStage.isStarted() && sortStage.getI() >= unsortedCollection.size()) sortStage.setSorted(true);
    }

    @Override
    public void timeComplexity() {
        System.out.println("Big O notation");
        System.out.println("Worst Case Time Complexity: O(n^2)");
        System.out.println("Average Case Time Complexity: O(n^2)");
        System.out.println("Best Case Time Complexity: O(n^2)");
    }

    @Override
    public void spaceComplexity() {
        System.out.println("Space Complexity: O(1)"); //sub arrays
    }

    @Override
    public SortStage nextStepSort(SortStage sortStage, List<Integer> unsortedCollection) {
        if (sortStage != null) {
            this.sortStage.update(sortStage);
        }
        this.sortStage.setStarted(true);
        sort(unsortedCollection, this.sortStage.isAscendingOrder());
        //System.out.println(unsortedCollection);
        return this.sortStage;
    }
}
