package Algorithms.Sorting;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements SortingAlgorithm {


    @Override
    public void sort(List<Integer> unsortedCollection) {
        sort(unsortedCollection,true);

    }

    @Override
    public void sort(List<Integer> unsortedCollection, boolean ascendingOder) {
        int left = 0;
        int right = unsortedCollection.size() - 1;
        sort(unsortedCollection, left, right + 1,ascendingOder);
    }


    private void sort(List<Integer> unsortedCollection, int left, int right,boolean ascendingOrder) {
        if ((right - left) > 1) {
            // Find the middle point (lower side)
            int middle = (left + right) / 2;

            // Sort first and second halves
            sort(unsortedCollection, left, middle, ascendingOrder);
            sort(unsortedCollection, middle, right, ascendingOrder);

            merge(unsortedCollection, left, middle, right,ascendingOrder);

            // Merge the sorted halves
        }
    }

    private void merge(List<Integer> unsortedCollection, int l, int m, int r, boolean ascendingOrder) {
        // Find sizes of two sub-arrays to be merged
        int leftSize = m - l;
        int rightSize = r - m;
        /* Create temp arrays */
        List<Integer> left = new ArrayList<>(leftSize);
        List<Integer> right = new ArrayList<>(rightSize);

        /*Copy data to temp arrays*/
        for (int i = 0; i < leftSize; ++i) left.add(unsortedCollection.get(l + i));

        for (int j = 0; j < rightSize; ++j) right.add(unsortedCollection.get(m + j));


        /* Merge the temp arrays */

        // Initial indexes of first and second sub-arrays
        int i = 0, j = 0;

        // Initial index of merged sub-array array
        int k = l;

        while (i < leftSize && j < rightSize) {
            if(ascendingOrder) {
                if (left.get(i) <= right.get(j)) {
                    unsortedCollection.set(k, left.get(i));
                    i++;
                } else {
                    unsortedCollection.set(k, right.get(j));
                    j++;
                }
            }
            else{
                if (left.get(i) >= right.get(j)) {
                    unsortedCollection.set(k, left.get(i));
                    i++;
                } else {
                    unsortedCollection.set(k, right.get(j));
                    j++;
                }
            }
            k++;
        }

        /* Copy remaining elements of left collection if any */
        while (i < leftSize) {
            unsortedCollection.set(k, left.get(i));
            i++;
            k++;
        }

        /* Copy remaining elements of right collection if any */
        while (j < rightSize) {
            unsortedCollection.set(k, right.get(j));
            j++;
            k++;
        }
    }


        @Override
        public void timeComplexity() {
            System.out.println("Big O notation");
            System.out.println("Worst Case Time Complexity: O(nlogn)");
            System.out.println("Average Case Time Complexity: O(nlogn)");
            System.out.println("Best Case Time Complexity: O(nlogn)");

        }

        @Override
        public void spaceComplexity() {
            System.out.println("Space Complexity: O(n)"); //sub arrays

        }

    @Override
    public SortStage nextStepSort(SortStage sortStage, List<Integer> unsortedCollection) {
        if(sortStage!=null) {this.sortStage.update(sortStage);}
        this.sortStage.setStarted(true);
        sort(unsortedCollection,this.sortStage.isAscendingOrder());
        System.out.println(unsortedCollection);
        return this.sortStage;
    }
}
