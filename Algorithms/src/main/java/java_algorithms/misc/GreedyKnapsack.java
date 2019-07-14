package java_algorithms.misc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GreedyKnapsack
{
    public class Item
    {
        public String name;
        public double value;
        public double weight;

        public double valuePerUnitMass()
        {
            if (weight == 0) {
                return Double.POSITIVE_INFINITY;
            } else {
                return value / weight;
            }
        }
    }

    public static void main(String[] args)
    {

    }

    /**
     * Takes the size of the sack and a list of items (each with a label, value and weight).
     * This will return the biggest combination of items it can fit in the bag.
     * 
     * This will not perfectly solve the problem, but will greedily solve it.
     */
    public List<Item> runAlgorithm(List<Item> items, double sackSize)
    {
        // Will store each item, in order of its value over its weight (highest to lowest)
        PriorityQueue<Item> valuePerUnitMass = new PriorityQueue<Item>(items.size(), Comparator.comparing(Item::valuePerUnitMass).reversed());

        // Add all the items to the priority queue
        for (Item item : items) {
            valuePerUnitMass.add(item);
        }

        // The list to be returned
        List<Item> itemsInSack = new ArrayList<Item>();

        // Go through from highest to lowest value per weight, totting up the weight
        // Stop when adding the next would take the weight too high
        double totalWeight = 0;
        Item nextItem;
        while ((nextItem = valuePerUnitMass.poll()).weight + totalWeight < sackSize) {
            itemsInSack.add(nextItem);
            totalWeight += nextItem.weight;
        }

        return itemsInSack;
   }
}