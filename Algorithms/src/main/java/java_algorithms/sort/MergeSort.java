package java_algorithms.sort;

import java.util.Arrays;

public class MergeSort extends SortingAlgorithm
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
        // Base case
        if (arr.length == 1) {
            return arr;
        }

        // Standard merge sort algorithm
        int[] left = sort(Arrays.copyOfRange(arr, 0, arr.length/2));
        int[] right = sort(Arrays.copyOfRange(arr, arr.length/2, arr.length));

        // Merge the two sides and return
        return merge(left, right);
    }

    private int[] merge(int[] arr1, int[] arr2)
    {
        int[] merged = new int[arr1.length + arr2.length];
        
        // Counters for how far through each half array the process is
        int i1 = 0, i2 = 0;

        // Loop through `merged`, adding in the smallest of the two arrays' first values
        for (int im = 0; im < merged.length; im++) {
            // Get the smallest, add it and progress along that array
            if (safelyGetItem(arr1, i1) < safelyGetItem(arr2, i2)) {
                merged[im] = arr1[i1];
                i1++;
            } else {
                merged[im] = arr2[i2];
                i2++;
            }
        }

        return merged;
    }

    private int safelyGetItem(int[] arr, int index)
    {
        if (index >= arr.length) {
            return Integer.MAX_VALUE;
        }

        return arr[index];
    }
}