package com.data.structures.and.algorithms.udemy.recursion.productSumOfArray;

import java.util.List;

/**
 * product sum of special array
 * special array consist of numbers and other arrays.
 * ej: [1, 2,[3,2],6,[[2,4],1],7
 *
 * Time complexity O(N)
 * Space complexity O(depth)
 */
public class ProductSum {

    public static void main(String[] args) {
        List<Object> a1 = List.of(2,3);
        List<Object> a2 = List.of(2,4);
        List<Object> a3 = List.of(a1,1);
        List<Object> array = List.of(1,2,a1,6,a3,7);

        System.out.println(productSum(array));
    }

    private static int productSum(List<Object> list) {
        return productSum(list, 1);
    }

    @SuppressWarnings("unchecked")
    private static int productSum(List<Object> list, int depth) {
        int result = 0;
        for (Object object : list) {
            if (object instanceof List) {
                result += productSum((List<Object>) object, depth + 1);
            } else {
                result += (int)object;
            }
        }
        return result * depth;
    }
}
