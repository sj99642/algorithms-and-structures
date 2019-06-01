package java_algorithms.sort;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort extends SortingAlgorithm
{
    public static void main(String[] args)
    {
        // Take list from command line and sort it
        int[] arr = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }

        arr = (new QuickSort()).sort(arr);

        // Print results
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Override
    public int[] sort(int[] arr)
    {
        return sort(arr, 0, arr.length);
    }

    public int[] sort(int[] arr, int start, int end)
    {        
        // Make a copy in order to preserve the original after merging in changes
        int[] copy = Arrays.copyOf(arr, arr.length);

        // Quick base case: if the section we are sorting is length 1 or 0
        if (end - start <= 1) {
            return arr;
        }

        // Choose a random pivot
        int pivotIndex = start + ThreadLocalRandom.current().nextInt(end - start);
        int pivotValue = copy[pivotIndex];

        // Make a list of the items below and above the pivot
        ArrayList<Integer> belowPivot = new ArrayList<Integer>();
        ArrayList<Integer> abovePivot = new ArrayList<Integer>();
        for (int i = 0; i < end - start; i++) {
            // Don't include the pivot as either above or below itself
            if (pivotIndex == i + start) {
                continue;
            }

            if (copy[start + i] <= pivotValue) {
                belowPivot.add(copy[start + i]);
            } else {
                abovePivot.add(copy[start + i]);
            }
        }

        // Put these back into the array
        assert belowPivot.size() + abovePivot.size() + 1 == end - start;

        // Add those below
        for (int i = 0; i < belowPivot.size(); i++) {
            copy[start + i] = belowPivot.get(i);
        }

        // Add the pivot
        copy[start + belowPivot.size()] = pivotValue;

        // Add those above
        for (int i = 0; i < abovePivot.size(); i++) {
            // Shifting along the correct amount
            copy[start + belowPivot.size() + 1 + i] = abovePivot.get(i);
        }

        // Now run again on the left and right parts
        copy = sort(copy, start, start + belowPivot.size());
        copy = sort(copy, start + belowPivot.size() + 1, end);

        // Should now be done, so return
        return copy;

        // // Make a copy of the bit to sort
        // int[] subset = Arrays.copyOfRange(copy, start, end);

        // // Pick a random value to act as a pivot
        // int pivotIndex = ThreadLocalRandom.current().nextInt(subset.length);
        // int pivotValue = arr[pivotIndex];

        // // Run DNF algorithm on subset
        // subset = (new DutchNationalFlag()).dnf(subset, pivotValue, pivotValue);

        // // Find start and end of pivot section
        // int startOfPivot = findFirstInstance(subset, pivotValue);
        // int startOfUpper = findLastInstance(subset, pivotValue) + 1;
        // int sizeOfPivot = startOfUpper - startOfPivot;

        // // Run quick sort on the left and right halves
        // int[] left = sort(Arrays.copyOf(subset, startOfPivot));
        // int[] right = sort(Arrays.copyOfRange(subset, startOfUpper, subset.length));

        // // Put them all back in
        // System.arraycopy(copy, start, left, 0, left.length);
        // for (int i = 0; i < sizeOfPivot; i++) copy[i + start + startOfPivot] = pivotValue;
        // System.arraycopy(copy, start + startOfUpper, right, 0, right.length);

        // Return the modified array
        // return copy;
    }
}