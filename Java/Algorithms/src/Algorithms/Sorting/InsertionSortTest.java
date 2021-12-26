package Algorithms.Sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InsertionSortTest {

    @Test
    public void sort() {
        SortingAlgorithm insertionSort = new InsertionSort();
        List<Integer> list = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        insertionSort.sort(list);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), list);

        List<Integer> list2 = Arrays.asList(8, 7, 10, 9, 1, 5, 3, 2, 4, 6);
        insertionSort.sort(list2);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), list2);

        List<Integer> list3 = Arrays.asList(-1, -2, -3, -4, -5, -6, -7, -8, -9, -10);
        insertionSort.sort(list3);
        assertEquals(Arrays.asList(-10, -9, -8, -7, -6, -5, -4, -3, -2, -1), list3);

        List<Integer> list4 = new ArrayList<>();
        insertionSort.sort(list4);
        assertEquals(new ArrayList<>(), list4);

    }

    @Test
    public void testSort() {
        SortingAlgorithm insertionSort = new InsertionSort();

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        insertionSort.sort(list,false);
        assertEquals(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), list);

        List<Integer> list2 = Arrays.asList(5, 9, 2, 1, 6, 3, 10, 8, 7, 4);
        insertionSort.sort(list2,false);
        assertEquals(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), list2);
    }
}