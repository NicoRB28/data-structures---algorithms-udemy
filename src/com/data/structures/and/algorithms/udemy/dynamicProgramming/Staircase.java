package com.data.structures.and.algorithms.udemy.dynamicProgramming;

import java.util.stream.IntStream;

/**
 * we have N stairs and Step size
 * The goal is to find out number of ways to reach the top
 */
public class Staircase {
    public static void main(String[] args) {
        int end = 4; // we want to reach 4th stair
        int stepSize = 3; // the number of steps are 1 or 2 or 3

        int[] memoization = new int[end + 1];
        initMemoization(end, memoization);
        //int ways = numberOfWays(end, stepSize);
        int ways = numberOfWays(end, stepSize, memoization);
        int waysBottomUp = numberOfWaysBottomUp(end, stepSize);
        System.out.println("ways top down: " + ways);
        System.out.println("ways bottom up: " + waysBottomUp);
    }

    private static void initMemoization(int end, int[] memoization) {
        IntStream.range(0, end + 1)
                .forEach(i -> memoization[i] = -1);
    }

    //BOTTOM UP IMPLEMENTATION
    private static int numberOfWaysBottomUp(int end, int stepSize) {
        int[] memoization = new int[end + 1];
        // base case:
        memoization[0] = 1;

        for (int i = 1; i <= end; i++) {
            memoization[i] = 0;
            for (int j = 1; j <= stepSize; j++) {
                if (i - j >= 0){
                    memoization[i] = memoization[i] + memoization[i - j];
                }
            }
        }
        return memoization[end];
    }

    // TOP DOWN IMPLEMENTATION
    private static int numberOfWays(int end, int stepSize, int[] memoization) {
        if (end == 0) {
            memoization[end] = 1;
            return 1;
        }
        if (end < 0) return 0;
        if (memoization[end] != -1) return memoization[end];

        memoization[end] = 0;

        for (int i = 1; i <= stepSize; i++ ) {
            memoization[end] = memoization[end] + numberOfWays(end - i, stepSize, memoization);
        }
        return memoization[end];
    }

    //Without Memoization
    private static int numberOfWays(int end, int stepSize) {
        int ways = 0;
        if (end == 0) return 1;
        if (end < 0) return 0;
        for (int i = 1; i <= stepSize; i++) {
            ways = ways + numberOfWays(end - i, stepSize);
        }
        return ways;
    }
}
