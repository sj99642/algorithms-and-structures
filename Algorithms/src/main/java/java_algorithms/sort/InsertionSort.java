package java_algorithms.sort;

import java.util.Arrays;

public class InsertionSort extends SortingAlgorithm
{
    public static void main(String[] args)
    {
        // Take list from command line and sort it
        int[] arr = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }

        arr = (new InsertionSort()).sort(arr);

        // Print results
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Override
    public int[] sort(int[] arr)
    {
        int[] sorted = Arrays.copyOf(arr, arr.length);
        int temp;

        // Loop through, expanding the "sorted section" one by one
        for (int numSorted = 1; numSorted < sorted.length; numSorted++) {
            // Get the next item and swap it back until it is in place
            for (int i = numSorted; i >= 1; i--) {
                if (sorted[i] < sorted[i-1]) {
                    temp = sorted[i-1];
                    sorted[i-1] = sorted[i];
                    sorted[i] = temp;
                }
            }
        }

        return sorted;
    }
}