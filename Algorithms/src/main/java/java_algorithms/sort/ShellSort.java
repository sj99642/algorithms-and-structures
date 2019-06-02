package java_algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
        // Make a copy so the original is not changed
        int[] copy = Arrays.copyOf(arr, arr.length);

        // Get a list of gaps
        int[] gaps = generateGapsArray(copy.length);

        // Loop whole thing with changing gaps
        for (int i = 0; i < gaps.length; i++) {
            // Generate lists of indices and corresponding values, sort them and put them back
            int gap = gaps[i];

            // Now the process will run for each offset in [0..gap)
            for (int offset = 0; offset < gap; offset++) {
                // Find the size of this subsequence
                int subseqLength = copy.length / gap;
                if (offset < copy.length % gap) {
                    // This is to account for when the length does not divide directly
                    subseqLength += 1;
                }

                // Generate the array of values
                int[] values = new int[subseqLength];
                for (int j = 0; j < subseqLength; j++) {
                    // `offset + (j * gap)` finds the current index
                    values[j] = copy[offset + (j * gap)];
                }

                // Do insertion sort on the values
                values = insertionSort(values);

                // Put them back in, corresponding to the indices
                for (int j = 0; j < subseqLength; j++) {
                    copy[offset + (j * gap)] = values[j];
                }
            }
        }

        return copy;
    }

    /**
     * Performs an insertion sort. In this context, used for sorting subsequences. 
     */
    private int[] insertionSort(int[] arr) 
    {
        return (new InsertionSort()).sort(arr);
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
        ArrayList<Integer> gaps = new ArrayList<Integer>();

        int k = 1;

        // Keep incrementing i and adding new ks until k gets too large
        for (int i = 1; k < n; i++) {
            // Add this k
            gaps.add(k);

            // Generate new k
            k = (int) (Math.pow(4, i) + 3 * Math.pow(2, i) + 1);
        }

        // Convert to integer array
        int[] ret = new int[gaps.size()];
        Iterator<Integer> iterator = gaps.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        
        return ret;
    }
}