package com.data.structures.and.algorithms.udemy.dynamicProgramming;

import java.util.Arrays;

/**
 * The goal is to find  the length of longest
 * subsequence from the given sequence in which elements are:
 * a. in Sorted order - Highest to lowest
 * b. Not necessarily contiguous or unique
 *
 * i.e:
 * 20, 8, 12, 16, 10, 9, 18, 7
 *
 * subsequences:
 * {20, 8, 7}
 * {20, 12, 10, 9, 7}
 * {20, 16, 10, 9, 7}
 * {20, 10, 9, 7}
 * {20, 9, 7}
 * {20, 18, 7}
 * {20, 7}
 * {8, 7}
 * {12, 10, 9, 7}
 * {16, 10, 9, 7}
 * ...etc.
 *
 */
public class LongestDecreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = {20, 8, 12, 16, 10, 9, 18, 7};

        int longestLength = getLongestDecreasingSubsequenceLength(nums, 0, Integer.MAX_VALUE);
        int[][] precalculatedValues = new int[nums.length + 1][nums.length];
        for (int[]arr : precalculatedValues) Arrays.fill(arr, -1);
        int longestTopDown = getLongestDecreasingSubsequenceLengthTopDown(nums, -1, 0, precalculatedValues);
        int bottomUp = bottomUp(nums);
        System.out.println("The longest length is: " + longestLength);
        System.out.println("The Top Down approach longest length is: " + longestTopDown);
        System.out.println("The Bottom up approach longest length is: " + bottomUp);

    }

    //Brute force
    private static int getLongestDecreasingSubsequenceLength(int[] nums, int currentPosition, int prev) {
        if (currentPosition == nums.length) return 0;
        int include = 0;
        if (nums[currentPosition] < prev) {
            include = 1 + getLongestDecreasingSubsequenceLength(nums, currentPosition + 1, nums[currentPosition]);
        }
        int exclude = getLongestDecreasingSubsequenceLength(nums, currentPosition + 1, prev);
        return Integer.max(include, exclude);
    }

    //TOP DOWN Approach
    private static int getLongestDecreasingSubsequenceLengthTopDown(int[] nums, int prevIndex, int current,
                                                                    int[][] precalculatedValues) {
        if (current == nums.length) return 0;

        if (precalculatedValues[prevIndex+1][current] > 0) {
            return precalculatedValues[prevIndex+1][current];
        }

        int include = 0;
        if (prevIndex < 0 || nums[current] < nums[prevIndex]) {
            include = 1 + getLongestDecreasingSubsequenceLengthTopDown(nums, current, current + 1, precalculatedValues);
        }
        int exclude = getLongestDecreasingSubsequenceLengthTopDown(nums, prevIndex, current + 1, precalculatedValues);

        //memoization
        precalculatedValues[prevIndex+1][current] = Integer.max(include, exclude);

        return precalculatedValues[prevIndex+1][current];
    }

    //BOTTOM UP Approach (Iterative)
    private static int bottomUp(int[] numbers){

        if (numbers.length == 0) return 0;

        int[] maxLengths = new int[numbers.length];
        Arrays.fill(maxLengths, 1);

        int max = 1;

        for (int j = 1; j < numbers.length; j++) {
            for (int i = 0; i < j; i++) {
                if (numbers[j] < numbers[i]) {
                    maxLengths[j] = Math.max(maxLengths[j], maxLengths[i] + 1);
                }
                max = Math.max(max, maxLengths[j]);
            }
        }
        return max;
    }

}
