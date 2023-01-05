package com.data.structures.and.algorithms.udemy.divideAndConquer;


import java.util.Arrays;

/**
 * Time complexity: O(N²)
 * Space complexity: O(N)
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {10, 1, 67, 20, 56, 34, 43, 90, 54, 8, 0};

        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int lowerBound, int upperBound) {
        int index;
        if (lowerBound < upperBound) {
            index = partition(arr, lowerBound, upperBound);
            quickSort(arr, lowerBound, index - 1);
            quickSort(arr, index + 1, upperBound);
        }
    }

    private static int partition(int[] arr, int lowerBound, int upperBound) {
        int pivot = arr[lowerBound];
        int left = lowerBound;
        int right = upperBound;

        while (left < right) {
            while (arr[left] <= pivot && left < arr.length - 1) left++;
            while (arr[right] > pivot && right > 0) right--;
            // swap
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        // swap right with lower bound/pivot
        int temp = arr[lowerBound];
        arr[lowerBound] = arr[right];
        arr[right] = temp;
        return right;
    }


}
