package com.data.structures.and.algorithms.udemy.divideAndConquer;

import java.util.Arrays;

/**
 * Divide an array in two half and keep doing that
 * until getting a single element array.
 *
 * lower bound and upper bound, we calculate a middle value:
 * middle value = (lower bound + upper bound) / 2
 *
 * Time Complexity:
 *  O(N Logn)
 * Space Complexity:
 *  O(N)
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = {1, 6, 3, 9, 14, 66, 2};
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void mergeSort(int[] array) {
        int lowerBound = 0;
        int upperBound = array.length - 1;
        mergeSort(array, lowerBound, upperBound);
    }

    private static void mergeSort(int[] array, int lowerBound, int upperBound) {
        if (lowerBound < upperBound) {
            int middleIndex = (lowerBound + upperBound) / 2;
            mergeSort(array, lowerBound, middleIndex);
            mergeSort(array, middleIndex + 1, upperBound);
            merge(array, lowerBound, middleIndex, upperBound);
        }
    }

    private static void merge(int[] array, int lowerBound, int middleIndex, int upperBound) {
        int leftLength  = middleIndex - lowerBound + 1;
        int rightLength = upperBound - middleIndex;

        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];

        for (int i = 0; i < leftLength; i++) {
            leftArray[i] = array[lowerBound + i];
        }

        for (int i = 0; i < rightLength; i++) {
            rightArray[i] = array[middleIndex + 1 + i];
        }

        int leftPointer = 0;
        int rightPointer = 0;
        int mergePointer = lowerBound;

        // When the two arrays have elements:
        while (leftPointer < leftLength && rightPointer < rightLength) {
            if (leftArray[leftPointer] < rightArray[rightPointer]) {
                array[mergePointer] = leftArray[leftPointer];
                leftPointer++;
            } else {
                array[mergePointer] = rightArray[rightPointer];
                rightPointer++;
            }
            mergePointer++;
        }
        // When there is one of the arrays that don't have elements:
        // Iterate over the left if still have elements
        while (leftPointer < leftLength) {
            array[mergePointer] = leftArray[leftPointer];
            leftPointer++;
            mergePointer++;
        }
        // Iterate over the right if still have elements
        while (rightPointer < rightLength) {
            array[mergePointer] = rightArray[rightPointer];
            rightPointer++;
            mergePointer++;
        }
    }

}
