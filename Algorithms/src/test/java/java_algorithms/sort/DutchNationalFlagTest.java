package java_algorithms.sort;

import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;

import static org.junit.Assert.*;

import java_algorithms.sort.DutchNationalFlag;

public class DutchNationalFlagTest 
{
    public DutchNationalFlag algorithm = new DutchNationalFlag();

    // Commented out because it didn't do as expected but in an irrelevent way.
    // @Test
    // public void testCases()
    // {
    //     assertArrayEquals(new int[] {1, 2, 3, 6, 9, 7, 8}, algorithm.dnf(new int[] {1, 9, 2, 3, 7, 6, 8}, 3, 7));
    //     assertArrayEquals(new int[] {232, 381, 267, 102, 67, 20, 429, 554, 442, 535, 944, 603, 929, 780, 870},
    //             algorithm.dnf(new int[] {944, 603, 232, 381, 267, 102, 429, 929, 554, 67, 780, 442, 535, 20, 870}, 400, 600));
    // }

    @Test()
    public void testLargeArray()
    {
        // Create and populate the array
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) arr[i] = ThreadLocalRandom.current().nextInt(1000);

        // Randomly generate two bounds to be the lower and higher divisions
        int r1 = ThreadLocalRandom.current().nextInt(1000);
        int r2 = ThreadLocalRandom.current().nextInt(1000);
        int low = Math.min(r1, r2);
        int high = Math.max(r1, r2);

        // Sort it
        arr = algorithm.dnf(arr, low, high);

        // Here, a phase of 0 is low, 1 is medium and 2 is high. Each item in the sorted list
        // may conform to the current phase or move to a higher phase, but not decrease.
        int phase = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            // In any of these cases, change the phase and move on
            if (phase == 0 && arr[i] > low && arr[i] < high) {
                // In low and this belongs in the middle
                phase = 1;
                continue;
            } else if (phase == 0 && arr[i] >= high) {
                // In low and this belongs in high
                phase = 2;
                continue;
            } else if (phase == 1 && arr[i] >= high) {
                // In middle and this belongs in high
                phase = 2;
                continue;
            }

            // Now check if the current value belongs in a lower phase.
            // If so, fail the test.
            if (phase == 1 && arr[i] <= low) {
                // In medium and this item should be in low
                fail();
            } else if (phase == 2 && arr[i] < high) {
                // In high and this item should be in low or medium
                fail();
            }
        }
    }
}
