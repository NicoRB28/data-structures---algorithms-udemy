package com.data.structures.and.algorithms.udemy.divideAndConquer;

import java.util.Arrays;

public class MedianOfMedians {
    public static void main(String[] args) {
        int[] array = {25, 24, 33, 39, 3, 18, 19, 31, 23, 49, 45, 16, 1, 29, 40, 22, 15, 20, 24, 4, 13, 34};
        
        findMedian(array);
    }

    private static void findMedian(int[] array) {
        int midIndex = (array.length / 2) + 1;
        int median = findMedianUtil(array, midIndex, 0, array.length - 1);
        System.out.println("Median is: " + median);
    }

    private static int findMedianUtil(int[] array, int midIndex, int lowerBound, int upperBound) {
        int m = partition(array, lowerBound, upperBound);
        int length = m - lowerBound + 1;
        if (length == midIndex) {
            return array[m];
        }
        if (length > midIndex) {
            return findMedianUtil(array, midIndex, lowerBound, m - 1);
        } else {
            return findMedianUtil(array, midIndex - length, m + 1, upperBound);
        }
    }

    private static int partition(int[] array, int lowerBound, int upperBound) {
        int pivot = getPivotValue(array, lowerBound, upperBound);
        while (lowerBound < upperBound) {
            while (array[lowerBound] < pivot) lowerBound++;
            while (array[upperBound] > pivot) upperBound--;

            if (array[lowerBound] == array[upperBound]) {
                lowerBound++;
            } else if (lowerBound < upperBound) {
                //swap
                int temp = array[lowerBound];
                array[lowerBound] = array[upperBound];
                array[upperBound] = temp;
            }
        }
        return upperBound;
    }

    private static int getPivotValue(int[] array, int lowerBound, int upperBound) {
        if (upperBound - lowerBound + 1 <= 9) {
            Arrays.sort(array);
            return array[array.length/2];
        }
        int[] temp = null;
        int[] medians = new int[(int) Math.ceil((double)(upperBound - lowerBound + 1) / 5)];
        int medianIndex = 0;
        while(upperBound >= lowerBound) {
            temp = new int[Math.min(5, upperBound-lowerBound+1)];
            for (int i = 0; i < temp.length && lowerBound <= upperBound; i++) {
                temp[i] = array[lowerBound];
                lowerBound++;
            }
            Arrays.sort(temp);
            medians[medianIndex] = temp[temp.length/2];
            medianIndex++;
        }
        return getPivotValue(medians, 0, medians.length-1);
    }
}
