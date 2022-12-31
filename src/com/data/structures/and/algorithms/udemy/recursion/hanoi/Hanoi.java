package com.data.structures.and.algorithms.udemy.recursion.hanoi;

public class Hanoi {

    public static void main(String[] args) {
        towerOfHanoi(4, 'A', 'C', 'H');
    }

    /**
     * Time complexity:
     * como hay dos llamados recursivos tenemos
     * 2 esos dos llamados vuelven a llamar 2 veces por lo que tenemos
     * 2 -> 2² -> 2³ ->... 2^n
     * por lo que el tiempo es O(2^n)
     *
     * Tn = Tn-1 + Tn-1 + 1 = 2Tn-1 + 1
     * Tn-1 = 2Tn-2 + 1
     * sustitución:
     * Tn = 2(2Tn-2 + 1) + 1 = 2²Tn-2 + 3
     * Tn-2 = 2Tn-3 + 1
     *
     * Tn = 2²(2Tn-3 + 1) + 1 = (2²*2Tn-3 + 2²) + 1 = 2³Tn-3 + 5
     *
     * Tn = 2Tn-1 + 1  (1)
     * Tn = 2²Tn-2 + 3 (2)
     * Tn = 2³Tn-3 + 5 (3)
     *
     * Generalizando: Tn = 2^k*Tn-k + C
     * tomando el caso base donde n = 1 tenemos que
     * Tn = 2^k T1 + C => n-k = 1 y k = n-1
     * sustitucion:
     * Tn = 2^n-1T1 + C, T1 = 1
     * Tn = 2^n-1 que es aprox 2^n => O(2^n)
     *
     * Pero no es necesario realizar todo este analisis, cuando se evalua el T de una funcion
     * recursiva donde la operatoria en el caso base es constante (como en este caso) el T
     * sera (BranchingFactor)^n siendo BranchingFactor igual a la cantidad de llamados recursivos
     * dentro de la funcion, en este caso hay dos llamados recursivos por eso es 2^n
     *
     * space complexity is O(n) tiene forma de arbol binario y como N es la altura del arbol esa es la
     * maxima cantidad de stack utilizada.
     *
     * @param numberOfDisk
     * @param from
     * @param to
     * @param aux
     */
    private static void towerOfHanoi(int numberOfDisk, char from, char to, char aux) {
        if (numberOfDisk == 1) {
            System.out.println("moving the disk 1 from " + from +" to the" + to + " rod.");
        } else {
            //se mueven todos menos el ultimo disco desde la fila origen a la fila
            //auxiliar utilizando como ayuda la 3er fila.
            // |        |        |
            // |        |        |
            // |        |        |
            // _        _        _
            //from     aux       to
            towerOfHanoi(numberOfDisk - 1, from, aux, to);
            System.out.println("moving the disk " + numberOfDisk + " from " + from + " to the" + to + " rod.");
            //se mueven todos los discos que quedaron en la fila aux a la 3er fila utilizando como
            //ayuda la fila 1
            towerOfHanoi(numberOfDisk - 1, aux, to, from);
        }
    }
}
