package com.data.structures.and.algorithms.udemy.greedy;

import java.util.Arrays;

/**
 *complexity: O(n*log(n))
 */
public class FractionalKnapsack {
    public static void main(String[] args) {
        int[] wt = {10, 40, 20, 30};
        int[] val = {60, 40, 100, 120};
        int capacity = 50;

        double maxValue = getMaxValue(wt, val, capacity);
        System.out.println("The maximum profit possible = " + maxValue);
    }

    private static double getMaxValue(int[] wt, int[] val, int capacity) {
        Item[] items = new Item[wt.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(wt[i], val[i], i);
        }
        Arrays.sort(items, (a, b) -> b.cost.compareTo(a.cost));
        double totalValue = 0d;

        for (Item item: items) {
            int currentWt = (int)item.wt;
            int currentValue = (int) item.val;

            if (capacity - currentWt >= 0) {
                capacity = capacity - currentValue;
                totalValue += currentValue;

            } else {
                double fraction = (double) capacity / currentWt;
                totalValue += currentValue * fraction;
                break;
            }
        }
        return totalValue;
    }

    static class Item {
        Double cost;
        double wt;
        double val;
        double index;

        public Item(int wt, int val, int index) {
            this.wt = wt;
            this.val = val;
            this.index = index;

            cost = (double) (val / wt);
        }
    }
}
