package com.data.structures.and.algorithms.udemy.recursion;

public class Sum {
    public static int sumOfNnumbers(int n) {
        //return sumOfNNumbers(n, 0);
        return sumOfNNumbers2(n);
    }

    //tail recursion
    private static int sumOfNNumbers(int n, int accumulator) {
        if (n == 0) return accumulator;
        else return sumOfNNumbers(n - 1, accumulator + n);
    }

    private static int sumOfNNumbers2(int n) {
        if (n == 0) return 0;
        else return n + sumOfNNumbers2(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("suma de 4 primeros numeros naturales: " + sumOfNnumbers(4));
        System.out.println("suma de 10 primeros numeros naturales: " + sumOfNnumbers(10));
        System.out.println("suma de 0 primeros numeros naturales: " + sumOfNnumbers(0));
    }
}
