package com.data.structures.and.algorithms.udemy.dynamicProgramming;

public class RodCutting {
    public static void main(String[] args) {
        //price of the cuts
        int[] price = new int[] {1, 5, 8, 9};
        int size = price.length;

        int max = rodCut(price, size);
        System.out.println("Max recursive = " + max);
        int topDown = topDown(price, size, new int[size]);
        System.out.println("Top Down = " + topDown);
        int bottomUp = bottomUp(price, size);
        System.out.println("Bottom UP = " + bottomUp);
    }

    private static int rodCut(int[] price, int size) {
        if (size == 0) return 0;

        int maxRevenue = Integer.MIN_VALUE;

        for (int i = 1; i <= size; i++) {
            maxRevenue = Math.max(maxRevenue, price[i-1] + rodCut(price, size - i));
        }
        return maxRevenue;
    }

    //Top Down
    public static int topDown(int[] price, int size, int[] memoization) {
        if (size == 0) return 0;

        if (memoization[size - 1] > 0) return memoization[size - 1];

        int maxRevenue = Integer.MIN_VALUE;

        for (int i = 1; i <= size; i++) {
            maxRevenue = Math.max(maxRevenue, price[i-1] + rodCut(price, size - i));
        }
        memoization[size - 1] = maxRevenue;
        return maxRevenue;
    }

    private static int bottomUp(int[] price, int size) {
        int[] revenue = new int[price.length + 1];
        revenue[0] = 0;
        
        int maxRevenue = Integer.MIN_VALUE;

        for (int i = 1; i <revenue.length ; i++) {
            for (int j = 1; j <= i; j++) {
                maxRevenue = Math.max(maxRevenue, price[j-1] + revenue[i-j]);
            }
            revenue[i] = maxRevenue;
        }
        return revenue[price.length];
    }

}
