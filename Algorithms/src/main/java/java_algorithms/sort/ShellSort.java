package java_algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class ShellSort extends SortingAlgorithm
{
    public static void main(String[] args)
    {
        // Take list from command line and sort it
        int[] arr = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }

        arr = (new ShellSort()).sort(arr);

        // Print results
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Override
    public int[] sort(int[] arr)
    {
        // Make a copy so the first is unchanged
        int[] copy = Arrays.copyOf(arr, arr.length);

        // An array of the gaps between subsets to perform insertion sort on
        int[] gaps = generateGapsArray(copy.length);

        // Run the procedure for each gap size
        for (int i = 0; i < gaps.length; i++) {
            // Divide into subsets and run insertion sort on each
            // There will be gaps[i] such subsets
            // This runs from the biggest gap downwards
            for (int j = gaps.length - 1; j >= 0; j--) {
                // Take this subset and run insertion sort on it
                int[] sorted = insertionSortSubset(copy, j, gaps[i]);

                // Put this back into the array
                for (int k = 0; k < sorted.length; k++) {
                    copy[j + (gaps[i] * k)] = sorted[k];
                }
            }
        }

        return copy;
    }

    public int[] insertionSortSubset(int[] arr, int start, int gap)
    {
        // Takes a subset of arr, consisting of arr[start] and all items some multiple
        // of gap after.

        // Find the size of this subset
        int subsetSize = arr.length / gap;

        // This might not be big enough - one more may be needed if the start is small
        // enough that there is an extra value on the end
        if (arr.length - start > subsetSize) {
            subsetSize += 1;
        }

        int[] subset = new int[subsetSize];

        // Now run insertion sort
        for (int sorted = 1; sorted < subset.length; sorted++) {
            // Take the next item and move it backwards until it is in place
            for (int current = sorted; current > 0; current--) {
                // See if it needs swapping, or leave otherwise
                if (subset[current] < subset[current - 1]) {
                    int temp = subset[current];
                    subset[current] = subset[current - 1];
                    subset[current - 1] = temp;
                } else {
                    // In place
                    break;
                }
            }
        }

        return subset;
    }

    /**
     * Returns an array of gaps to use between elements in the subsets.
     * 
     * Uses the Sedgewick, 1986 technique, which is A036562 in the OEIS:
     * 
     * $$
     *  A_k = 4^k + 3*2^{k-1} + 1
     * $$
     */
    private int[] generateGapsArray(int n)
    {
        int[] gaps = new int[(int) Math.floor(0.2 * (Math.log(n)/Math.log(2) + 3))];

        int k = 1;

        // Keep incrementing i and adding new ks until k gets too large
        for (int i = 1; k < n; i++) {
            // Check we are still in the list's range
            assert i < gaps.length : "New value of k is valid but array is too short";

            // Add this k
            gaps[i-1] = k;

            // Generate new k
            k = (int) (Math.pow(4, i) + 3 * Math.pow(2, i) + 1);
        }

        return gaps;
    }
}