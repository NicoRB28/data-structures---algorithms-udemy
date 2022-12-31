package com.data.structures.and.algorithms.udemy.recursion.subsequences;

/**
 * Find all the subsequences in a given string ie:
 * abc => [a, ab, abc, ac, b, bc, c, '']
 *
 * si getSubsequence funciona correctamente entonces puedo
 * estar seguro que si paso bc retorna b, bc, c, ''
 * entonces si a eso le agrego la a tengo
 * ab, abc, ac, a, b, bc, c, ''
 *
 * COMPLEXITY ANALYSIS:
 *  en el llamdo recursivo O(n) dado que se llama recursivamente por cada letra al metodo getSubsequence()
 *  luego en el for O(2n) porque primero me quedo con los substring que se forman sin contar la primer
 *  letra y luego vuelvo a iterar agregando la primer letra
 *
 *  entonces la complejidad es O(n + 2^n)  pero queda solo O(2^n) dado que se puede ignorar el n
 *
 */
public class Subsequence {

    public static void main(String[] args) {
        String word = "abc";
        System.out.println(getSubsequence(word));
    }

    private static String getSubsequence(String word) {
        if (word.isEmpty()) return "";
        char first = word.charAt(0);
        String rest = getSubsequence(word.substring(1));
        String result = "";
        for (String subseq : rest.split(",",-1)) {
            result += "," + first + subseq;
            result += "," + subseq;
        }
        return result.substring(1);
    }
}
