package com.data.structures.and.algorithms.udemy.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class MinimumCoinsToMakeChange {
    public static void main(String[] args) {
        int[] coins = {1, 3, 5, 2};
        int len = coins.length;
        int total = 7;

        int minNumberOfCoinsToGiveChange = minCoins(coins, len, total);
        int topDownMin = minCoinsTopDown(coins, len, total);
        int bottomUpMin = minCoinsBottomUp(coins, len, total);

        System.out.println("Min num of coins: " + minNumberOfCoinsToGiveChange);
        System.out.println("Top Down Min num of coins: " + topDownMin);
        System.out.println("Bottom Up num of coins: " + bottomUpMin);

    }

    //Exponential approach
    private static int minCoins(int[] coins, int len, int total) {
        if (total == 0) return 0;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (coins[i] <= total) {
                int subResult = minCoins(coins, len, total - coins[i]);
                if (subResult != Integer.MAX_VALUE && subResult + 1 < result) {
                    result = subResult + 1;
                }
            }
        }
        return result;
    }

    //TOP-DOWN approach | recursion + memoization
    //TIME: O(MA) Time (M = number of coins, A = Target Amount)
    //SPACE: O(A) (A= total target)
    private static int minCoinsTopDown(int[] coins, int len, int total) {
        return minCoinsTopDown(coins, len, total, new HashMap<>());
    }

    private static int minCoinsTopDown(int[] coins, int len, int total, Map<Integer, Integer> map) {
        if (total == 0) return 0;

        if (map.containsKey(total)) return map.get(total);

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (coins[i] <= total) {
                int subResult = minCoinsTopDown(coins, len, total - coins[i], map);
                if (subResult != Integer.MAX_VALUE && subResult + 1 < result) {
                    result = subResult + 1;
                }
            }
        }
        map.put(total, result);
        return result;
    }

    //Bottom Up approach | Iterative Approach
    //TIME: O(MA) (M = number of coins , A = target amount)
    //SPACE: O(A) (A = target amount)
    private static int minCoinsBottomUp(int[] coins, int len, int total) {
        int[] preCalculatedValues = new int[total + 1];
        //for 0 value there is no number of coins
        preCalculatedValues[0] = 0;

        for (int i = 1; i < preCalculatedValues.length; i++) {
            preCalculatedValues[i] = Integer.MAX_VALUE - 1;
        }

        //denominations (coins)
        for (int i = 0; i < coins.length; i++) {
            //total / target amount
            //the 0 value is already place in line 60
            for (int j = 1; j < preCalculatedValues.length; j++) {
                if ((coins[i] <= j) && (1 + preCalculatedValues[j - coins[i]] < preCalculatedValues[j])) {
                        preCalculatedValues[j] = 1 + preCalculatedValues[j - coins[i]];
                }
            }
        }
        return preCalculatedValues[total];
    }
}
