package java_algorithms.misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This algorithm takes an amount and a set of coins, and outputs the minimum 
 */
public class CoinChange
{
    public static void main(String[] args)
    {
        CoinChange alg = new CoinChange();
        int amount;
        HashSet<Integer> coins;

        if (args.length >= 2) {
            amount = Integer.parseInt(args[0]);
            coins = new HashSet<Integer>();

            for (int i = 1; i < args.length; i++) {
                coins.add(Integer.valueOf(args[i]));
            }
        } else {
            System.err.println("Must run as `CoinChange amount coin1 coin2 ...`");
            return;
        }

        System.out.println(alg.runAlgorithm(amount, coins, new HashMap<Integer, Integer>()));
    }

    public int runAlgorithm(int amount, Set<Integer> coins, Map<Integer, Integer> known)
    {
        if (amount == 0) {
            return 0;
        }

        if (coins.contains(amount)) {
            return 1;
        }

        int minFound = Integer.MAX_VALUE;
        for (int i = 0; i < Math.ceil((double) amount / 2); i++) {

        }

        return minFound;
    }
}