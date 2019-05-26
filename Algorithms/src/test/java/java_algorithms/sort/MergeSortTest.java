package java_algorithms.sort;


import java.util.Random;
import org.junit.Test;

import static org.junit.Assert.*;

import java_algorithms.sort.MergeSort;

public class MergeSortTest
{
    /**
     * A few small, out-of-order lists
     */
    @Test
    public void testSimpleSorting()
    {
        assertArrayEquals((new MergeSort()).sort(new int[] {1, 3, 2}), new int[] {1, 2, 3});
        assertArrayEquals((new MergeSort()).sort(new int[] {4, 3, 2, 1}), new int[] {1, 2, 3, 4});
        assertArrayEquals((new MergeSort()).sort(new int[] {1, 0, 0, 1}), new int[] {0, 0, 1, 1});
    }

    /**
     * A list already in order, which should be untouched
     */
    @Test 
    public void testUnchangedAscending()
    {
        assertArrayEquals((new MergeSort()).sort(new int[] {1, 10, 100, 1000}), new int[] {1, 10, 100, 1000});
    }

    /**
     * Generate a large random list and test that it is in ascending order after sorting
     */
    @Test 
    public void testLargeArray()
    {
        // Create and populate the array
        int[] arr = new int[1024];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) arr[i] = rand.nextInt();

        // Sort it
        arr = (new MergeSort()).sort(arr);

        // Test it is in the right order
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]) {
                fail("Found an item in the large list greater than its rightward neighbour");
            }
        }
    }
}