package com.data.structures.and.algorithms.udemy.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Data compression algorithm
 * ABBCCCCGGGGDEAAAEDBBBDFAGG
 *
 */
public class HuffmanCoding {

    static HuffmanNode root;
    static Map<Character, String> charBinaryMapping = new HashMap<>();

    public static void main(String[] args) {
        String word = "ABBCCCCGGGGDEAAAEDBBBDFAGG";

        String code = encode(word);
        System.out.println("Final Coded String : " + code);
    }

    private static String encode(String word) {
        Map<Character, Integer> frequencies = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            if (!frequencies.containsKey(word.charAt(i))) {
                frequencies.put(word.charAt(i), 0);
            }
            frequencies.put(word.charAt(i), frequencies.get(word.charAt(i)) + 1);
        }
        HuffmanNode root = generateTree(frequencies);
        setBinaryCode(root, new StringBuilder());

        System.out.println("Binary Codes : " + charBinaryMapping);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            sb.append(charBinaryMapping.get(character));
        }
        return sb.toString();
    }

    private static void setBinaryCode(HuffmanNode root, StringBuilder stringBuilder) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                charBinaryMapping.put(root.data, stringBuilder.toString());
            } else {
                stringBuilder.append('0');
                setBinaryCode(root.left, stringBuilder);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);

                stringBuilder.append('1');
                setBinaryCode(root.right, stringBuilder);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }

        }
    }

    private static HuffmanNode generateTree(Map<Character, Integer> frequencies) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        Set<Character> keySet = frequencies.keySet();
        for (Character character : keySet) {

            HuffmanNode node = new HuffmanNode();

            node.data = character;
            node.frequency = frequencies.get(character);
            node.left = null;
            node.right = null;

            priorityQueue.offer(node);// como los nodos implementan comparable se ordenan por frequencia en orden asc.
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode firstElement = priorityQueue.peek(); // obtenemos primer elemento
            priorityQueue.poll(); // lo eliminamos

            HuffmanNode secondElement = priorityQueue.peek(); // obtenemos el segundo
            priorityQueue.poll(); // lo eliminamos

            HuffmanNode mergeNode = new HuffmanNode();
            mergeNode.left = firstElement;
            mergeNode.right = secondElement;
            mergeNode.frequency = firstElement.frequency + secondElement.frequency;
            mergeNode.data = '-';
            root = mergeNode;

            priorityQueue.add(mergeNode);
        }
        return priorityQueue.poll();
    }

}

class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    char data;
    HuffmanNode left, right;

    @Override
    public int compareTo(HuffmanNode o) {
        return frequency - o.frequency;
    }
}
