package com.data.structures.and.algorithms.udemy.dynamicProgramming;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * Levenshtein Distance Problem
 * Given two strings we have to calculate the minimum number of
 * operations it takes to transform one string to another string.
 * The allowed operations are:
 * a. insert a character
 * b. delete a character
 * c. substitution of a character
 *
 * ie:
 * str1 = BASE
 * str2 = BASIC
 *
 * 1. substitution of E -> I => BASI
 * 2. inserting character C => BASIC
 *
 * str1 = Thursday
 * str2 = Tuesday
 * 1.delete letter h => Tursday
 * 2.replace R with E => Tuesday
 *
 *
 */
public class Levenshtein {
    public static void main(String[] args) {
        String wordOne = "Tuesday";
        String wordTwo = "Thursday";

        int recursiveDistance = getLevenshteinDistanceRecursive(wordOne, wordTwo);
        System.out.println("Recursive distance: " + recursiveDistance);
        int topDownDistance = getLevenshteinDistanceTopDown(wordOne, wordTwo);
        System.out.println("Top Down distance: " + topDownDistance);
        int bottomUpDistance = getLevenshteinDistanceBottomUp(wordOne, wordTwo);
        System.out.println("Bottom Up distance: " + bottomUpDistance);
    }

    public static int getLevenshteinDistanceRecursive(String wordOne, String wordTwo) {
        return getLevenshteinDistanceRecursive(wordOne, wordTwo, wordOne.length(), wordTwo.length());
    }

    private static int getLevenshteinDistanceRecursive(String wordOne, String wordTwo, int wordOneLength, int wordTwoLength) {
        if (wordOneLength == 0 || wordTwoLength == 0) return Math.max(wordOneLength, wordTwoLength);

        int insertionChoice = 1 + getLevenshteinDistanceRecursive(
                wordOne, wordTwo, wordOneLength, wordTwoLength - 1);
        int deleteChoice = 1 + getLevenshteinDistanceRecursive(
                wordOne, wordTwo, wordOneLength - 1, wordTwoLength);

        int k = wordOne.charAt(wordOneLength - 1) == wordTwo.charAt(wordTwoLength - 1) ? 0 : 1;

        int substitutionChoice = k + getLevenshteinDistanceRecursive(
                wordOne, wordTwo, wordOneLength - 1, wordTwoLength - 1);
        return Math.min(insertionChoice, Math.min(deleteChoice, substitutionChoice));
    }

    //Top-Down Approach:
    public static int getLevenshteinDistanceTopDown(String wordOne, String wordTwo) {
        int[][] memoization = initializeMemoizationArray(wordOne.length(), wordTwo.length());
        return getLevenshteinDistanceTopDown(wordOne, wordTwo, wordOne.length(), wordTwo.length(), memoization);
    }
    private static int getLevenshteinDistanceTopDown(
            String wordOne, String wordTwo, int wordOneLength, int wordTwoLength, int[][] memoization) {
        if (wordOneLength == 0 || wordTwoLength == 0)
            return Math.max(wordOneLength, wordTwoLength);

        if (memoization[wordOneLength-1][wordTwoLength-1] >= 0)
            return memoization[wordOneLength-1][wordTwoLength-1];

        int insertionChoice = 1 + getLevenshteinDistanceTopDown(
                wordOne, wordTwo, wordOneLength, wordTwoLength - 1, memoization);
        int deleteChoice = 1 + getLevenshteinDistanceTopDown(
                wordOne, wordTwo, wordOneLength - 1, wordTwoLength, memoization);

        int k = wordOne.charAt(wordOneLength - 1) == wordTwo.charAt(wordTwoLength - 1) ? 0 : 1;

        int substitutionChoice = k + getLevenshteinDistanceTopDown(
                wordOne, wordTwo, wordOneLength - 1, wordTwoLength - 1, memoization);
        memoization[wordOneLength-1][wordTwoLength-1] = Math.min(insertionChoice, Math.min(deleteChoice, substitutionChoice));
        return memoization[wordOneLength-1][wordTwoLength-1];
    }

    private static int[][] initializeMemoizationArray(int wordOneLength, int wordTwoLength) {
        int[][] memoization = new int[wordOneLength][wordTwoLength];
        for (int[] row : memoization) {
            Arrays.fill(row, -1);
        }
        return memoization;
    }

    public static int getLevenshteinDistanceBottomUp(String wordOne, String wordTwo) {
        int [][] preValues = initialize(wordOne.length(), wordTwo.length());
        for (int i = 1; i <= wordOne.length(); i++) {
            for (int j = 1; j <= wordTwo.length(); j++) {
                if (wordOne.charAt(i - 1) == wordTwo.charAt(j - 1)) {
                    preValues[i][j] = preValues[i - 1][j - 1];
                } else {
                    preValues[i][j] = 1 + Math.min(preValues[i][j-1],
                            Math.min(preValues[i-1][j], preValues[i - 1][j - 1]));
                }
            }
        }
        return preValues[wordOne.length()][wordTwo.length()];
    }

    private static int[][] initialize(int wordOneLength, int wordTwoLength) {
        int [][] preValues = new int[wordOneLength + 1][wordTwoLength + 1];
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < wordOneLength; i++) {
                //base cases
                preValues[0][i] = i;
            }
            latch.countDown();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < wordTwoLength; i++) {
                //base cases
                preValues[i][0] = i;
            }
            latch.countDown();
        });
        t1.run();t2.run();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return preValues;
    }
}