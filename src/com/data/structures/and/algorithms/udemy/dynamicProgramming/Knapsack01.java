package com.data.structures.and.algorithms.udemy.dynamicProgramming;

public class Knapsack01 {
    public static void main(String[] args) {
        int[] weight = {7, 4, 4};
        int[] price = {15, 8, 8};

        int capacity = 10;
        int n = weight.length;

        int maxProfitBottomUp = getMaxProfitBottomUp(weight, price, n, capacity);

        int[][] values = initValues(capacity, n);

        int maxProfit = getMaxProfit(weight, price, n, capacity, values);

        System.out.println("The max profit is: " + maxProfit);
        System.out.println("The max profit is: " + maxProfitBottomUp + " (bottom up)");


    }

    // BOTTOM-UP approach
    private static int getMaxProfitBottomUp(int[] weight, int[] price, int n, int capacity) {
        int[][] values = new int[n+1][capacity+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity ; j++) {
                if (i == 0 || j == 0) {
                    values[i][j] = 0;
                } else {
                    int include = 0, exclude = 0;
                    // J represents the capacity for that cell
                    if (weight[i-1] <= j)
                        include = price[i-1] + values[i-1][j - weight[i-1]];
                    exclude = values[i-1][j];
                    values[i][j] = Math.max(include, exclude);
                }
            }
        }

        return values[n][capacity];
    }

    //TOP-DOWN approach
    private static int getMaxProfit(int[] weight, int[] price, int n, int capacity, int[][] values) {

        if (n == 0 || capacity == 0) {
            values[n][capacity] = 0;
            return 0;
        }

        if (values[n-1][capacity-1] != -1) return values[n-1][capacity-1];

        int include = 0, exclude = 0;
        //si entra en la bolsa lo incluyo
        if(weight[n-1] <= capacity)
            include = price[n-1] + getMaxProfit(weight, price, n-1, capacity - weight[n-1], values);
        //sino lo excluyo
        exclude = getMaxProfit(weight, price, n-1, capacity, values);

        values[n-1][capacity-1] = Math.max(include, exclude);
        return values[n-1][capacity-1];
    }

    private static int[][] initValues(int capacity, int n) {
        int[][] values = new int[n +1][capacity +1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                values[i][j] = -1;
            }
        }
        return values;
    }
}
