package java_algorithms.misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This algorithm takes an amount and a set of coins, and outputs the minimum 
 */
public class CoinChange
{
    private Map<Integer, Integer> previouslyFound = new HashMap<Integer, Integer>();
    private Set<Integer> coins;

    public static void main(String[] args)
    {
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

        CoinChange alg = new CoinChange(coins);
        System.out.println(alg.runAlgorithm(amount));
    }

    public CoinChange(Set<Integer> coins)
    {
        this.coins = coins;
    }

    public int runAlgorithm(int amount)
    {
        if (amount == 0) {
            return 0;
        }

        if (coins.contains(amount)) {
            return 1;
        }

        if (previouslyFound.containsKey(amount)) {
            return previouslyFound.get(amount);
        }

        // Looping, run the algorithm. Ask the question for all numbers
        // from 0 up to here, then find the smallest valued number bond. 
        int minFound = Integer.MAX_VALUE;
        for (int i = 0; i < amount / 2; i++) {
            // All ways of making it to here
            int x = runAlgorithm(i) + runAlgorithm(amount - i);
            if (x < minFound) {
                minFound = x;
            }
        }

        return minFound;
    }
}