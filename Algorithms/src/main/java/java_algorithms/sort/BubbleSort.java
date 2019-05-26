package java_algorithms.sort;

import java.util.Arrays;

/**
 * Implements bubble sort on an array of integers. 
 */
public class BubbleSort extends SortingAlgorithm
{
    public static void main(String[] args)
    {
        // Take list from command line and sort it
        int[] arr = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }

        arr = (new BubbleSort()).sort(arr);

        // Print results
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Override
    public int[] sort(int[] arr)
    {
        int[] copy = Arrays.copyOf(arr, arr.length);

        int temp;
        boolean hasSwapped;

        // Iterate as many times as there are items in the array
        for (int i = 0; i < copy.length; i++) {
            // No swap has taken place so far in this iteration
            hasSwapped = false;

            // Loop through from the start to (length - i - 1), swapping with the next as necessary
            // We go to there as all past (length - i) have been sorted, and the -1 is because we 
            //  swap with the next.
            for (int j = 0; j < (copy.length - i - 1); j++) {
                // If this item is bigger than the next, swap them
                if (copy[j] > copy[j+1]) {
                    temp = copy[j+1];
                    copy[j+1] = copy[j];
                    copy[j] = temp;

                    hasSwapped = true;
                }
            }

            // If, still, no swap has taken place, the list is sorted
            if (!hasSwapped) {
                break;
            }
        }

        return copy;
    }
}