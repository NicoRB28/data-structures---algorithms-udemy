package com.data.structures.and.algorithms.udemy.greedy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * We get a set of intervals and the goal is to find
 * a set of non overlapping intervals of maximum size.
 */
public class IntervalSchedulingMaximization {
    public static void main(String[] args) {
        int[][] intervals = {
                {0, 3},
                {0, 15},
                {5, 10},
                {7, 12},
                {11, 16},
                {12, 14},
                {16, 20},
        };
        List<int[]> optimalSchedule = schedule(intervals);
        for (int[] is : optimalSchedule) {
            for (int j : is) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    private static List<int[]> schedule(int[][] intervals) {
        List<int[]> optimalIntervalSet = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));//(o1, o2) -> o1[1] - o2[1] comparo los tiempos de
        // finalizacion, que estan en la posicion 1.

        int lastFinishTime = Integer.MIN_VALUE;
        for (int[] interval: intervals) {
            int start = interval[0];
            if (start > lastFinishTime) {
                int end = interval[1];
                optimalIntervalSet.add(interval);
                lastFinishTime = end;
            }
        }

        return optimalIntervalSet;
    }
}
