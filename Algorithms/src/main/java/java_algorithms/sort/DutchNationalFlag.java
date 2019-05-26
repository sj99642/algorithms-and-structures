package java_algorithms.sort;

import java.util.Arrays;

public class DutchNationalFlag
{
    /**
     * Sorts the array into the order of [<= low, middle, >= high]. Thus low and high are the
     * dividing lines between low-middle and middle-high. 
     */
    public int[] dnf(int[] arr, int low, int high)
    {
        int[] copy = Arrays.copyOf(arr, arr.length);

        int low_p = 0;
        int mid_p = 0;
        int high_p = arr.length - 1;
        int temp;

        /**
         * low_p is the index of the first middle element (thus the end of the low section) and
         * high_p is the last in the middle (so the next element is the first high).
         * 
         * The algorithm progresses by incrementing mid_p. If it qualifies as low, copy[low_p] is
         * swapped with copy[mid_p] and low_p is incremented by 1. If it qualifies as high,
         * copy[high_p] is swapped with copy[mid_p], and high_p is decremented by 1. In this way,
         * high and low values are moved into their correct places. If copy[mid_p] is neither high
         * nor low, nothing happens.
         */

         for (mid_p = 0; mid_p <= high_p; ) {
            // Check if it is low or high
            if (copy[mid_p] <= low) {
                // It is low; swap it down
                temp = copy[low_p];
                copy[low_p] = copy[mid_p];
                copy[mid_p] = temp;

                // Move the low section right by 1
                low_p++;

                // Move mid_p along by 1
                mid_p++;
            } else if (copy[mid_p] >= high) {
                // It is high, swap it up
                temp = copy[high_p];
                copy[high_p] = copy[mid_p];
                copy[mid_p] = temp;

                // Move the high section left by 1
                high_p--;

                // Do not increment mid_p since the new copy[mid_p] value is currently unprocessed
            } else {
                // Current value stays in the mid section, just move along
                mid_p++;
            }
         }

        return copy;
    }
}